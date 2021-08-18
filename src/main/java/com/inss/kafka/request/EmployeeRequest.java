package com.inss.kafka.request;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class EmployeeRequest {

    private UUID id;
    private BigDecimal salary;

}
