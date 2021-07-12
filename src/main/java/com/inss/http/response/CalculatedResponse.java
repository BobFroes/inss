package com.inss.http.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CalculatedResponse {

    private List<EmployeeResponse> employees;

}
