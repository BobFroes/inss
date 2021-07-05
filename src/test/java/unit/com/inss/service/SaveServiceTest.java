package com.inss.service;

import com.inss.builder.InssBuilder;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SaveServiceTest {

    private InssBuilder builder;

    @Mock
    private InssRepository repository;

    @InjectMocks
    private SaveService service;

    @Captor
    private ArgumentCaptor<Inss> captor;

    @BeforeEach
    public void setUp() {
        builder = new InssBuilder();
    }

    @Test
    void it_should_create_when_valid() {
        Inss inss = builder.get();

        service.execute(inss);

        verify(repository, times(1)).save(captor.capture());

        assertEquals(captor.getValue(),inss);

    }

    @Test
    void it_not_should_create_when_nullable() {

        assertNull(service.execute(builder.nullable()));

        verify(repository, times(1)).save(captor.capture());

        assertEquals(captor.getValue(), builder.nullable());

    }

    @Test
    void it_not_should_create_when_negative_values() {
        assertNull(service.execute(builder.withNegativeNumbers()));
    }

    @Test
    void it_not_should_create_when_big_numbers() {
        assertNull(service.execute(builder.withBigNumbers()));
    }

}