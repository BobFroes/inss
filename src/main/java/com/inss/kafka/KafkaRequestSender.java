package com.inss.kafka;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
public class KafkaRequestSender {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public ListenableFuture<SendResult<String, String>> execute(String topicRequest, Object objectRequest) {

        Message<String> message = MessageBuilder
                .withPayload(new Gson().toJson(objectRequest))
                .setHeader(KafkaHeaders.TOPIC, topicRequest)
                .build();

        return this.kafkaTemplate.send(message);

    }

}
