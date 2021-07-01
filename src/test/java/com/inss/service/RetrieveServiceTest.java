package com.inss.service;

import com.inss.domain.Inss;
import com.inss.domain.InssRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RetrieveServiceTest {

    private Inss inss;

    @Mock
    private InssRepository repository;

    @InjectMocks
    private RetrieveService service;

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

    }


    @Test
    void it_should_retrieve_when_success() {

        when(repository.findById(any())).thenReturn(Optional.of(inss));

        Optional<Inss> optional = service.execute(inss.getId());

        verify(repository, times(1)).findById(inss.getId());

        assertNotNull(optional);

    }

    @Test
    void it_should_throw_not_found() {

        when(repository.findById(any())).thenReturn(null);

        Optional<Inss> optional = service.execute(inss.getId());

        verify(repository, times(1)).findById(inss.getId());

        assertNull(optional);

    }

}