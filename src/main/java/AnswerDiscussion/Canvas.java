package AnswerDiscussion;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class Canvas {
  static OkHttpClient client = new OkHttpClient().newBuilder().build();
  static String CANVAS_TOKEN = System.getenv("CANVAS_TOKEN");
  String classId;

  public Canvas(String classId) {
    this.classId = classId;
    JSONObject course = getCourse();
    if (!course.get("id").toString().equals(classId)) {
      throw new RuntimeException("Invalid class ID");
    }
  }

  Request buildPostRequest(String uri, RequestBody body) {
    return new Request.Builder()
      .url("https://setonhall.instructure.com/api/v1/courses/" + this.classId + uri)
      .method("POST", body)
      .addHeader("Authorization", "Bearer " + CANVAS_TOKEN)
      .build();
  }

  Request buildGetRequest(String uri) {
    return new Request.Builder()
      .url("https://setonhall.instructure.com/api/v1/courses/" + this.classId + uri)
      .method("GET", null)
      .addHeader("Authorization", "Bearer " + CANVAS_TOKEN)
      .build();
  }

  ResponseBody sendRequest(Request request) {
    try {
      Response response = client.newCall(request).execute();
      
      // Handle common HTTP status codes
      switch (response.code()) {
        case 200:
        case 201:
          if (response.body() == null) {
            throw new RuntimeException("Response body is empty");
          }
          return response.body();
        case 401:
          throw new RuntimeException("Unauthorized: Please check your Canvas API token");
        case 403:
          throw new RuntimeException("Forbidden: You don't have permission to access this resource");
        case 404:
          throw new RuntimeException("Not Found: The requested resource doesn't exist");
        case 429:
          throw new RuntimeException("Rate Limited: Too many requests. Please try again later");
        default:
          throw new RuntimeException("HTTP " + response.code() + ": " + response.message());
      }
    } catch (IOException e) {
      throw new RuntimeException("Network error: " + e.getMessage(), e);
    } catch (RuntimeException e) {
      throw e; // Re-throw runtime exceptions without wrapping
    } catch (Exception e) {
      throw new RuntimeException("Unexpected error: " + e.getMessage(), e);
    }
  }

  String bodyToString(ResponseBody response) {
    try {
      return response.string();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public JSONObject getCourse() {
    try {
      Request request = buildGetRequest("");
      ResponseBody response = sendRequest(request);
      String body = bodyToString(response);
      return new JSONObject(body);
    } catch (RuntimeException e) {
      if (e.getMessage().contains("Not Found")) {
        throw new RuntimeException("Course does not exist");
      }
      throw e;
    }
  }

  public JSONArray getModules() {
    Request request = buildGetRequest("/modules");
    ResponseBody response = sendRequest(request);
    String body = bodyToString(response);

    return new JSONArray(body);
  }

  public JSONArray getModuleItems(String moduleId) {
    Request request = buildGetRequest("/modules/" + moduleId + "/items");
    ResponseBody response = sendRequest(request);
    String body = bodyToString(response);

    return new JSONArray(body);
  }

  public JSONArray getFiles() {
    Request request = buildGetRequest("/files");
    ResponseBody response = sendRequest(request);
    String body = bodyToString(response);

    return new JSONArray(body);
  }

  public JSONObject getFirstFile() {
    JSONArray files = getFiles();
    JSONObject newestFile = null;
    long newestEpoch = 0;

    for (int i = 0; i < files.length(); i++) {
      JSONObject topic = files.getJSONObject(i);
      String postedAt = topic.getString("created_at");
      long epoch = java.time.Instant.parse(postedAt).toEpochMilli();

      if (epoch > newestEpoch) {
        newestEpoch = epoch;
        newestFile = topic;
      }
    }

    return newestFile;
  }

  public JSONArray getDiscussionTopics() {
    Request request = buildGetRequest("/discussion_topics?order_by=recent_activity&scope=unlocked");
    ResponseBody response = sendRequest(request);
    String body = bodyToString(response);

    return new JSONArray(body);
  }

  public JSONArray getAllDiscussionTopics() {
    Request request = buildGetRequest("/discussion_topics");
    ResponseBody response = sendRequest(request);
    String body = bodyToString(response);

    return new JSONArray(body);
  }

  public JSONObject getFirstDiscussionTopic() {
    JSONArray topics = getDiscussionTopics();
    JSONObject newestTopic = null;
    long newestEpoch = 0;

    for (int i = 0; i < topics.length(); i++) {
      JSONObject topic = topics.getJSONObject(i);
      String postedAt = topic.getString("posted_at");
      long epoch = java.time.Instant.parse(postedAt).toEpochMilli();

      if (epoch > newestEpoch) {
        newestEpoch = epoch;
        newestTopic = topic;
      }
    }

    if (newestTopic == null) {
      throw new RuntimeException("No discussion topics found");
    }

    return newestTopic;
  }

  public JSONObject getAnyDiscussionTopic() {
    JSONArray topics = getAllDiscussionTopics();
    JSONObject newestTopic = null;
    long newestEpoch = 0;

    for (int i = 0; i < topics.length(); i++) {
      JSONObject topic = topics.getJSONObject(i);
      String postedAt = topic.getString("posted_at");
      long epoch = java.time.Instant.parse(postedAt).toEpochMilli();

      if (epoch > newestEpoch) {
        newestEpoch = epoch;
        newestTopic = topic;
      }
    }

    if (newestTopic == null) {
      throw new RuntimeException("No discussion topics found");
    }

    return newestTopic;
  }

  public void postDiscussionEntry(String topicId, String message) {
    RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
      .addFormDataPart("message", message)
      .build();
    Request request = buildPostRequest("/discussion_topics/" + topicId + "/entries", body);
    sendRequest(request);
  }

  String downloadFile(String url, String fileName) {
    Request request = new Request.Builder()
      .url(url)
      .get()
      .build();
    try {
      Response response = client.newCall(request).execute();
      if (!response.isSuccessful()) {
        throw new RuntimeException("Unexpected response: " + response);
      } else if (response.body() == null) {
        throw new RuntimeException("Response body is empty");
      } else {
        File file = new File(fileName);
        // check if file exists

        if (file.exists()) {
          file.delete();
        }

        if (!file.createNewFile()) {
          throw new RuntimeException("Failed to create file");
        }

        FileOutputStream fos = new FileOutputStream(file);
        fos.write(response.body().bytes());
        fos.close();
        return file.getAbsolutePath();
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  String getFileData(String url) {
    Request request = new Request.Builder()
      .url(url)
      .get()
      .build();
    try {
      Response response = client.newCall(request).execute();
      if (!response.isSuccessful()) {
        throw new RuntimeException("Unexpected response: " + response);
      } else if (response.body() == null) {
        throw new RuntimeException("Response body is empty");
      } else {
        File file = new File("temp.pdf");
        if (file.exists()) {
          file.delete();
        }
        if (!file.createNewFile()) {
          throw new RuntimeException("Failed to create file");
        }
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(response.body().bytes());
        fos.close();

        PdfReader reader = new PdfReader(file.getAbsolutePath());
        PdfReaderContentParser parser = new PdfReaderContentParser(reader);

        StringBuilder PDFText = new StringBuilder();
        TextExtractionStrategy strategy;
        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
          strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
          PDFText.append(strategy.getResultantText());
        }
        reader.close();
        file.delete();
        return PDFText.toString();
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  boolean deleteFile(String filePath) {
    File file = new File(filePath);
    return file.delete();
  }

  public String extractTextFromPDF(JSONObject file) {
    String URL = file.get("url").toString();
    String fileName = file.get("filename").toString();
    String filePath = downloadFile(URL, fileName);

    StringBuilder PDFText = new StringBuilder();

    try {
      PdfReader reader = new PdfReader(filePath);
      PdfReaderContentParser parser = new PdfReaderContentParser(reader);

      TextExtractionStrategy strategy;
      for (int i = 1; i <= reader.getNumberOfPages(); i++) {
        strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
        PDFText.append(strategy.getResultantText());
      }
      reader.close();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    return PDFText.toString();
  }
}