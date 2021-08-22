package com.inss.config.kafka;

import com.inss.common.constant.Topics;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic inssCalculateRequest() {
        return TopicBuilder.name(Topics.INSS_CALCULATE_REQUEST).partitions(3).replicas(1).build();
    }

    @Bean
    public NewTopic inssCalculateReply() {
        return TopicBuilder.name(Topics.INSS_CALCULATE_REPLY).partitions(3).replicas(1).build();
    }
}
