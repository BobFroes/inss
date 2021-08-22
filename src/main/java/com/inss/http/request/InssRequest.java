package com.inss.http.request;

import java.math.BigDecimal;
import java.util.UUID;

import com.inss.domain.Inss;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InssRequest {

    @NotNull
    private String year;

    @NotNull
    @Positive
    private BigDecimal until;

    @NotNull
    @Positive
    private BigDecimal percent;

    @NotNull
    @Positive
    private BigDecimal fromSecond;

    @NotNull
    @Positive
    private BigDecimal untilSecond;

    @NotNull
    @Positive
    private BigDecimal percentSecond;

    @NotNull
    @Positive
    private BigDecimal fromThird;

    @NotNull
    @Positive
    private BigDecimal untilThird;

    @NotNull
    @Positive
    private BigDecimal percentThird;

    @NotNull
    @Positive
    private BigDecimal fromFourth;

    @NotNull
    @Positive
    private BigDecimal untilFourth;

    @NotNull
    @Positive
    private BigDecimal percentFourth;


    public static Inss from(InssRequest request) {
        return getInss(null, request);
    }

    public static Inss from(UUID id, InssRequest request) {
        return getInss(id, request);
    }

    private static Inss getInss(UUID id, InssRequest request) {
        return Inss.builder()
                .id(id)
                .year(request.getYear())
                .until(request.getUntil())
                .percent(request.getPercent())
                .fromSecond(request.getFromSecond())
                .untilSecond(request.getUntilSecond())
                .percentSecond(request.getPercentSecond())
                .fromThird(request.getFromThird())
                .untilThird(request.getUntilThird())
                .percentThird(request.getPercentThird())
                .fromFourth(request.getFromFourth())
                .untilFourth(request.getUntilFourth())
                .percentFourth(request.getPercentFourth())
                .build();
    }
}