package com.inss.http.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CalculatedResponse {

    private String year;
    private List<EmployeeResponse> employees;

}
