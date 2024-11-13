package AnswerDiscussion;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import org.json.JSONArray;
import org.json.JSONObject;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;


public class Main {
  public static void main(String[] args) {
    Canvas canvas = new Canvas("33973");
    JSONObject discussion = canvas.getFirstDiscussionTopic();
    JSONObject file = canvas.getFirstFile();
    System.out.println("newest discussion: "+discussion.get("title")+" - "+discussion.get("id"));
    System.out.println("newest file: "+file.get("filename")+" - "+file.get("url"));

    String PDF = canvas.getFileData(file.get("url").toString());
    String prompt = PDF.concat("\n\n"+discussion.get("title")+"\n"+discussion.get("message"));
    System.out.println(prompt);

    ChatGPT chatGPT = new ChatGPT(prompt);
    System.out.println("Sending prompt to ChatGPT...");
    String response = chatGPT.getResponse();
    System.out.println(response);


//    JSONObject response = canvas.postDiscussionEntry("188636", "Testing from IntelliJ");
//    System.out.println(response);
  }
}