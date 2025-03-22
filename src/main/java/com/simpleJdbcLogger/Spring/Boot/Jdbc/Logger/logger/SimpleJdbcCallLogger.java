package com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.logger;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import java.util.Map;

public class SimpleJdbcCallLogger extends SimpleJdbcCall {

    int maxRowsToPrint;
    boolean runWithEnhancedLogger;

    public SimpleJdbcCallLogger(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }


    public SimpleJdbcCallLogger(JdbcTemplate jdbcTemplate, int maxRowsToPrint, boolean runWithEnhancedLogger) {
        super(jdbcTemplate);
        this.maxRowsToPrint = maxRowsToPrint;
        this.runWithEnhancedLogger = runWithEnhancedLogger;
    }

    @Override
    public Map<String, Object> execute(SqlParameterSource parameterSource) {

        String sqlCall;
        var a = parameterSource;
        return super.execute(parameterSource);
    }
}
