package com.xuananh.employeeservice.command.command;

import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeleteEmployeeCommand {
    @TargetAggregateIdentifier
    private String employeeId;
}
