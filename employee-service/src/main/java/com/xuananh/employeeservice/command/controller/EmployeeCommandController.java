package com.xuananh.employeeservice.command.controller;

import com.xuananh.employeeservice.command.command.CreateEmployeeCommand;
import com.xuananh.employeeservice.command.command.DeleteEmployeeCommand;
import com.xuananh.employeeservice.command.command.UpdateEmployeeCommand;
import com.xuananh.employeeservice.command.model.EmployeeRequestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeCommandController {
    @Autowired
    private CommandGateway commandGateway;

    @PostMapping
    public String addNewEmployee(@RequestBody EmployeeRequestModel employeeRequestModel) {
        CreateEmployeeCommand createEmployeeCommand = CreateEmployeeCommand.builder()
                .employeeId(UUID.randomUUID().toString())
                .firstName(employeeRequestModel.getFirstName())
                .lastName(employeeRequestModel.getLastName())
                .kin(employeeRequestModel.getKin())
                .isDisciplined(false)
                .build();
        commandGateway.sendAndWait(createEmployeeCommand);
        return "added employee";
    }

    @PutMapping
    public String updateEmployee(@RequestBody EmployeeRequestModel employeeRequestModel) {
        UpdateEmployeeCommand updateEmployeeCommand = UpdateEmployeeCommand.builder()
                .employeeId(employeeRequestModel.getEmployeeId())
                .firstName(employeeRequestModel.getFirstName())
                .lastName(employeeRequestModel.getLastName())
                .kin(employeeRequestModel.getKin())
                .isDisciplined(employeeRequestModel.getIsDisciplined())
                .build();
        commandGateway.sendAndWait(updateEmployeeCommand);
        return "added employee";
    }

    @DeleteMapping("/{employeeId}")
    public String deleteEmployee(@PathVariable String employeeId) {
        DeleteEmployeeCommand deleteEmployeeCommand = new DeleteEmployeeCommand(employeeId);
        commandGateway.sendAndWait(deleteEmployeeCommand);
        return "deleted employee";
    }
}
