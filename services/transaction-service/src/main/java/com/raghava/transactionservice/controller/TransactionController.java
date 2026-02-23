package com.raghava.transactionservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.raghava.transactionservice.dto.TransactionRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private static final Logger log = LoggerFactory.getLogger(TransactionController.class);

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final String topic;

    public TransactionController(
            KafkaTemplate<String, String> kafkaTemplate,
            ObjectMapper objectMapper,
            @Value("${app.kafka.topic.transactions}") String topic
    ) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
        this.topic = topic;
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody TransactionRequest req) throws Exception {
        log.info("Publishing transactionId={} userId={} amount={} currency={} to topic={}",
                req.getTransactionId(), req.getUserId(), req.getAmount(), req.getCurrency(), topic);

        String json = objectMapper.writeValueAsString(req);
        kafkaTemplate.send(topic, req.getTransactionId(), json);

        return ResponseEntity.accepted().build();
    }
}
