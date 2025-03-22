package com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.mapper;

import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.common.MapperUtil;
import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.model.ProcedureParameter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ProcedureParameterMapper extends MapperUtil implements RowMapper<ProcedureParameter> {
    @Override
    public ProcedureParameter mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProcedureParameter procedureParameter = new ProcedureParameter();
        procedureParameter.setParameterName(getString(rs, "ParameterName"));
        procedureParameter.setParameterType(getString(rs, "ParameterType"));
        procedureParameter.setProcedureName(getString(rs, "ProcedureName"));
        return procedureParameter;
    }
}
