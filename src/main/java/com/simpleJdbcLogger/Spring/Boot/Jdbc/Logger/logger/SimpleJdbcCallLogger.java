package com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.logger;


import com.microsoft.sqlserver.jdbc.SQLServerDataColumn;
import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import java.sql.JDBCType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Slf4j
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
        if (!runWithEnhancedLogger) {
            return super.execute(parameterSource);
        }

        long startTime = System.currentTimeMillis();
        boolean success = false;
        Map<String, Object> result;

        try {
            result = super.execute(parameterSource);
            success = true;
            return result;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        } finally {
            long endTime = System.currentTimeMillis();
            String dbCall = "";
            try {
                dbCall = getDbCall(parameterSource);
            } catch (Exception ex) {
                dbCall = "-- failed to build dbCall: " + ex.getMessage();
            }
            if (success) {

                log.info("Success: {}, Duration: {}ms, Call: {}", success, endTime - startTime, dbCall);
            } else {
                log.error("Success: {}, Duration: {}ms, Call: {}", success, endTime - startTime, dbCall);

            }
        }
    }


    private String getDbCall(SqlParameterSource parameterSource) {
        if (parameterSource == null || parameterSource.getParameterNames() == null) {
            return "Error generating query, param source is null";
        }
        StringBuilder log = new StringBuilder("EXEC ").append(super.getProcedureName());
        if (parameterSource.getParameterNames().length > 0) {
            log.append(" ");
        }

        var parameterNames = parameterSource.getParameterNames();
        for (String parameterName : parameterNames) {
            Object paramValue = parameterSource.getValue(parameterName);
            StringBuilder declareLog = new StringBuilder();
            String sqlParamName = "@" + parameterName;
            if (paramValue instanceof SQLServerDataTable dt) {
                declareLog = new StringBuilder("DECLARE ");
                declareLog.append(sqlParamName);
                declareLog.append(" TABLE(");
                for (int i = 0; i < dt.getColumnMetadata().size(); i++) {
                    SQLServerDataColumn column = dt.getColumnMetadata().get(i);
                    declareLog.append(column.getColumnName()).append(" ").append(getSqlType(column.getColumnType()));

                    if (i < dt.getColumnMetadata().size() - 1) {
                        declareLog.append(", ");
                    }
                }
                declareLog.append("); \n");
                declareLog.append("INSERT INTO ").append(sqlParamName);
                declareLog.append(" VALUES");
                var iterator = dt.getIterator();
                int currentRow = 0;
                while (iterator.hasNext()) {
                    if (currentRow == maxRowsToPrint) {
                        break;
                    }
                    var column = iterator.next();
                    var values = column.getValue();
                    declareLog.append("(");
                    for (int i = 0; i < values.length; i++) {
                        declareLog.append(formatValue(values[i]));

                        if (i < values.length - 1) {
                            declareLog.append(", ");
                        }
                    }
                    declareLog.append("),");
                    currentRow++;
                }
                if (declareLog.charAt(declareLog.length() - 1) == ',') {
                    declareLog.deleteCharAt(declareLog.length() - 1);
                }
                declareLog.append(";\n");


                log.insert(0, declareLog);
                log.append(" ").append("@").append(parameterName).append("=").append("@").append(parameterName).append(", ");
            } else {
                declareLog.append(" ").append(sqlParamName);

                Object value = parameterSource.getValue(parameterName);
                declareLog.append("=").append(formatValue(value));

                log.append(declareLog).append(",");
            }
        }

        String logString = log.toString();
        if (logString.endsWith(",")) {
            logString = logString.substring(0, logString.length() - 1);
        }
        logString += ";";
        return logString;
    }

    private String getSqlType(int sqlType) {
        try {
            return JDBCType.valueOf(sqlType).getName();
        } catch (IllegalArgumentException e) {
            return "SQL_VARIANT";
        }
    }


    private static String formatValue(Object paramValue) {
        if (paramValue == null) {
            return "NULL";
        } else if (paramValue instanceof LocalDate || paramValue instanceof String || paramValue instanceof LocalTime) {
            return "'" + paramValue + "'";
        } else if (paramValue instanceof Boolean) {
            return (Boolean) paramValue ? "1" : "0";
        } else if (paramValue instanceof LocalDateTime) {
            return "'" + ((LocalDateTime) paramValue).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "'";
        }

        return paramValue.toString();
    }
}
