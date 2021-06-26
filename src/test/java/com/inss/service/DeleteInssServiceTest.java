package com.inss.service;

import com.inss.domain.Inss;
import com.inss.repository.InssRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeleteInssServiceTest {

    private Inss inss;

    @Mock
    private InssRepository repository;

    @InjectMocks
    private DeleteInssService service;

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

    }


    @Test
    void it_should_delete_when_success() {

        when(repository.findById(any())).thenReturn(Optional.of(inss));

        service.execute(inss.getId());

        verify(repository, times(1)).save(captor.capture());

        assertEquals(captor.getValue(), inss);

    }

    @Test
    void it_should_throw_not_found() {

        when(repository.findById(any())).thenReturn(Optional.empty());

        service.execute(inss.getId());

        verify(repository, times(1)).findById(inss.getId());

    }
}