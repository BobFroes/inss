package com.inss.http.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class EmployeeResponse {

    private UUID id;
    private BigDecimal discount;
    private BigDecimal percent;

}
