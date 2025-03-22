package com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.repository.impl;

import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.common.CommonUtil;
import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.logger.SimpleJdbcCallLoggerFactory;
import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.mapper.ProcedureParameterMapper;
import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.model.ProcedureParameter;
import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.repository.ProcedureParameterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProcedureParameterRepositoryImpl implements ProcedureParameterRepository {

    final JdbcTemplate jdbcTemplate;
    final SimpleJdbcCallLoggerFactory factory;
    final ProcedureParameterMapper procedureParameterMapper;

    @Override
    public List<ProcedureParameter> findAll() {
        SimpleJdbcCall simpleJdbcCall = factory.createJdbcLogger(jdbcTemplate)
                .withProcedureName(CommonUtil.GET_PROCEDURE_PARAMETERS)
                .withoutProcedureColumnMetaDataAccess()
                .returningResultSet("results", procedureParameterMapper);

        MapSqlParameterSource in = new MapSqlParameterSource();
        return (List<ProcedureParameter>) simpleJdbcCall.execute(in).get("results");
    }
}
