package com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Employee {
    int id;
    String name;
    int departmentId;
    LocalDate hireDate;
}
