package com.inss.service;

import com.inss.builder.Builder;
import com.inss.domain.Inss;
import com.inss.domain.InssRepository;
import com.inss.exception.InssNotFoundException;
import com.inss.http.request.CalculateRequest;
import com.inss.http.request.EmployeeRequest;
import com.inss.http.response.CalculatedResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalculateServiceTest {

    private CalculateRequest request;

    private Inss inss;

    @Mock
    private InssRepository repository;

    @InjectMocks
    private CalculateService service;

    @Captor
    private ArgumentCaptor<CalculatedResponse> captor;

    @BeforeEach
    public void setUp() {
        inss = new Builder().create().get();
        request = CalculateRequest.builder().build();
    }

    @Test
    void it_should_calculate_when_is_between_first_limit() {

        request.setEmployees(Collections.singletonList(
                EmployeeRequest.builder()
                        .id(UUID.randomUUID())
                        .salary(new BigDecimal("1000"))
                        .build()
        ));

        when(repository.findByIsCurrentTrue()).thenReturn(Optional.of(inss));

        var response = service.execute(request);

        verify(repository, times(1)).findByIsCurrentTrue();

        assertEquals(inss.getPercent(), response.getEmployees().get(0).getPercent());
    }

    @Test
    void it_should_calculate_when_is_equal_first_limit() {

        request.setEmployees(Collections.singletonList(
                EmployeeRequest.builder()
                        .id(UUID.randomUUID())
                        .salary(new BigDecimal("1100"))
                        .build()
        ));

        when(repository.findByIsCurrentTrue()).thenReturn(Optional.of(inss));

        var response = service.execute(request);

        verify(repository, times(1)).findByIsCurrentTrue();

        assertEquals(inss.getPercent(), response.getEmployees().get(0).getPercent());
    }

    @Test
    void it_should_calculate_when_is_between_second_limit() {

        request.setEmployees(Collections.singletonList(
                EmployeeRequest.builder()
                        .id(UUID.randomUUID())
                        .salary(new BigDecimal("2000"))
                        .build()
        ));

        when(repository.findByIsCurrentTrue()).thenReturn(Optional.of(inss));

        var response = service.execute(request);

        verify(repository, times(1)).findByIsCurrentTrue();

        assertEquals(inss.getPercentSecond(), response.getEmployees().get(0).getPercent());
    }

    @Test
    void it_should_calculate_when_is_equal_from_second_limit() {

        request.setEmployees(Collections.singletonList(
                EmployeeRequest.builder()
                        .id(UUID.randomUUID())
                        .salary(new BigDecimal("1100.01"))
                        .build()
        ));

        when(repository.findByIsCurrentTrue()).thenReturn(Optional.of(inss));

        var response = service.execute(request);

        verify(repository, times(1)).findByIsCurrentTrue();

        assertEquals(inss.getPercentSecond(), response.getEmployees().get(0).getPercent());
    }

    @Test
    void it_should_calculate_when_is_equal_until_second_limit() {

        request.setEmployees(Collections.singletonList(
                EmployeeRequest.builder()
                        .id(UUID.randomUUID())
                        .salary(new BigDecimal("2203.48"))
                        .build()
        ));

        when(repository.findByIsCurrentTrue()).thenReturn(Optional.of(inss));

        var response = service.execute(request);

        verify(repository, times(1)).findByIsCurrentTrue();

        assertEquals(inss.getPercentSecond(), response.getEmployees().get(0).getPercent());
    }

    @Test
    void it_should_calculate_when_is_between_third_limit() {

        request.setEmployees(Collections.singletonList(
                EmployeeRequest.builder()
                        .id(UUID.randomUUID())
                        .salary(new BigDecimal("3000"))
                        .build()
        ));

        when(repository.findByIsCurrentTrue()).thenReturn(Optional.of(inss));

        var response = service.execute(request);

        verify(repository, times(1)).findByIsCurrentTrue();

        assertEquals(inss.getPercentThird(), response.getEmployees().get(0).getPercent());
    }

    @Test
    void it_should_calculate_when_is_equal_from_third_limit() {

        request.setEmployees(Collections.singletonList(
                EmployeeRequest.builder()
                        .id(UUID.randomUUID())
                        .salary(new BigDecimal("2203.49"))
                        .build()
        ));

        when(repository.findByIsCurrentTrue()).thenReturn(Optional.of(inss));

        var response = service.execute(request);

        verify(repository, times(1)).findByIsCurrentTrue();

        assertEquals(inss.getPercentThird(), response.getEmployees().get(0).getPercent());
    }

    @Test
    void it_should_calculate_when_is_equal_until_third_limit() {

        request.setEmployees(Collections.singletonList(
                EmployeeRequest.builder()
                        .id(UUID.randomUUID())
                        .salary(new BigDecimal("3305.22"))
                        .build()
        ));

        when(repository.findByIsCurrentTrue()).thenReturn(Optional.of(inss));

        var response = service.execute(request);

        verify(repository, times(1)).findByIsCurrentTrue();

        assertEquals(inss.getPercentThird(), response.getEmployees().get(0).getPercent());
    }

    @Test
    void it_should_calculate_when_is_between_fourth_limit() {

        request.setEmployees(Collections.singletonList(
                EmployeeRequest.builder()
                        .id(UUID.randomUUID())
                        .salary(new BigDecimal("3500"))
                        .build()
        ));

        when(repository.findByIsCurrentTrue()).thenReturn(Optional.of(inss));

        var response = service.execute(request);

        verify(repository, times(1)).findByIsCurrentTrue();

        assertEquals(inss.getPercentFourth(), response.getEmployees().get(0).getPercent());
    }

    @Test
    void it_should_calculate_when_is_equal_from_fourth_limit() {

        request.setEmployees(Collections.singletonList(
                EmployeeRequest.builder()
                        .id(UUID.randomUUID())
                        .salary(new BigDecimal("3305.23"))
                        .build()
        ));

        when(repository.findByIsCurrentTrue()).thenReturn(Optional.of(inss));

        var response = service.execute(request);

        verify(repository, times(1)).findByIsCurrentTrue();

        assertEquals(inss.getPercentFourth(), response.getEmployees().get(0).getPercent());
    }

    @Test
    void it_should_calculate_when_is_above_fourth_limit() {

        request.setEmployees(Collections.singletonList(
                EmployeeRequest.builder()
                        .id(UUID.randomUUID())
                        .salary(new BigDecimal("70000.00"))
                        .build()
        ));

        when(repository.findByIsCurrentTrue()).thenReturn(Optional.of(inss));

        var response = service.execute(request);

        verify(repository, times(1)).findByIsCurrentTrue();

        assertEquals(inss.getPercentFourth(), response.getEmployees().get(0).getPercent());
    }

    @Test
    void it_should_throw_calculate_when_not_found() {

        when(repository.findByIsCurrentTrue()).thenReturn(Optional.empty());

        assertThrows(InssNotFoundException.class, () -> service.execute(request));

    }
}