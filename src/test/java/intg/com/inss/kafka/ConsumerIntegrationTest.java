package com.inss.kafka;

import com.google.gson.Gson;
import com.inss.builder.CalculateBuilder;
import com.inss.builder.InssBuilder;
import com.inss.common.constant.Topics;
import com.inss.db.CustomPostgresContainer;
import com.inss.domain.InssRepository;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Map;

@Testcontainers
@DirtiesContext
@SpringBootTest
@EmbeddedKafka(topics = { Topics.INSS_CALCULATE, Topics.INSS_RESPONSE})
class ConsumerIntegrationTest {

    @Container
    public static PostgreSQLContainer<CustomPostgresContainer> postgreSQLContainer = CustomPostgresContainer.getInstance();

    private CalculateBuilder builder;

    private KafkaProducer<String, String> producer;
    private KafkaConsumer<String, String> consumer;

    @Autowired
    private EmbeddedKafkaBroker embeddedKafkaBroker;

    @Autowired
    private InssRepository repository;

    @BeforeEach
    void setUp() {
        repository.save(new InssBuilder().get());
        builder = new CalculateBuilder();

        Map<String, Object> producerProps = KafkaTestUtils.producerProps(embeddedKafkaBroker);
        producer = new KafkaProducer<>(producerProps);

        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps("inss-group", "false", embeddedKafkaBroker);
        consumer = new KafkaConsumer<>(consumerProps);
        embeddedKafkaBroker.consumeFromAnEmbeddedTopic(consumer, Topics.INSS_CALCULATE);
    }

    @Test
    void it_should_calculate_successfully() throws Exception {
        producer.send(new ProducerRecord<>(Topics.INSS_CALCULATE, 0, null, new Gson().toJson(builder.withEmployees())));

        var records = KafkaTestUtils.getRecords(consumer, 10_000L, 1);

        Assertions.assertEquals(1, records.count());

        producer.close();
        consumer.close();
    }

}