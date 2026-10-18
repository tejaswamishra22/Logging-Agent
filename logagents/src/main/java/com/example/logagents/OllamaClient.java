package com.example.logagents;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class OllamaClient {

    private static final String CHAT_MODEL = "llama3.1:8b";
    private static final String EMBED_MODEL = "nomic-embed-text";

    private final RestClient http = RestClient.builder()
            .baseUrl("http://localhost:11434")
            .build();

    public record EmbedRequest(String model, String prompt) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record EmbedResponse(float[] embedding) {}

    public record GenerateRequest(String model, String prompt, boolean stream,
                                  @JsonProperty("keep_alive") String keepAlive) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record GenerateResponse(String response) {}

    public float[] embed(String text) {
        EmbedResponse res = http.post()
                .uri("/api/embeddings")
                .body(new EmbedRequest(EMBED_MODEL, text))
                .retrieve()
                .body(EmbedResponse.class);
        return res.embedding();
    }

    public String generate(String prompt) {
        GenerateResponse res = http.post()
                .uri("/api/generate")
                .body(new GenerateRequest(CHAT_MODEL, prompt, false, "30m"))
                .retrieve()
                .body(GenerateResponse.class);
        return res.response();
    }
}