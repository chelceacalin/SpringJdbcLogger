package com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.logger;

import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.service.SettingService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.common.CacheKeys.CUSTOM_JDBC_LOGGER_ENABLED;
import static com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.common.CacheKeys.MAX_ROWS_TO_PRINT;

@Component
@RequiredArgsConstructor
@Slf4j
public class SimpleJdbcCallLoggerFactory {
    final SettingService settingService;
    boolean runWithEnhancedLogger = false;
    int maxRowsToPrint = 5;

    @PostConstruct
    public void init() {
        log.warn("Initializing SimpleJdbcCallLoggerFactory");
        setConfigValues();
        log.warn("[SimpleJdbcCallLoggerFactory] runWithEnhancedLogger = {}, maxRowsToPrint = {}", runWithEnhancedLogger, maxRowsToPrint);
    }

    public SimpleJdbcCallLogger createJdbcLogger(JdbcTemplate jdbcTemplate) {
        setConfigValues();
        return new SimpleJdbcCallLogger(jdbcTemplate, maxRowsToPrint, runWithEnhancedLogger);
    }

    private void setConfigValues() {
        Optional.ofNullable(settingService.getSettingByKey(CUSTOM_JDBC_LOGGER_ENABLED)).ifPresent(setting ->
                runWithEnhancedLogger = Boolean.parseBoolean(setting.getValue()));

        Optional.ofNullable(settingService.getSettingByKey(MAX_ROWS_TO_PRINT)).ifPresent(setting ->
                maxRowsToPrint = Integer.parseInt(setting.getValue()));
    }
}
