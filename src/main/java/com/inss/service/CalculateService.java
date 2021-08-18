package com.inss.service;

import com.inss.domain.Repository;
import com.inss.exception.InssNotFoundException;
import com.inss.kafka.request.CalculateRequest;
import com.inss.kafka.response.CalculatedResponse;
import com.inss.kafka.response.EmployeeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;

@Service
public class CalculateService {

    @Autowired
    private Repository repository;

    public CalculatedResponse execute(CalculateRequest request) {
        var calculateResponse = CalculatedResponse.builder().year(request.getYear())
                .employees(new ArrayList<>()).build();

        repository.findByYear(request.getYear()).ifPresentOrElse(
                inss -> {
                    var firstTrack = inss.getUntil().multiply(inss.getPercent().divide(new BigDecimal(100))).setScale(2);
                    request.getEmployees().forEach(employee -> {
                        var employeeResponse = EmployeeResponse.builder().build();
                        var secondTrack = new BigDecimal(0);
                        var thirdTrack = new BigDecimal(0);
                        var fourthTrack = new BigDecimal(0);
                        employeeResponse.setId(employee.getId());
                        employeeResponse.setSalary(employee.getSalary());
                        // Until R$ 1.100 discounts 7.50%
                        if (employee.getSalary().compareTo(inss.getUntil()) == -1 ||
                                employee.getSalary().compareTo(inss.getUntil()) == 0
                        ) {

                            employeeResponse.setDiscount(employee.getSalary().multiply(inss.getPercent()
                                    .divide(new BigDecimal(100))).setScale(2, RoundingMode.HALF_EVEN));

                            employeeResponse.setPercent(inss.getPercent()+"%");

                        } // From R$ 1.100.01 until R$ 2.203.48
                        else if ((employee.getSalary().compareTo(inss.getFromSecond()) == 0 ||
                                employee.getSalary().compareTo(inss.getFromSecond()) == 1) &&
                                (employee.getSalary().compareTo(inss.getUntilSecond()) == 0 ||
                                        employee.getSalary().compareTo(inss.getUntilSecond()) == -1)) {

                            secondTrack = (employeeResponse.getSalary().subtract(inss.getUntil())).multiply(inss.getPercentSecond()
                                    .divide(new BigDecimal(100))).setScale(2, RoundingMode.HALF_EVEN);

                            employeeResponse.setDiscount(firstTrack.add(secondTrack));

                            employeeResponse.setPercent(employeeResponse.getDiscount().divide(employeeResponse.getSalary(), MathContext.DECIMAL128)
                                    .multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_EVEN)+"%");

                        } // From R$ 2.203,49 until R$ 3.305,22
                        else if ((employee.getSalary().compareTo(inss.getFromThird()) == 0 ||
                                employee.getSalary().compareTo(inss.getFromThird()) == 1) &&
                                (employee.getSalary().compareTo(inss.getUntilThird()) == 0 ||
                                        employee.getSalary().compareTo(inss.getUntilThird()) == -1)) {

                            secondTrack = (inss.getUntilSecond().subtract(inss.getUntil())).multiply(inss.getPercentSecond()
                                    .divide(new BigDecimal(100))).setScale(2, RoundingMode.HALF_EVEN);

                            thirdTrack = (employeeResponse.getSalary().subtract(inss.getUntilSecond())).multiply(inss.getPercentThird()
                                    .divide(new BigDecimal(100))).setScale(2, RoundingMode.HALF_EVEN);

                            employeeResponse.setDiscount(firstTrack.add(secondTrack).add(thirdTrack));

                            employeeResponse.setPercent(employeeResponse.getDiscount().divide(employeeResponse.getSalary(), MathContext.DECIMAL128)
                                    .multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_EVEN)+"%");

                        } // From R$ 3.305,22 until R$ 6.433,57
                        else if ((employee.getSalary().compareTo(inss.getFromFourth()) == 0 ||
                                employee.getSalary().compareTo(inss.getFromFourth()) == 1) &&
                                (employee.getSalary().compareTo(inss.getUntilFourth()) == 0 ||
                                        employee.getSalary().compareTo(inss.getUntilFourth()) == -1)) {

                            secondTrack = (inss.getUntilSecond().subtract(inss.getUntil())).multiply(inss.getPercentSecond()
                                    .divide(new BigDecimal(100))).setScale(2, RoundingMode.HALF_EVEN);

                            thirdTrack = (inss.getUntilThird().subtract(inss.getUntilSecond())).multiply(inss.getPercentThird()
                                    .divide(new BigDecimal(100))).setScale(2, RoundingMode.HALF_EVEN);

                            fourthTrack = (employeeResponse.getSalary().subtract(inss.getUntilThird())).multiply(inss.getPercentFourth()
                                    .divide(new BigDecimal(100))).setScale(2, RoundingMode.HALF_EVEN);

                            employeeResponse.setDiscount(firstTrack.add(secondTrack).add(thirdTrack).add(fourthTrack));

                            employeeResponse.setPercent(employeeResponse.getDiscount().divide(employeeResponse.getSalary(), MathContext.DECIMAL128)
                                    .multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_EVEN)+"%");

                        } else {

                            secondTrack = (inss.getUntilSecond().subtract(inss.getUntil())).multiply(inss.getPercentSecond()
                                    .divide(new BigDecimal(100))).setScale(2, RoundingMode.HALF_EVEN);

                            thirdTrack = (inss.getUntilThird().subtract(inss.getUntilSecond())).multiply(inss.getPercentThird()
                                    .divide(new BigDecimal(100))).setScale(2, RoundingMode.HALF_EVEN);

                            fourthTrack = (inss.getUntilFourth().subtract(inss.getUntilThird())).multiply(inss.getPercentFourth()
                                    .divide(new BigDecimal(100))).setScale(2, RoundingMode.HALF_EVEN);

                            employeeResponse.setDiscount(firstTrack.add(secondTrack).add(thirdTrack).add(fourthTrack));

                            employeeResponse.setPercent("TETO");
                        }

                        calculateResponse.getEmployees().add(employeeResponse);
                    });
                },
                () -> {
                    throw new InssNotFoundException();
                });

        return calculateResponse;
    }

}
