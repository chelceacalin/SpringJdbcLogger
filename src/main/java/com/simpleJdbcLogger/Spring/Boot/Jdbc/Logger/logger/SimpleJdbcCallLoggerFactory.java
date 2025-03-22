package com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.logger;

import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.common.CommonUtil;
import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.service.SettingService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.common.CacheKeys.*;

@Component
@RequiredArgsConstructor
public class SimpleJdbcCallLoggerFactory {
    final SettingService settingService;
    boolean runWithEnhancedLogger = false;
    int maxRowsToPrint = 5;

    @PostConstruct
    public void init() {
        Optional.ofNullable(settingService.getSettingByKey(CUSTOM_JDBC_LOGGER_ENABLED)).ifPresent(setting ->
        {
            runWithEnhancedLogger = Boolean.parseBoolean(setting.getValue());
        });

        Optional.ofNullable(settingService.getSettingByKey(MAX_ROWS_TO_PRINT)).ifPresent(setting ->
        {
            maxRowsToPrint = Integer.parseInt(setting.getValue());
        });
    }

    public SimpleJdbcCallLogger createJdbcLogger(JdbcTemplate jdbcTemplate) {
        return new SimpleJdbcCallLogger(jdbcTemplate, maxRowsToPrint, runWithEnhancedLogger);
    }
}
