package com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.service.impl;

import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.dto.employee.EmployeeAdd;
import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.dto.employee.EmployeeAdded;
import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.repository.EmployeeRepository;
import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    final EmployeeRepository employeeRepository;

    @Override
    public EmployeeAdded addEmployee(EmployeeAdd employeeAdd) {
        return employeeRepository.addEmployee(employeeAdd);
    }

    @Override
    public List<EmployeeAdded> findALl() {
        return employeeRepository.findAll();
    }

    @Override
    public List<EmployeeAdded> addEmployees(List<EmployeeAdd> employeeAdds) {
        return employeeRepository.addEmployees(employeeAdds);
    }
}
