package com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.repository;

import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.dto.employee.EmployeeAdd;
import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.dto.employee.EmployeeAdded;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository {

    EmployeeAdded addEmployee(EmployeeAdd employeeAdd);

    List<EmployeeAdded> findAll();

    List<EmployeeAdded> addEmployees(List<EmployeeAdd> employeeAdds);
}
