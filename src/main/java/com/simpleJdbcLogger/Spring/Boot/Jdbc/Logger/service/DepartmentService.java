package com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.service;

import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.dto.department.DepartmentAdd;
import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.dto.department.DepartmentAdded;

public interface DepartmentService {

    DepartmentAdded addDepartment(DepartmentAdd departmentAdd);
}
