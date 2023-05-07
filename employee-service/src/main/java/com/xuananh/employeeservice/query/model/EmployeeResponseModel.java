package com.xuananh.employeeservice.query.model;

import com.xuananh.employeeservice.command.data.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class EmployeeResponseModel {
    private String employeeId;
    private String firstName;
    private String lastName;
    private String kin;
    private Boolean isDisciplined;

    public EmployeeResponseModel(Employee employee) {
        BeanUtils.copyProperties(employee, this);
    }
}
