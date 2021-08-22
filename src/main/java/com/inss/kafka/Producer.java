package com.inss.kafka;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, Object request) {

        Message<String> message = MessageBuilder
                .withPayload(new Gson().toJson(request))
                .setHeader(KafkaHeaders.TOPIC, topic)
                .build();

        this.kafkaTemplate.send(message);

    }

}
