package com.xuananh.employeeservice.query.controller;

import com.xuananh.employeeservice.command.data.Employee;
import com.xuananh.employeeservice.query.model.EmployeeResponseModel;
import com.xuananh.employeeservice.query.queries.GetAllEmployeesQuery;
import com.xuananh.employeeservice.query.queries.GetEmployeeQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeQueryController {
    @Autowired
    private QueryGateway queryGateway;

    @GetMapping("/{employeeId}")
    public EmployeeResponseModel findById(@PathVariable String employeeId) {
        GetEmployeeQuery getEmployeeQuery = new GetEmployeeQuery(employeeId);
        return queryGateway.query(getEmployeeQuery, ResponseTypes.instanceOf(EmployeeResponseModel.class)).join();
    }

    @GetMapping
    public List<EmployeeResponseModel> findAll() {
        return queryGateway.query(new GetAllEmployeesQuery(), ResponseTypes.multipleInstancesOf(EmployeeResponseModel.class)).join();
    }

}
