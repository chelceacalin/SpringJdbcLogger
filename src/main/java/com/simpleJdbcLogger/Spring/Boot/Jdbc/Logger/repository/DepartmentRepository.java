package com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.repository;

import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.dto.DepartmentAdd;
import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.dto.DepartmentAdded;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository {

    DepartmentAdded addDepartment(DepartmentAdd dto);
}
