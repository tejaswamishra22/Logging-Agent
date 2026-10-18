package com.example.logagents;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/logs")
public class LogIngestController {

    private final KafkaTemplate<String, String> kafka;

    public LogIngestController(KafkaTemplate<String, String> kafka) {
        this.kafka = kafka;
    }

    @PostMapping
    public String ingest(@RequestBody String logLine) {
        kafka.send("raw-logs", logLine);
        return "queued";
    }
}