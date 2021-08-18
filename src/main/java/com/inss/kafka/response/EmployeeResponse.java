package com.inss.kafka.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class EmployeeResponse {

    private UUID id;
    private BigDecimal salary;
    private BigDecimal discount;
    private String percent;

}
