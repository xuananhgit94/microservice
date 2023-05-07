package com.xuananh.employeeservice.command.event;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDeleteEvent {
    private String employeeId;
}
