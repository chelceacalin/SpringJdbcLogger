package com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.dto.employee;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeAdd {
    String name;
    int departmentId;
    LocalDate hireDate;
}
