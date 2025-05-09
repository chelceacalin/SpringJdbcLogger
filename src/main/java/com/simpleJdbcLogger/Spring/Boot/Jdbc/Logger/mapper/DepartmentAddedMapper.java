package com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.mapper;

import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.common.MapperUtil;
import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.dto.department.DepartmentAdded;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;

@Component
public class DepartmentAddedMapper extends MapperUtil implements RowMapper<DepartmentAdded> {
    @Override
    public DepartmentAdded mapRow(ResultSet rs, int rowNum) {
        DepartmentAdded added = new DepartmentAdded();
        added.setId(getInt(rs, "DepartmentId"));
        added.setName(getString(rs, "DepartmentName"));
        return added;
    }
}
