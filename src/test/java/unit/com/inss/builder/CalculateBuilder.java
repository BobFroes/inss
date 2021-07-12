package com.inss.builder;

import com.google.gson.Gson;
import com.inss.http.request.CalculateRequest;
import com.inss.http.request.EmployeeRequest;
import com.inss.http.response.CalculatedResponse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

public class CalculateBuilder {

    public CalculateRequest calculateRequest() {
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

    public CalculatedResponse calculateResponse() {
        var json =  "{\"employees\": [{\"id\": \"fa07de98-1d78-4b8a-9fb2-0308474d3c35\", \"salary\": 1100, \"discount\": 82.50, \"percent\": 7.50}," +
                "{\"id\": \"7c1e1d02-0a0b-41c7-b5f1-929ec01e04d7\", \"salary\": 2000, \"discount\": 180.00, \"percent\": 9.00}," +
                "{\"id\": \"df32e121-03a7-4af4-b5c5-02ffc08b3db5\", \"salary\": 3000, \"discount\": 360.00, \"percent\": 12.00}," +
                "{\"id\": \"048fe759-02ba-4e25-b19f-04c4c882d4d2\", \"salary\": 7000, \"discount\": 980.00, \"percent\": 14.00}]}";

        return new Gson().fromJson(json, CalculatedResponse.class);
    }

}
