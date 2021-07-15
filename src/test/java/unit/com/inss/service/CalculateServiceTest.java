package com.inss.service;

import com.inss.builder.Builder;
import com.inss.domain.Inss;
import com.inss.domain.Repository;
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
import java.math.RoundingMode;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalculateServiceTest {

    private CalculateRequest request;

    private Inss inss;

    @Mock
    private Repository repository;

    @InjectMocks
    private CalculateService service;

    @Captor
    private ArgumentCaptor<CalculatedResponse> captor;

    @BeforeEach
    public void setUp() {
        inss = new Builder().create().get();
        request = CalculateRequest.builder().year("2021").build();
    }

    @Test
    void it_should_calculate_when_is_between_first_limit() {

        request.setEmployees(Collections.singletonList(
                EmployeeRequest.builder()
                        .id(UUID.randomUUID())
                        .salary(new BigDecimal("1000"))
                        .build()
        ));

        when(repository.findByYear(any())).thenReturn(Optional.of(inss));

        var response = service.execute(request);

        verify(repository, times(1)).findByYear(request.getYear());

        assertEquals("7.5%", response.getEmployees().get(0).getPercent());
    }

    @Test
    void it_should_calculate_when_is_equal_first_limit() {

        request.setEmployees(Collections.singletonList(
                EmployeeRequest.builder()
                        .id(UUID.randomUUID())
                        .salary(new BigDecimal("1100"))
                        .build()
        ));

        when(repository.findByYear(any())).thenReturn(Optional.of(inss));

        var response = service.execute(request);

        verify(repository, times(1)).findByYear(request.getYear());

        assertEquals("7.5%", response.getEmployees().get(0).getPercent());
    }

    @Test
    void it_should_calculate_when_is_between_second_limit() {

        request.setEmployees(Collections.singletonList(
                EmployeeRequest.builder()
                        .id(UUID.randomUUID())
                        .salary(new BigDecimal("2000"))
                        .build()
        ));

        when(repository.findByYear(any())).thenReturn(Optional.of(inss));

        var response = service.execute(request);

        verify(repository, times(1)).findByYear(request.getYear());

        assertEquals(new BigDecimal(163.50).setScale(2, RoundingMode.HALF_EVEN)
                , response.getEmployees().get(0).getDiscount());
        assertEquals("8.18%", response.getEmployees().get(0).getPercent());
    }

    @Test
    void it_should_calculate_when_is_equal_from_second_limit() {

        request.setEmployees(Collections.singletonList(
                EmployeeRequest.builder()
                        .id(UUID.randomUUID())
                        .salary(new BigDecimal("1100.01"))
                        .build()
        ));

        when(repository.findByYear(any())).thenReturn(Optional.of(inss));

        var response = service.execute(request);

        verify(repository, times(1)).findByYear(request.getYear());

        assertEquals(new BigDecimal(82.50).setScale(2, RoundingMode.HALF_EVEN)
                , response.getEmployees().get(0).getDiscount());
        assertEquals("7.50%", response.getEmployees().get(0).getPercent());
    }

    @Test
    void it_should_calculate_when_is_equal_until_second_limit() {

        request.setEmployees(Collections.singletonList(
                EmployeeRequest.builder()
                        .id(UUID.randomUUID())
                        .salary(new BigDecimal("2203.48"))
                        .build()
        ));

        when(repository.findByYear(any())).thenReturn(Optional.of(inss));

        var response = service.execute(request);

        verify(repository, times(1)).findByYear(request.getYear());

        assertEquals(new BigDecimal(181.81).setScale(2, RoundingMode.HALF_EVEN)
                , response.getEmployees().get(0).getDiscount());
        assertEquals("8.25%", response.getEmployees().get(0).getPercent());
    }

    @Test
    void it_should_calculate_when_is_between_third_limit() {

        request.setEmployees(Collections.singletonList(
                EmployeeRequest.builder()
                        .id(UUID.randomUUID())
                        .salary(new BigDecimal("3000"))
                        .build()
        ));

        when(repository.findByYear(any())).thenReturn(Optional.of(inss));

        var response = service.execute(request);

        verify(repository, times(1)).findByYear(request.getYear());

        assertEquals(new BigDecimal(277.39).setScale(2, RoundingMode.HALF_EVEN)
                , response.getEmployees().get(0).getDiscount());
        assertEquals("9.25%", response.getEmployees().get(0).getPercent());
    }

    @Test
    void it_should_calculate_when_is_equal_from_third_limit() {

        request.setEmployees(Collections.singletonList(
                EmployeeRequest.builder()
                        .id(UUID.randomUUID())
                        .salary(new BigDecimal("2203.49"))
                        .build()
        ));

        when(repository.findByYear(any())).thenReturn(Optional.of(inss));

        var response = service.execute(request);

        verify(repository, times(1)).findByYear(request.getYear());

        assertEquals(new BigDecimal(181.81).setScale(2, RoundingMode.HALF_EVEN)
                , response.getEmployees().get(0).getDiscount());
        assertEquals("8.25%", response.getEmployees().get(0).getPercent());
    }

    @Test
    void it_should_calculate_when_is_equal_until_third_limit() {

        request.setEmployees(Collections.singletonList(
                EmployeeRequest.builder()
                        .id(UUID.randomUUID())
                        .salary(new BigDecimal("3305.22"))
                        .build()
        ));

        when(repository.findByYear(any())).thenReturn(Optional.of(inss));

        var response = service.execute(request);

        verify(repository, times(1)).findByYear(request.getYear());

        assertEquals(new BigDecimal(314.02).setScale(2, RoundingMode.HALF_EVEN)
                , response.getEmployees().get(0).getDiscount());
        assertEquals("9.50%", response.getEmployees().get(0).getPercent());
    }

    @Test
    void it_should_calculate_when_is_between_fourth_limit() {

        request.setEmployees(Collections.singletonList(
                EmployeeRequest.builder()
                        .id(UUID.randomUUID())
                        .salary(new BigDecimal("3500"))
                        .build()
        ));

        when(repository.findByYear(any())).thenReturn(Optional.of(inss));

        var response = service.execute(request);

        verify(repository, times(1)).findByYear(request.getYear());

        assertEquals(new BigDecimal(341.29).setScale(2, RoundingMode.HALF_EVEN)
                , response.getEmployees().get(0).getDiscount());
        assertEquals("9.75%", response.getEmployees().get(0).getPercent());
    }

    @Test
    void it_should_calculate_when_is_equal_from_fourth_limit() {

        request.setEmployees(Collections.singletonList(
                EmployeeRequest.builder()
                        .id(UUID.randomUUID())
                        .salary(new BigDecimal("3305.23"))
                        .build()
        ));

        when(repository.findByYear(any())).thenReturn(Optional.of(inss));

        var response = service.execute(request);

        verify(repository, times(1)).findByYear(request.getYear());

        assertEquals(new BigDecimal(314.02).setScale(2, RoundingMode.HALF_EVEN)
                , response.getEmployees().get(0).getDiscount());
        assertEquals("9.50%", response.getEmployees().get(0).getPercent());
    }

    @Test
    void it_should_calculate_when_is_equal_until_fourth_limit() {

        request.setEmployees(Collections.singletonList(
                EmployeeRequest.builder()
                        .id(UUID.randomUUID())
                        .salary(new BigDecimal("6433.57"))
                        .build()
        ));

        when(repository.findByYear(any())).thenReturn(Optional.of(inss));

        var response = service.execute(request);

        verify(repository, times(1)).findByYear(request.getYear());

        assertEquals(new BigDecimal(751.99).setScale(2, RoundingMode.HALF_EVEN)
                , response.getEmployees().get(0).getDiscount());
        assertEquals("11.69%", response.getEmployees().get(0).getPercent());
    }


    @Test
    void it_should_calculate_when_is_above_fourth_limit() {

        request.setEmployees(Collections.singletonList(
                EmployeeRequest.builder()
                        .id(UUID.randomUUID())
                        .salary(new BigDecimal("70000.00"))
                        .build()
        ));

        when(repository.findByYear(any())).thenReturn(Optional.of(inss));

        var response = service.execute(request);

        verify(repository, times(1)).findByYear(request.getYear());

        assertEquals(new BigDecimal(751.99).setScale(2, RoundingMode.HALF_EVEN)
                , response.getEmployees().get(0).getDiscount());
        assertEquals("TETO", response.getEmployees().get(0).getPercent());
    }

    @Test
    void it_should_throw_calculate_when_not_found() {

        when(repository.findByYear(any())).thenReturn(Optional.empty());

        assertThrows(InssNotFoundException.class, () -> service.execute(request));

    }
}