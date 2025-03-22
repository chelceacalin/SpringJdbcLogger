package com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.dto.employee;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeAdded {
    int id;
    String name;
    int departmentId;
    LocalDate hireDate;
}
