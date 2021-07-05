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
public class KafkaSender {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public ListenableFuture<SendResult<String, String>> execute(String topic, Object request) {

        Message<String> message = MessageBuilder
                .withPayload(new Gson().toJson(request))
                .setHeader(KafkaHeaders.TOPIC, topic)
                .build();

        return this.kafkaTemplate.send(message);

    }

}
