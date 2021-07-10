package com.inss.builder;

import com.inss.http.request.CalculateRequest;
import com.inss.http.request.EmployeeRequest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

public class CalculateBuilder {

    public CalculateRequest withEmployees() {
        var employees = new ArrayList<EmployeeRequest>();
        employees.add(EmployeeRequest.builder().id(UUID.fromString("fa07de98-1d78-4b8a-9fb2-0308474d3c35")).salary(new BigDecimal(1100)).build());
        employees.add(EmployeeRequest.builder().id(UUID.fromString("7c1e1d02-0a0b-41c7-b5f1-929ec01e04d7")).salary(new BigDecimal(2000)).build());
        employees.add(EmployeeRequest.builder().id(UUID.fromString("df32e121-03a7-4af4-b5c5-02ffc08b3db5")).salary(new BigDecimal(3000)).build());
        employees.add(EmployeeRequest.builder().id(UUID.fromString("f048fe759-02ba-4e25-b19f-4c4c882d4d2")).salary(new BigDecimal(7000)).build());

        return CalculateRequest
                .builder()
                .employees(employees)
                .build();
    }

}
