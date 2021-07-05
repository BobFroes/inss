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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeleteServiceTest {

    private InssBuilder builder;

    @Mock
    private InssRepository repository;

    @InjectMocks
    private DeleteService service;

    @Captor
    private ArgumentCaptor<Inss> captor;

    @BeforeEach
    public void setUp() {
        builder = new InssBuilder();
    }

    @Test
    void it_should_delete_when_success() {

        Inss inss = builder.get();

        when(repository.findById(any())).thenReturn(Optional.of(inss));

        service.execute(inss.getId());

        verify(repository, times(1)).save(captor.capture());

        assertEquals(captor.getValue(), inss);

    }

    @Test
    void it_should_throw_not_found() {

        Inss inss = builder.get();

        when(repository.findById(any())).thenReturn(Optional.empty());

        service.execute(inss.getId());

        verify(repository, times(1)).findById(inss.getId());

    }
}