package com.inss.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "inss")
@EntityListeners(AuditingEntityListener.class)
public class Inss {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    private String year;

    private BigDecimal until;

    private BigDecimal percent;

    @Column(nullable = false, name = "from_second", precision = 2)
    private BigDecimal fromSecond;

    @Column(nullable = false, name = "until_second", precision = 2)
    private BigDecimal untilSecond;

    @Column(nullable = false, name = "percent_second", precision = 2)
    private BigDecimal percentSecond;

    @Column(nullable = false, name = "from_third", precision = 2)
    private BigDecimal fromThird;

    @Column(nullable = false, name = "until_third", precision = 2)
    private BigDecimal untilThird;

    @Column(nullable = false, name = "percent_third", precision = 2)
    private BigDecimal percentThird;

    @Column(nullable = false, name = "from_fourth", precision = 2)
    private BigDecimal fromFourth;

    @Column(nullable = false, name = "until_fourth", precision = 2)
    private BigDecimal untilFourth;

    @Column(nullable = false, name = "percent_fourth", precision = 2)
    private BigDecimal percentFourth;

    @CreatedDate
    @Column(nullable = false, updatable = false, name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false, name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

}