package com.xuananh.employeeservice.query.queries;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class GetEmployeeQuery {
    private String employeeId;
}
