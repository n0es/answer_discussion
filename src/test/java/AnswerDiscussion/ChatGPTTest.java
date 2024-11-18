package AnswerDiscussion;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.*;

public class ChatGPTTest {
    private static ChatGPT chatGPT;
    private static final String TEST_PROMPT = "Hello, this is a test prompt.";

    @BeforeAll
    static void setUp() {
        chatGPT = new ChatGPT(TEST_PROMPT);
    }

    @Test
    void testOpenAIToken() {
        Assertions.assertNotNull(System.getenv("OPENAI_KEY"), 
            "OPENAI_KEY environment variable should be set");
    }

    @Test
    void testChatGPTConstruction() {
        Assertions.assertDoesNotThrow(() -> new ChatGPT(TEST_PROMPT),
            "ChatGPT constructor should not throw an exception");
    }

    @Test
    void testPromptStorage() {
        ChatGPT instance = new ChatGPT(TEST_PROMPT);
        Assertions.assertEquals(TEST_PROMPT, instance.prompt,
            "Stored prompt should match the input prompt");
    }

    @Test
    void testModelName() {
        ChatGPT instance = new ChatGPT(TEST_PROMPT);
        Assertions.assertEquals("gpt-4o-mini", instance.model,
            "Default model should be gpt-4o-mini");
    }

    @Test
    void testRequestBody() {
        ChatGPT instance = new ChatGPT(TEST_PROMPT);
        JSONObject body = instance.body;
        
        Assertions.assertNotNull(body, "Request body should not be null");
        Assertions.assertEquals("gpt-4o-mini", body.getString("model"),
            "Body should contain correct model name");
        
        JSONArray messages = body.getJSONArray("messages");
        Assertions.assertNotNull(messages, "Messages array should exist");
        Assertions.assertEquals(1, messages.length(),
            "Should have exactly one message");
        
        JSONObject message = messages.getJSONObject(0);
        Assertions.assertEquals("user", message.getString("role"),
            "Message should have 'user' role");
        
        JSONArray content = message.getJSONArray("content");
        JSONObject textContent = content.getJSONObject(0);
        Assertions.assertEquals("text", textContent.getString("type"),
            "Content should have 'text' type");
        Assertions.assertEquals(TEST_PROMPT, textContent.getString("text"),
            "Content text should match input prompt");
    }

    @Test
    void testGetResponse() {
        Assertions.assertDoesNotThrow(() -> {
            String response = chatGPT.getResponse();
            Assertions.assertNotNull(response, "Response should not be null");
            Assertions.assertFalse(response.isEmpty(), "Response should not be empty");
        }, "getResponse should not throw an exception");
    }
}
