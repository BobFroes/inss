package com.inss.kafka.reply;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class EmployeeReply {

    private UUID id;
    private BigDecimal salary;
    private BigDecimal discount;
    private String percent;

}
