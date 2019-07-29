package com.mccl.poc.kafkapoc.service;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class KafkaServiceImpl implements KafkaService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final static Logger LOGGER = LoggerFactory.getLogger(KafkaServiceImpl.class);

    @Autowired
    public KafkaServiceImpl(final KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendMessage(String topicName, String message) {
        sendMessageToKafka(topicName, null, message);
    }

    @Override
    public void sendMassiveMessages(String topicName, Integer totalMessages) {
        for (int i=0; i < totalMessages; i++) {
            String message = "Message " + i;
            sendMessageToKafka(topicName, Integer.valueOf(i/10).toString(), message);
        }
    }

    @KafkaListener(topics = "newCustomerCreated", groupId = "users")
    public void readMessage(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        LOGGER.info(".:: Received message {} from partition {} ::.", message, partition);
    }

    private void sendMessageToKafka(String topicName, String key, String message) {
        kafkaTemplate.send(topicName, key, message).addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                LOGGER.error(".:: Error sending message: {} to topic: {} ::.", message, topicName);
                throwable.printStackTrace();
            }

            @Override
            public void onSuccess(SendResult<String, String> stringSendResult) {
                RecordMetadata recordMetadata = stringSendResult.getRecordMetadata();
                LOGGER.info(".:: Message sent successfully offset:{}, partition:{}, key:{}, message:{} ::.",
                        recordMetadata.offset(),
                        recordMetadata.partition(),
                        key,
                        message);
            }
        });
    }
}
