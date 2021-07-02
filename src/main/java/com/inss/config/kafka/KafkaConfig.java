package com.inss.config.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.topic.inss-calculate}")
    private String inssCalculate;

    @Bean
    public NewTopic inssCalculate(){
        return TopicBuilder.name(inssCalculate).partitions(3).replicas(1).build();
    }

}
