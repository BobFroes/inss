package com.inss.http.request;

import java.math.BigDecimal;
import java.util.UUID;

import com.inss.domain.Inss;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InssRequest {

    @NotNull
    private String year;

    @NotNull
    private BigDecimal until;

    @NotNull
    private BigDecimal percent;

    @NotNull
    private BigDecimal fromSecond;

    @NotNull
    private BigDecimal untilSecond;

    @NotNull
    private BigDecimal percentSecond;

    @NotNull
    private BigDecimal fromThird;

    @NotNull
    private BigDecimal untilThird;

    @NotNull
    private BigDecimal percentThird;

    @NotNull
    private BigDecimal fromFourth;

    @NotNull
    private BigDecimal untilFourth;

    @NotNull
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