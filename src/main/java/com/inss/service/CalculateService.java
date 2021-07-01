package com.inss.service;

import com.inss.domain.InssRepository;
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

        calculateResponse.setStartDate(request.getStartDate());
        calculateResponse.setEndDate(request.getEndDate());

        var inss = repository.findByIsCurrentTrue();

        if (inss.isPresent()) {
            request.getEmployees().forEach(employee -> {
                employeeResponse.setId(employee.getId());
                if (employee.getSalary().compareTo(inss.get().getUntil()) == 0 ||
                        employee.getSalary().compareTo(inss.get().getUntil()) == -1) {
                    employeeResponse.setDiscount(employee.getSalary().multiply(inss.get().getPercent().divide(new BigDecimal(100))));
                    employeeResponse.setPercent(inss.get().getPercent());
                } else if ((employee.getSalary().compareTo(inss.get().getFromSecond()) == 0 ||
                        employee.getSalary().compareTo(inss.get().getFromSecond()) == 1) &&
                        (employee.getSalary().compareTo(inss.get().getUntilSecond()) == 0 ||
                                employee.getSalary().compareTo(inss.get().getUntilSecond()) == -1)) {
                    employeeResponse.setDiscount(employee.getSalary().multiply(inss.get().getPercentSecond().divide(new BigDecimal(100))));
                    employeeResponse.setPercent(inss.get().getPercentSecond());
                } else if ((employee.getSalary().compareTo(inss.get().getFromThird()) == 0 ||
                        employee.getSalary().compareTo(inss.get().getFromThird()) == 1) &&
                        (employee.getSalary().compareTo(inss.get().getUntilThird()) == 0 ||
                                employee.getSalary().compareTo(inss.get().getUntilThird()) == -1)) {
                    employeeResponse.setDiscount(employee.getSalary().multiply(inss.get().getPercentThird().divide(new BigDecimal(100))));
                    employeeResponse.setPercent(inss.get().getPercentThird());
                } else if ((employee.getSalary().compareTo(inss.get().getFromFourth()) == 0 ||
                        employee.getSalary().compareTo(inss.get().getFromFourth()) == 1) &&
                        (employee.getSalary().compareTo(inss.get().getUntilFourth()) == 0 ||
                                employee.getSalary().compareTo(inss.get().getUntilFourth()) == -1)) {
                    employeeResponse.setDiscount(employee.getSalary().multiply(inss.get().getPercentFourth().divide(new BigDecimal(100))));
                    employeeResponse.setPercent(inss.get().getPercentFourth());
                }
                calculateResponse.getEmployees().add(employeeResponse);
            });
        }

        return calculateResponse;
    }


}
