package com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.service;

import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.dto.employee.EmployeeAdd;
import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.dto.employee.EmployeeAdded;

import java.util.List;

public interface EmployeeService {

    EmployeeAdded addEmployee(EmployeeAdd employeeAdd);

    List<EmployeeAdded> findALl();

    List<EmployeeAdded> addEmployees(List<EmployeeAdd> employeeAdds);
}
