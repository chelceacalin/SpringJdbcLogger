package com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.logger;

import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.model.ProcedureParameter;
import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.service.ProcedureParameterService;
import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.service.SettingService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.common.CacheKeys.CUSTOM_JDBC_LOGGER_ENABLED;
import static com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.common.CacheKeys.MAX_ROWS_TO_PRINT;

@Component
@RequiredArgsConstructor
@Slf4j
public class SimpleJdbcCallLoggerFactory {
    final SettingService settingService;
    final ProcedureParameterService procedureParameterService;

    boolean runWithEnhancedLogger = false;
    int maxRowsToPrint = 5;
    Map<String, List<ProcedureParameter>> paramMap = new HashMap<>();


    @PostConstruct
    public void init() {
        log.warn("Initializing SimpleJdbcCallLoggerFactory");
        setConfigValues();
        setProcedureParameters();
        log.warn("[SimpleJdbcCallLoggerFactory] runWithEnhancedLogger = {}, maxRowsToPrint = {}", runWithEnhancedLogger, maxRowsToPrint);
    }

    private void setProcedureParameters() {
        List<ProcedureParameter> procedureParameters = procedureParameterService.findAll();
        paramMap = procedureParameters.stream().collect(Collectors.groupingBy(ProcedureParameter::getProcedureName, Collectors.toList()));
        if (paramMap.isEmpty()) {
            log.warn("[SimpleJdbcCallLoggerFactory] No procedure parameters found");
        } else {
            log.warn("[SimpleJdbcCallLoggerFactory] Found {} procedure parameters", paramMap.size());
        }
    }

    public SimpleJdbcCallLogger createJdbcLogger(JdbcTemplate jdbcTemplate) {
        setConfigValues();
        return new SimpleJdbcCallLogger(jdbcTemplate, maxRowsToPrint, runWithEnhancedLogger, paramMap);
    }

    private void setConfigValues() {
        Optional.ofNullable(settingService.getSettingByKey(CUSTOM_JDBC_LOGGER_ENABLED)).ifPresent(setting ->
                runWithEnhancedLogger = Boolean.parseBoolean(setting.getValue()));

        Optional.ofNullable(settingService.getSettingByKey(MAX_ROWS_TO_PRINT)).ifPresent(setting ->
                maxRowsToPrint = Integer.parseInt(setting.getValue()));
    }
}
