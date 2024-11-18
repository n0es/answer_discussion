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
    RuntimeException exception = Assertions.assertThrows(RuntimeException.class, 
      () -> new Canvas("invalid_id"),
      "Should throw RuntimeException for invalid class ID"
    );
    Assertions.assertTrue(exception.getMessage().contains("Course does not exist"),
      "Exception message should indicate course does not exist");
  }

  @Test
  void testUnauthorizedClassId() {
    RuntimeException exception = Assertions.assertThrows(RuntimeException.class, 
      () -> new Canvas("0"),
      "Should throw RuntimeException for unauthorized access"
    );
    Assertions.assertTrue(exception.getMessage().contains("Unauthorized"),
      "Exception message should indicate unauthorized access");
  }

  @Test
  void testValidClassId() {
    Assertions.assertDoesNotThrow(
      () -> new Canvas("33973"),
      "Should not throw exception for valid class ID"
    );
  }

  @Test
  void testCanvasToken() {
    Assertions.assertNotNull(System.getenv("CANVAS_TOKEN"),
      "CANVAS_TOKEN environment variable should be set");
  }

  @Test
  void testGetModules() {
    JSONArray modules = canvas.getModules();
    Assertions.assertNotNull(modules,
      "Modules list should not be null");
  }

  @Test
  void testGetDiscussionTopics() {
    JSONArray topics = canvas.getDiscussionTopics();
    System.out.println(topics);
    Assertions.assertNotNull(topics,
      "Discussion topics list should not be null");
    Assertions.assertTrue(topics.length() == 0,
      "Discussion topics list should be empty");
  }

  @Test
  void testGetAllDiscussionTopics() {
    JSONArray topics = canvas.getAllDiscussionTopics();
    System.out.println(topics);
    Assertions.assertNotNull(topics,
      "All discussion topics list should not be null");
    Assertions.assertTrue(topics.length() == 2,
      "Should have exactly 2 discussion topics");
  }

  @Test
  void testGetFirstDiscussionTopic() {
    JSONObject topic = canvas.getFirstDiscussionTopic();
    Assertions.assertNull(topic,
      "First discussion topic should be null");
  }

  @Test
  void testGetAnyDiscussionTopic() {
    JSONObject topic = canvas.getAnyDiscussionTopic();
    Assertions.assertNotNull(topic,
      "Should find at least one discussion topic");
  }

  @Test
  void testGetFiles() {
    JSONArray files = canvas.getFiles();
    Assertions.assertNotNull(files,
      "Files list should not be null");
    Assertions.assertTrue(files.length() == 2,
      "Should have exactly 2 files");
  }

  @Test
  void testGetFirstFile() {
    JSONObject file = canvas.getFirstFile();
    Assertions.assertNotNull(file,
      "First file should not be null");
    Assertions.assertTrue(file.has("filename"),
      "File should have a filename property");
    Assertions.assertTrue(file.getString("filename").equals("2014-scheme-workshop-proceedings.pdf"),
      "First file should be the expected PDF");
  }

  @Test
  void testGetFileData() {
    JSONObject file = canvas.getFirstFile();
    String pdfContent = canvas.getFileData(file.getString("url"));
    Assertions.assertNotNull(pdfContent,
      "PDF content should not be null");
    Assertions.assertFalse(pdfContent.isEmpty(),
      "PDF content should not be empty");
  }
}
