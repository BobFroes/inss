package com.inss.service;

import com.inss.builder.Builder;
import com.inss.domain.Inss;
import com.inss.domain.Repository;
import com.inss.exception.InssNotFoundException;
import com.inss.http.request.CalculateRequest;
import com.inss.http.request.EmployeeRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
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

    @BeforeEach
    public void setUp() {
        inss = new Builder().create().get();
        request = CalculateRequest.builder().year("2021").build();
    }

    @ParameterizedTest
    @ValueSource(strings = {"1000", "1100"})
    void it_should_calculate_when_is_between_first_limit(String number) {

        request.setEmployees(Collections.singletonList(
                EmployeeRequest.builder()
                        .id(UUID.randomUUID())
                        .salary(new BigDecimal(number))
                        .build()
        ));

        when(repository.findByYear(any())).thenReturn(Optional.of(inss));

        var response = service.execute(request);

        verify(repository, times(1)).findByYear(request.getYear());

        assertEquals("7.5%", response.getEmployees().get(0).getPercent());
    }

    @ParameterizedTest
    @CsvSource({"1100.01, 82.50, 7.50%", "2000, 163.50, 8.18%", "2203.48, 181.81, 8.25%"})
    void it_should_calculate_when_is_between_second_limit(String salary, String discount, String percent) {

        request.setEmployees(Collections.singletonList(
                EmployeeRequest.builder()
                        .id(UUID.randomUUID())
                        .salary(new BigDecimal(salary))
                        .build()
        ));

        when(repository.findByYear(any())).thenReturn(Optional.of(inss));

        var response = service.execute(request);

        verify(repository, times(1)).findByYear(request.getYear());

        assertEquals(new BigDecimal(discount).setScale(2, RoundingMode.HALF_EVEN)
                , response.getEmployees().get(0).getDiscount());
        assertEquals(percent, response.getEmployees().get(0).getPercent());
    }

    @ParameterizedTest
    @CsvSource({"2203.49, 181.81, 8.25%", "3000, 277.39, 9.25%", "3305.22, 314.02, 9.50%"})
    void it_should_calculate_when_is_between_third_limit(String salary, String discount, String percent) {

        request.setEmployees(Collections.singletonList(
                EmployeeRequest.builder()
                        .id(UUID.randomUUID())
                        .salary(new BigDecimal(salary))
                        .build()
        ));

        when(repository.findByYear(any())).thenReturn(Optional.of(inss));

        var response = service.execute(request);

        verify(repository, times(1)).findByYear(request.getYear());

        assertEquals(new BigDecimal(discount).setScale(2, RoundingMode.HALF_EVEN)
                , response.getEmployees().get(0).getDiscount());
        assertEquals(percent, response.getEmployees().get(0).getPercent());
    }

    @ParameterizedTest
    @CsvSource({"3305.23, 314.02, 9.50%", "6433.57, 751.99, 11.69%"})
    void it_should_calculate_when_is_between_fourth_limit(String salary, String discount, String percent) {

        request.setEmployees(Collections.singletonList(
                EmployeeRequest.builder()
                        .id(UUID.randomUUID())
                        .salary(new BigDecimal(salary))
                        .build()
        ));

        when(repository.findByYear(any())).thenReturn(Optional.of(inss));

        var response = service.execute(request);

        verify(repository, times(1)).findByYear(request.getYear());

        assertEquals(new BigDecimal(discount).setScale(2, RoundingMode.HALF_EVEN)
                , response.getEmployees().get(0).getDiscount());
        assertEquals(percent, response.getEmployees().get(0).getPercent());
    }

    @ParameterizedTest
    @CsvSource({"70000.00, 751.99, TETO", "10000, 751.99, TETO", "20000, 751.99, TETO"})
    void it_should_calculate_when_is_above_fourth_limit(String salary, String discount, String percent) {

        request.setEmployees(Collections.singletonList(
                EmployeeRequest.builder()
                        .id(UUID.randomUUID())
                        .salary(new BigDecimal(salary))
                        .build()
        ));

        when(repository.findByYear(any())).thenReturn(Optional.of(inss));

        var response = service.execute(request);

        verify(repository, times(1)).findByYear(request.getYear());

        assertEquals(new BigDecimal(discount).setScale(2, RoundingMode.HALF_EVEN)
                , response.getEmployees().get(0).getDiscount());
        assertEquals(percent, response.getEmployees().get(0).getPercent());
    }

    @Test
    void it_should_throw_calculate_when_not_found() {

        when(repository.findByYear(any())).thenReturn(Optional.empty());

        assertThrows(InssNotFoundException.class, () -> service.execute(request));

    }
}