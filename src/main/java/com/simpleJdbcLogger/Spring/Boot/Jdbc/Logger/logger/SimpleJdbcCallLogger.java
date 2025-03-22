package com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.logger;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import java.util.Map;

public class SimpleJdbcCallLogger extends SimpleJdbcCall {

    public SimpleJdbcCallLogger(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public Map<String, Object> execute(SqlParameterSource parameterSource) {


        var a=parameterSource;
        return super.execute(parameterSource);
    }
}
