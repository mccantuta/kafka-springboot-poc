package com.mccl.poc.kafkapoc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KakfaController {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final static Logger LOGGER = LoggerFactory.getLogger(KakfaController.class);

    public KakfaController(final KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/publishMessage")
    public void publishMessage(@RequestParam String message) {
        LOGGER.info(".:: Start publishMessage, message={} ::.", message);
        kafkaTemplate.send("newCustomerCreated", message);
    }
}
