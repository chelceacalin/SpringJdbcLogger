package com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.service;

import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.dto.DepartmentAdd;
import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.dto.DepartmentAdded;
import org.springframework.stereotype.Service;

@Service
public interface DepartmentService {

    DepartmentAdded addDepartment(DepartmentAdd departmentAdd);
}
