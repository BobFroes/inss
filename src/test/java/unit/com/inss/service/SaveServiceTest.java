package com.inss.service;

import com.inss.builder.Builder;
import com.inss.domain.Inss;
import com.inss.domain.Repository;
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

    private Builder builder;

    @Mock
    private Repository repository;

    @InjectMocks
    private SaveService service;

    @Captor
    private ArgumentCaptor<Inss> captor;

    @BeforeEach
    public void setUp() {
        builder = new Builder();
    }

    @Test
    void it_should_create_when_valid() {
        Inss inss = builder.create().get();

        service.execute(inss);

        verify(repository, times(1)).save(captor.capture());

        assertEquals(captor.getValue(),inss);

    }

    @Test
    void it_not_should_create_when_nullable() {

        assertNull(service.execute(null));

        verify(repository, times(1)).save(captor.capture());

        assertEquals(captor.getValue(),null);

    }

    @Test
    void it_not_should_create_when_negative_values() {
        assertNull(service.execute(builder.withNegativeNumbers().get()));
    }

    @Test
    void it_not_should_create_when_big_numbers() {
        assertNull(service.execute(builder.withBigNumbers().get()));
    }

}