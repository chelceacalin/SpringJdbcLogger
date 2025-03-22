package com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.mapper;

import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.common.MapperUtil;
import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.dto.employee.EmployeeAdded;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;

@Component
public class EmployeeAddedMapper extends MapperUtil implements RowMapper<EmployeeAdded> {
    @Override
    public EmployeeAdded mapRow(ResultSet rs, int rowNum) {
        EmployeeAdded employeeAdded = new EmployeeAdded();
        employeeAdded.setId(getInt(rs, "EmployeeId"));
        employeeAdded.setName(getString(rs, "EmployeeName"));
        employeeAdded.setDepartmentId(getInt(rs, "DepartmentId"));
        employeeAdded.setHireDate(getLocalDate(rs, "HireDate"));
        return employeeAdded;
    }
}
