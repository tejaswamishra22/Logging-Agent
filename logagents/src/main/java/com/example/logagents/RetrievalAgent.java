package com.example.logagents;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class RetrievalAgent {

    private static final Logger log = LoggerFactory.getLogger(RetrievalAgent.class);

    @KafkaListener(topics = "raw-logs")
    public void onLog(String logLine) {
        log.info("RetrievalAgent received: {}", logLine);
    }
}