package com.inss.kafka;

import com.google.gson.Gson;
import com.inss.common.constant.Topics;
import com.inss.kafka.request.CalculateRequest;
import com.inss.service.CalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @Autowired
    private CalculateService service;

    @Autowired
    private Producer producer;

    @KafkaListener(topics = Topics.INSS_CALCULATE, groupId = "inss-group")
    public void receive(String topicRequest) {
        var request = new Gson().fromJson(topicRequest, CalculateRequest.class);
        var response = service.execute(request);
        producer.send(Topics.INSS_RESPONSE, response);
    }

}
