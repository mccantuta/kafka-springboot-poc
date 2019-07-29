package com.mccl.poc.kafkapoc.service;

public interface KafkaService {

    void sendMessage(String topicName, String message);

    void sendMassiveMessages(String topicName, Integer totalMessages);
}
