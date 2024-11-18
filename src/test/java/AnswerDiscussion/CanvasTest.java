package AnswerDiscussion;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.*;

public class CanvasTest {
  private static Canvas canvas;

  @BeforeAll
  static void setUp() {
    canvas = new Canvas("33973");
  }

  @Test
  void testInvalidClassId() {
    RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> 
      new Canvas("invalid_id")
    );
    Assertions.assertTrue(exception.getMessage().contains("Course does not exist"));
  }

  @Test
  void testUnauthorizedClassId() {
    RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> 
      new Canvas("0")
    );
    Assertions.assertTrue(exception.getMessage().contains("Unauthorized"));
  }

  @Test
  void testValidClassId() {
    Assertions.assertDoesNotThrow(() -> new Canvas("33973"));
  }

  @Test
  void testCanvasToken() {
    Assertions.assertNotNull(System.getenv("CANVAS_TOKEN"));
  }

  @Test
  void testGetModules() {
    JSONArray modules = canvas.getModules();
    Assertions.assertNotNull(modules);
  }

  @Test
  void testGetDiscussionTopics() {
    JSONArray topics = canvas.getDiscussionTopics();
    System.out.println(topics);
    Assertions.assertNotNull(topics);
    Assertions.assertTrue(topics.length() == 0);
  }

  @Test
  void testGetAllDiscussionTopics() {
    JSONArray topics = canvas.getAllDiscussionTopics();
    System.out.println(topics);
    Assertions.assertNotNull(topics);
    Assertions.assertTrue(topics.length() == 2);
  }

  @Test
  void testGetFirstDiscussionTopic() {
    JSONObject topic = canvas.getFirstDiscussionTopic();
    Assertions.assertNull(topic);
  }

  @Test
  void testGetAnyDiscussionTopic() {
    JSONObject topic = canvas.getAnyDiscussionTopic();
    Assertions.assertNotNull(topic);
  }

  @Test
  void testGetFiles() {
    JSONArray files = canvas.getFiles();
    Assertions.assertNotNull(files);
    Assertions.assertTrue(files.length() == 2);
  }

  @Test
  void testGetFirstFile() {
    JSONObject file = canvas.getFirstFile();
    Assertions.assertNotNull(file);
    Assertions.assertTrue(file.has("filename"));
    Assertions.assertTrue(file.getString("filename").equals("2014-scheme-workshop-proceedings.pdf"));
  }

  @Test
  void testGetFileData() {
    JSONObject file = canvas.getFirstFile();
    String pdfContent = canvas.getFileData(file.getString("url"));
    Assertions.assertNotNull(pdfContent);
    Assertions.assertFalse(pdfContent.isEmpty());
  }
}
