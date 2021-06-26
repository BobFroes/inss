package com.inss.builder;

import com.inss.domain.Inss;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class InssBuilder {

    public InssBuilder() {
    }

    public Inss create() {
        return Inss
                .builder()
                .id(UUID.randomUUID())
                .year("2020")
                .until(new BigDecimal("1100"))
                .percent(new BigDecimal("7.5"))
                .fromSecond(new BigDecimal("1100.1"))
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
    }

    public Inss withNegativeNumbers() {
        return Inss
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
    }

    public Inss withBigNumbers() {
        return Inss
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
    }

    public Inss nullable() {
        return Inss.builder().build();
    }

}
