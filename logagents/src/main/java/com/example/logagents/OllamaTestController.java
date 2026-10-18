package com.example.logagents;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ollama")
public class OllamaTestController {

    private final OllamaClient ollama;

    public OllamaTestController(OllamaClient ollama) {
        this.ollama = ollama;
    }

    @PostMapping("/ask")
    public String ask(@RequestBody String prompt) {
        return ollama.generate(prompt);
    }

    @PostMapping("/embed")
    public int embed(@RequestBody String text) {
        return ollama.embed(text).length;
    }
}