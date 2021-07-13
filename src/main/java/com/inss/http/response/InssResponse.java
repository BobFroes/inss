package com.inss.http.response;


import com.inss.domain.Inss;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class InssResponse {
    private UUID id;
    private String year;
    private BigDecimal until;
    private BigDecimal percent;
    private BigDecimal fromSecond;
    private BigDecimal untilSecond;
    private BigDecimal percentSecond;
    private BigDecimal fromThird;
    private BigDecimal untilThird;
    private BigDecimal percentThird;
    private BigDecimal fromFourth;
    private BigDecimal untilFourth;
    private BigDecimal percentFourth;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public static InssResponse from(Inss inss) {
        return InssResponse.builder()
                .id(inss.getId())
                .year(inss.getYear())
                .until(inss.getUntil())
                .percent(inss.getPercent())
                .fromSecond(inss.getFromSecond())
                .untilSecond(inss.getUntilSecond())
                .percentSecond(inss.getPercentSecond())
                .fromThird(inss.getFromThird())
                .untilThird(inss.getUntilThird())
                .percentThird(inss.getPercentThird())
                .fromFourth(inss.getFromFourth())
                .untilFourth(inss.getUntilFourth())
                .percentFourth(inss.getPercentFourth())
                .createdAt(inss.getCreatedAt())
                .updatedAt(inss.getUpdatedAt())
                .build();
    }

}