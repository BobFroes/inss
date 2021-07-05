package com.inss.consumer;

import com.inss.builder.CalculateBuilder;
import com.inss.http.request.CalculateRequest;
import com.inss.http.response.CalculateResponse;
import com.inss.kafka.KafkaSender;
import com.inss.service.CalculateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalculateConsumerTest {

    private CalculateBuilder builder;

    private static final String TOPIC_RESPONSE = "inss-response";

    @Mock
    private CalculateService service;

    @Mock
    private KafkaSender sender;

    @Captor
    private ArgumentCaptor<CalculateRequest> captor;

    @InjectMocks
    private CalculateConsumer consumer;

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(consumer, "topicResponse", TOPIC_RESPONSE);
        builder = new CalculateBuilder();
    }

    @Test
    void it_should_calculate_tributes_successfully() {

        var request = builder.withEmployees();

        when(service.execute(any(CalculateRequest.class))).thenReturn(mock(CalculateResponse.class));

        var response = service.execute(request);

        verify(service, times(1)).execute(captor.capture());

        assertEquals(captor.getValue(), request);

    }

}