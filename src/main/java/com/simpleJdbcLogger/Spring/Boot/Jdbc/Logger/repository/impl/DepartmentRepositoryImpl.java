package com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.repository.impl;

import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.dto.DepartmentAdd;
import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.dto.DepartmentAdded;
import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.logger.SimpleJdbcCallLogger;
import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.mapper.DepartmentAddedMapper;
import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.sql.SQLType;
import java.sql.Types;
import java.util.List;

import static com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.common.CommonUtil.INSERT_DEPARTMENT;

@RequiredArgsConstructor
@Repository
public class DepartmentRepositoryImpl implements DepartmentRepository {

    final DepartmentAddedMapper departmentAddedMapper;
    final JdbcTemplate jdbcTemplate;

    @Override
    public DepartmentAdded addDepartment(DepartmentAdd dto) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName(INSERT_DEPARTMENT)
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(
                        new SqlParameter("DepartmentName", Types.VARCHAR)
                )
                .returningResultSet("results", departmentAddedMapper);

        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("DepartmentName", dto.getName());

        var output = simpleJdbcCall.execute(parameterSource);
        var departments = (List<DepartmentAdded>) output.get("results");

        return departments != null ? departments.get(0) : null;
    }
}
