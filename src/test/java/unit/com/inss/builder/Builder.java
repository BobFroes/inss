package com.inss.builder;

import com.inss.domain.Inss;
import com.inss.domain.Repository;
import com.inss.http.request.CalculateRequest;
import com.inss.http.request.EmployeeRequest;
import com.inss.http.response.CalculatedResponse;
import com.inss.http.response.EmployeeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

@Component
public class Builder {

    @Autowired
    private Repository repository;

    private Inss inss;

    public Builder create() {
        inss = Inss
                .builder()
                .id(UUID.randomUUID())
                .year("2020")
                .until(new BigDecimal("1100"))
                .percent(new BigDecimal("7.5"))
                .fromSecond(new BigDecimal("1100.01"))
                .untilSecond(new BigDecimal("2203.48"))
                .percentSecond(new BigDecimal("9"))
                .fromThird(new BigDecimal("2203.49"))
                .untilThird(new BigDecimal("3305.22"))
                .percentThird(new BigDecimal("12"))
                .fromFourth(new BigDecimal("3305.23"))
                .untilFourth(new BigDecimal("6433.57"))
                .percentFourth(new BigDecimal("14"))
                .isCurrent(Boolean.TRUE)
                .build();
        return this;
    }

    public Builder withNegativeNumbers() {
        inss = Inss
                .builder()
                .year("2020")
                .until(new BigDecimal("-1100"))
                .percent(new BigDecimal("-7.5"))
                .fromSecond(new BigDecimal("-1100.1"))
                .untilSecond(new BigDecimal("-2203.48"))
                .percentSecond(new BigDecimal("-9"))
                .fromThird(new BigDecimal("-2203.49"))
                .untilThird(new BigDecimal("-3305.22"))
                .percentThird(new BigDecimal("-12"))
                .fromFourth(new BigDecimal("-3305.23"))
                .untilFourth(new BigDecimal("-6433.57"))
                .percentFourth(new BigDecimal("-14"))
                .isCurrent(Boolean.TRUE)
                .build();
        return this;
    }

    public Builder withBigNumbers() {
        inss = Inss
                .builder()
                .year("2020")
                .until(new BigDecimal("9999999999999999.99"))
                .percent(new BigDecimal("9999.99"))
                .fromSecond(new BigDecimal("9999999999999999.99"))
                .untilSecond(new BigDecimal("9999999999999999.99"))
                .percentSecond(new BigDecimal("9999.99"))
                .fromThird(new BigDecimal("9999999999999999.99"))
                .untilThird(new BigDecimal("9999999999999999.99"))
                .percentThird(new BigDecimal("9999.99"))
                .fromFourth(new BigDecimal("9999999999999999.99"))
                .untilFourth(new BigDecimal("9999999999999999.99"))
                .percentFourth(new BigDecimal("9999.99"))
                .isCurrent(Boolean.TRUE)
                .build();

        return this;
    }

    public Builder save() {

        repository.save(create().get());

        return this;
    }

    public Inss get() {
        return inss;
    }

    public CalculateRequest calculateRequest() {

        var employees = new ArrayList<EmployeeRequest>();

        employees.add(EmployeeRequest.builder().id(UUID.fromString("fa07de98-1d78-4b8a-9fb2-0308474d3c35"))
                .salary(new BigDecimal(1100)).build());
        employees.add(EmployeeRequest.builder().id(UUID.fromString("7c1e1d02-0a0b-41c7-b5f1-929ec01e04d7"))
                .salary(new BigDecimal(2000)).build());
        employees.add(EmployeeRequest.builder().id(UUID.fromString("df32e121-03a7-4af4-b5c5-02ffc08b3db5"))
                .salary(new BigDecimal(3000)).build());
        employees.add(EmployeeRequest.builder().id(UUID.fromString("f048fe759-02ba-4e25-b19f-4c4c882d4d2"))
                .salary(new BigDecimal(7000)).build());

        return CalculateRequest.builder().employees(employees).build();

    }

    public CalculatedResponse calculatedResponse() {

        var employees = new ArrayList<EmployeeResponse>();

        employees.add(EmployeeResponse.builder().id(UUID.fromString("fa07de98-1d78-4b8a-9fb2-0308474d3c35"))
                .salary(new BigDecimal(1100))
                .discount(new BigDecimal(82.50).setScale(2))
                .percent(new BigDecimal(7.50).setScale(2)).build());
        employees.add(EmployeeResponse.builder().id(UUID.fromString("7c1e1d02-0a0b-41c7-b5f1-929ec01e04d7"))
                .salary(new BigDecimal(2000))
                .discount(new BigDecimal(180).setScale(2))
                .percent(new BigDecimal(9).setScale(2)).build());
        employees.add(EmployeeResponse.builder().id(UUID.fromString("df32e121-03a7-4af4-b5c5-02ffc08b3db5"))
                .salary(new BigDecimal(3000))
                .discount(new BigDecimal(360).setScale(2))
                .percent(new BigDecimal(12).setScale(2)).build());
        employees.add(EmployeeResponse.builder().id(UUID.fromString("f048fe759-02ba-4e25-b19f-4c4c882d4d2"))
                .salary(new BigDecimal(7000))
                .discount(new BigDecimal(980).setScale(2))
                .percent(new BigDecimal(14).setScale(2)).build());

        return CalculatedResponse.builder().employees(employees).build();

    }

}
