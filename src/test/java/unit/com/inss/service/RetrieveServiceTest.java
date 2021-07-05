package com.inss.service;

import com.inss.builder.InssBuilder;
import com.inss.domain.Inss;
import com.inss.domain.InssRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RetrieveServiceTest {

    private InssBuilder builder;

    @Mock
    private InssRepository repository;

    @InjectMocks
    private RetrieveService service;

    @BeforeEach
    public void setUp() {
        builder = new InssBuilder();
    }

    @Test
    void it_should_retrieve_when_success() {
        Inss inss = builder.get();

        when(repository.findById(any())).thenReturn(Optional.of(inss));

        Optional<Inss> optional = service.execute(inss.getId());

        verify(repository, times(1)).findById(inss.getId());

        assertNotNull(optional);

    }

    @Test
    void it_should_throw_not_found() {
        Inss inss = builder.get();

        when(repository.findById(any())).thenReturn(null);

        Optional<Inss> optional = service.execute(inss.getId());

        verify(repository, times(1)).findById(inss.getId());

        assertNull(optional);

    }

}