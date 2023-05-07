package com.xuananh.employeeservice.query.projection;

import com.xuananh.employeeservice.command.data.Employee;
import com.xuananh.employeeservice.command.data.EmployeeRepository;
import com.xuananh.employeeservice.query.model.EmployeeResponseModel;
import com.xuananh.employeeservice.query.queries.GetAllEmployeesQuery;
import com.xuananh.employeeservice.query.queries.GetEmployeeQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeProjection {
    @Autowired
    private EmployeeRepository employeeRepository;

    @QueryHandler
    public EmployeeResponseModel handle(GetEmployeeQuery query) {
        Employee employee = employeeRepository.findById(query.getEmployeeId()).orElseThrow();
        return new EmployeeResponseModel(employee);
    }

    @QueryHandler
    public List<EmployeeResponseModel> handle(GetAllEmployeesQuery query) {
        return employeeRepository.findAll().stream().map(EmployeeResponseModel::new).collect(Collectors.toList());
    }
}
