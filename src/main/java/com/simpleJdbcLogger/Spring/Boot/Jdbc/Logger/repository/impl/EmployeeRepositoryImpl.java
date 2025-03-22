package com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.repository.impl;

import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.dto.employee.EmployeeAdd;
import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.dto.employee.EmployeeAdded;
import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.logger.SimpleJdbcCallLogger;
import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.mapper.EmployeeAddedMapper;
import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.time.LocalDate;
import java.util.List;

import static com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.common.CommonUtil.GET_ALL_EMPLOYEES;
import static com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.common.CommonUtil.INSERT_EMPLOYEES;

@Repository
@RequiredArgsConstructor
@Slf4j
public class EmployeeRepositoryImpl implements EmployeeRepository {

    final JdbcTemplate jdbcTemplate;
    final EmployeeAddedMapper employeeAddedMapper;

    @Override
    public EmployeeAdded addEmployee(EmployeeAdd employeeAdd) {

        SQLServerDataTable sqlServerDataTable = null;
        try {
            sqlServerDataTable = new SQLServerDataTable();
            sqlServerDataTable.addColumnMetadata("EmployeeName", Types.VARCHAR);
            sqlServerDataTable.addColumnMetadata("DepartmentId", Types.INTEGER);
            sqlServerDataTable.addColumnMetadata("HireDate", Types.DATE);
            sqlServerDataTable.addRow(employeeAdd.getName(), employeeAdd.getDepartmentId(), employeeAdd.getHireDate());
        } catch (Exception e) {
            log.error(e.getMessage());
        }


        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCallLogger(jdbcTemplate)
                .withProcedureName(INSERT_EMPLOYEES)
                .declareParameters(
                        new SqlParameter("Employees", microsoft.sql.Types.STRUCTURED),
                        new SqlParameter("DefaultHireDate", Types.DATE)
                )
                .withoutProcedureColumnMetaDataAccess()
                .returningResultSet("results", employeeAddedMapper);

        MapSqlParameterSource in = new MapSqlParameterSource()
                .addValue("Employees", sqlServerDataTable)
                .addValue("DefaultHireDate", LocalDate.now());
        var output = simpleJdbcCall.execute(in);
        var employees = (List<EmployeeAdded>) output.get("results");

        return employees != null ? employees.get(0) : null;
    }

    @Override
    public List<EmployeeAdded> findAll() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCallLogger(jdbcTemplate)
                .withProcedureName(GET_ALL_EMPLOYEES)
                .withoutProcedureColumnMetaDataAccess()
                .returningResultSet("results", employeeAddedMapper);

        MapSqlParameterSource in = new MapSqlParameterSource();
        var output = simpleJdbcCall.execute(in);
        return (List<EmployeeAdded>) output.get("results");
    }
}
