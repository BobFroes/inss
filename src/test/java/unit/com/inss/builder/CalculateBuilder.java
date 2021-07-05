package com.inss.builder;

import com.inss.http.request.CalculateRequest;
import com.inss.http.request.EmployeeRequest;

import java.math.BigDecimal;
import java.util.ArrayList;

public class CalculateBuilder {

    public CalculateRequest withEmployees() {
        var employees = new ArrayList<EmployeeRequest>();
        employees.add(EmployeeRequest.builder().salary(new BigDecimal(1100)).build());
        employees.add(EmployeeRequest.builder().salary(new BigDecimal(2000)).build());
        employees.add(EmployeeRequest.builder().salary(new BigDecimal(3000)).build());
        employees.add(EmployeeRequest.builder().salary(new BigDecimal(7000)).build());

        return CalculateRequest
                .builder()
                .employees(employees)
                .build();
    }

}
