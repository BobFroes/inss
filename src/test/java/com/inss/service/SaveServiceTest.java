package com.inss.service;

import com.inss.domain.Inss;
import com.inss.domain.InssRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SaveServiceTest {

    Inss inss, withNegativeNumbers, withBigNumbers, nullable;

    @Mock
    private InssRepository repository;

    @InjectMocks
    private SaveService service;

    @Captor
    private ArgumentCaptor<Inss> captor;

    @BeforeEach
    public void setUp() {

        inss = Inss
                .builder()
                .id(UUID.randomUUID())
                .year("2020")
                .until(new BigDecimal("1100"))
                .percent(new BigDecimal("7.5"))
                .fromSecond(new BigDecimal("1100.1"))
                .untilSecond(new BigDecimal("2203.48"))
                .percentSecond(new BigDecimal("9"))
                .fromThird(new BigDecimal("2203.49"))
                .untilThird(new BigDecimal("3305.22"))
                .percentThird(new BigDecimal("12"))
                .fromFourth(new BigDecimal("3305.23"))
                .untilFourth(new BigDecimal("6433.57"))
                .percentFourth(new BigDecimal("14"))
                .isCurrent(Boolean.TRUE)
                .build();

        withNegativeNumbers = Inss
                .builder()
                .year("2020")
                .until(new BigDecimal("-1100"))
                .percent(new BigDecimal("-7.5"))
                .fromSecond(new BigDecimal("-1100.1"))
                .untilSecond(new BigDecimal("-2203.48"))
                .percentSecond(new BigDecimal("-9"))
                .fromThird(new BigDecimal("-2203.49"))
                .untilThird(new BigDecimal("-3305.22"))
                .percentThird(new BigDecimal("-12"))
                .fromFourth(new BigDecimal("-3305.23"))
                .untilFourth(new BigDecimal("-6433.57"))
                .percentFourth(new BigDecimal("-14"))
                .isCurrent(Boolean.TRUE)
                .build();

        withBigNumbers = Inss
                .builder()
                .year("2020")
                .until(new BigDecimal("9999999999999999.99"))
                .percent(new BigDecimal("9999.99"))
                .fromSecond(new BigDecimal("9999999999999999.99"))
                .untilSecond(new BigDecimal("9999999999999999.99"))
                .percentSecond(new BigDecimal("9999.99"))
                .fromThird(new BigDecimal("9999999999999999.99"))
                .untilThird(new BigDecimal("9999999999999999.99"))
                .percentThird(new BigDecimal("9999.99"))
                .fromFourth(new BigDecimal("9999999999999999.99"))
                .untilFourth(new BigDecimal("9999999999999999.99"))
                .percentFourth(new BigDecimal("9999.99"))
                .isCurrent(Boolean.TRUE)
                .build();

        nullable = Inss.builder().build();

    }

    @Test
    void it_should_create_when_valid() {

        service.execute(inss);

        verify(repository, times(1)).save(captor.capture());

        assertEquals(captor.getValue(), inss);

    }

    @Test
    void it_not_should_create_when_nullable() {

        assertNull(service.execute(nullable));

        verify(repository, times(1)).save(captor.capture());

        assertEquals(captor.getValue(), nullable);

    }

    @Test
    void it_not_should_create_when_negative_values() {
        assertNull(service.execute(withNegativeNumbers));
    }

    @Test
    void it_not_should_create_when_big_numbers() {
        assertNull(service.execute(withBigNumbers));
    }

}