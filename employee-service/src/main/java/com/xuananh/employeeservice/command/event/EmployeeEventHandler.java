package com.xuananh.employeeservice.command.event;

import com.xuananh.employeeservice.command.data.Employee;
import com.xuananh.employeeservice.command.data.EmployeeRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeEventHandler {
    @Autowired
    private EmployeeRepository employeeRepository;

    @EventHandler
    public void on(EmployeeCreateEvent event) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(event, employee);
        System.out.println(employee);
        employeeRepository.save(employee);
    }
    @EventHandler
    public void on(EmployeeUpdateEvent event) {
        Employee employee = employeeRepository.findById(event.getEmployeeId()).orElseThrow();
        employee.setFirstName(event.getFirstName());
        employee.setLastName(employee.getLastName());
        employee.setKin(event.getKin());
        employee.setIsDisciplined(event.getIsDisciplined());
        employeeRepository.save(employee);
    }
    @EventHandler
    public void on(EmployeeDeleteEvent event) {
        employeeRepository.deleteById(event.getEmployeeId());
    }
}
