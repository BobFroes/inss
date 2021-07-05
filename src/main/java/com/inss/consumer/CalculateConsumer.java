package com.inss.consumer;

import com.google.gson.Gson;
import com.inss.http.request.CalculateRequest;
import com.inss.kafka.KafkaSender;
import com.inss.service.CalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CalculateConsumer {

    @Value("${spring.kafka.topic.inss-response}")
    private String topicResponse;

    @Autowired
    private CalculateService service;

    @Autowired
    private KafkaSender sender;

    @KafkaListener(topics = "${spring.kafka.topic.inss-calculate}")
    public void consume(String topicRequest) {
        var request = new Gson().fromJson(topicRequest, CalculateRequest.class);
        var response = service.execute(request);
        sender.execute(topicResponse, response);
    }

}
