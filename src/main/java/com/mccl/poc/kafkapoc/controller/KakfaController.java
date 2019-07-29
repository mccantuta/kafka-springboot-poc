package com.mccl.poc.kafkapoc.controller;

import com.mccl.poc.kafkapoc.service.KafkaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KakfaController {

    private final KafkaService kafkaService;
    private final static Logger LOGGER = LoggerFactory.getLogger(KakfaController.class);

    @Autowired
    public KakfaController(final KafkaService kafkaService) {
        this.kafkaService = kafkaService;
    }

    @PostMapping("/publishMessage")
    public void publishMessage(
            @RequestParam String topicName,
            @RequestParam String message) {
        LOGGER.info(".:: Start publishMessage, topicName={}, message={} ::.", topicName, message);
        kafkaService.sendMessage(topicName, message);
    }

    @PostMapping("/publishMassiveMessage")
    public void publishMassiveMessage(
            @RequestParam String topicName,
            @RequestParam Integer quantity) {
        LOGGER.info(".:: Start publishMessage, topicName={}, quantity={} ::.", topicName, quantity);
        kafkaService.sendMassiveMessages(topicName, quantity);
    }
}