package AnswerDiscussion;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

class utils {
  static String or(String a, String b) {
    return a != null ? a : b;
  }
}

public class ChatGPT {
  static HttpClient client = HttpClient.newHttpClient();
  static String API_KEY = System.getenv("OPENAI_KEY");
  static String API_URL = utils.or(
    System.getenv("OPENAI_URL"),
    "https://api.openai.com/v1/chat/completions"
  );
  String model;
  String prompt;
  JSONObject body;
  HttpRequest request;

  public ChatGPT(String prompt) {
    this.prompt = prompt;
    this.model = "gpt-4o-mini";
    this.body = new JSONObject()
      .put("model", this.model)
      .put("messages", new JSONArray()
        .put(new JSONObject()
          .put("role", "user")
          .put("content", new JSONArray()
            .put(new JSONObject()
              .put("type", "text")
              .put("text", this.prompt)
            )
          )
        )
      );
    this.request = HttpRequest.newBuilder()
      .uri(URI.create(API_URL))
      .header("Content-Type", "application/json")
      .header("Authorization", "Bearer " + API_KEY)
      .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
      .build();
  }

  public String getResponse() {
    this.request = HttpRequest.newBuilder()
      .uri(URI.create(API_URL))
      .header("Content-Type", "application/json")
      .header("Authorization", "Bearer " + API_KEY)
      .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
      .build();
    try {
      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

      JSONObject body = new JSONObject(response.body());
      JSONArray choices = new JSONArray(body.get("choices").toString());
      JSONObject choice = new JSONObject(choices.get(0).toString());
      JSONObject message = new JSONObject(choice.get("message").toString());
      String content = message.get("content").toString();
      return content;

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}