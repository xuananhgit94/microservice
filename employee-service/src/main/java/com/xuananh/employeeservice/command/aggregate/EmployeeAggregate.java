package com.xuananh.employeeservice.command.aggregate;

import com.xuananh.employeeservice.command.command.CreateEmployeeCommand;
import com.xuananh.employeeservice.command.command.DeleteEmployeeCommand;
import com.xuananh.employeeservice.command.command.UpdateEmployeeCommand;
import com.xuananh.employeeservice.command.event.EmployeeCreateEvent;
import com.xuananh.employeeservice.command.event.EmployeeDeleteEvent;
import com.xuananh.employeeservice.command.event.EmployeeUpdateEvent;
import lombok.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Aggregate
public class EmployeeAggregate {
    @AggregateIdentifier
    private String employeeId;
    private String firstName;
    private String lastName;
    private String kin;
    private Boolean isDisciplined;

    @CommandHandler
    public EmployeeAggregate(CreateEmployeeCommand command) {
        EmployeeCreateEvent event = new EmployeeCreateEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void on(UpdateEmployeeCommand command) {
        EmployeeUpdateEvent event = new EmployeeUpdateEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void on(DeleteEmployeeCommand command) {
        EmployeeDeleteEvent event = new EmployeeDeleteEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }
    @EventSourcingHandler
    public void on(EmployeeCreateEvent event) {
        BeanUtils.copyProperties(event, this);
    }
    @EventSourcingHandler
    public void on(EmployeeUpdateEvent event) {
        BeanUtils.copyProperties(event, this);
    }
    @EventSourcingHandler
    public void on(EmployeeDeleteEvent event) {
        this.employeeId = event.getEmployeeId();
    }
}
