package com.inss.config.kafka;

import com.inss.common.constant.Topics;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic inssCalculate(){
        return TopicBuilder.name(Topics.INSS_CALCULATE).partitions(3).replicas(1).build();
    }

    @Bean
    public NewTopic inssResponse(){
        return TopicBuilder.name(Topics.INSS_RESPONSE).partitions(3).replicas(1).build();
    }
}
