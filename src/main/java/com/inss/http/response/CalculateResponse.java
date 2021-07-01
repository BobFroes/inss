package com.inss.http.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class CalculateResponse {

    private LocalDate startDate;
    private LocalDate endDate;
    private List<EmployeeResponse> employees;

}
