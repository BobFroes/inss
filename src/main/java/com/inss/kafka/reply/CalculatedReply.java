package com.inss.kafka.reply;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CalculatedReply {

    private String year;
    private List<EmployeeReply> employees;

}
