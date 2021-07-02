package com.inss.service;

import com.inss.domain.InssRepository;
import com.inss.exception.InssNotFoundException;
import com.inss.http.request.CalculateRequest;
import com.inss.http.response.CalculateResponse;
import com.inss.http.response.EmployeeResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;

@Service
public class CalculateService {

    private final InssRepository repository;

    public CalculateService(InssRepository repository) {
        this.repository = repository;
    }

    public CalculateResponse execute(CalculateRequest request) {
        var calculateResponse = CalculateResponse.builder().employees(new ArrayList<>()).build();
        var employeeResponse = EmployeeResponse.builder().build();

        repository.findByIsCurrentTrue().ifPresentOrElse(
                (inss) -> {
                    request.getEmployees().forEach(employee -> {
                        employeeResponse.setId(employee.getId());
                        if (employee.getSalary().compareTo(inss.getUntil()) == 0 ||
                                employee.getSalary().compareTo(inss.getUntil()) == -1) {
                            employeeResponse.setDiscount(employee.getSalary().multiply(inss.getPercent().divide(new BigDecimal(100))));
                            employeeResponse.setPercent(inss.getPercent());
                        } else if ((employee.getSalary().compareTo(inss.getFromSecond()) == 0 ||
                                employee.getSalary().compareTo(inss.getFromSecond()) == 1) &&
                                (employee.getSalary().compareTo(inss.getUntilSecond()) == 0 ||
                                        employee.getSalary().compareTo(inss.getUntilSecond()) == -1)) {
                            employeeResponse.setDiscount(employee.getSalary().multiply(inss.getPercentSecond().divide(new BigDecimal(100))));
                            employeeResponse.setPercent(inss.getPercentSecond());
                        } else if ((employee.getSalary().compareTo(inss.getFromThird()) == 0 ||
                                employee.getSalary().compareTo(inss.getFromThird()) == 1) &&
                                (employee.getSalary().compareTo(inss.getUntilThird()) == 0 ||
                                        employee.getSalary().compareTo(inss.getUntilThird()) == -1)) {
                            employeeResponse.setDiscount(employee.getSalary().multiply(inss.getPercentThird().divide(new BigDecimal(100))));
                            employeeResponse.setPercent(inss.getPercentThird());
                        } else if ((employee.getSalary().compareTo(inss.getFromFourth()) == 0 ||
                                employee.getSalary().compareTo(inss.getFromFourth()) == 1)) {
                            employeeResponse.setDiscount(employee.getSalary().multiply(inss.getPercentFourth().divide(new BigDecimal(100))));
                            employeeResponse.setPercent(inss.getPercentFourth());
                        }
                        calculateResponse.getEmployees().add(employeeResponse);
                    });
                },
                () -> { throw new InssNotFoundException(); });

        return calculateResponse;
    }


}
