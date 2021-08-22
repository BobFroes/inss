package com.inss.kafka;

import com.google.gson.Gson;
import com.inss.builder.Builder;
import com.inss.common.constant.Topics;
import com.inss.db.PostgresContainer;
import com.inss.service.CalculateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@Testcontainers
@DirtiesContext
@SpringBootTest
@EmbeddedKafka(topics = {Topics.INSS_CALCULATE_REQUEST, Topics.INSS_CALCULATE_REPLY},
        partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
class ConsumerIntegrationTest {

    @Container
    public static PostgreSQLContainer<PostgresContainer> postgreSQLContainer = PostgresContainer.getInstance();

    @Autowired
    private Builder builder;

    @SpyBean
    private Consumer consumer;

    @SpyBean
    private CalculateService service;

    @SpyBean
    private Producer producer;

    @BeforeEach
    void setUp() {
       builder.save();
    }

    @Test
    void it_should_calculate_successfully() throws Exception {

        var request = builder.calculateRequest();
        var response = builder.calculatedReply();

        consumer.receive(new Gson().toJson(request));

        verify(service, times(1)).execute(request);;
        verify(producer, times(1)).send(Topics.INSS_CALCULATE_REPLY, response);

    }

}