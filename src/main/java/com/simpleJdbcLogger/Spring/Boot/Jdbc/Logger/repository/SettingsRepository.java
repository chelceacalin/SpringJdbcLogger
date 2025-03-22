package com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.repository;

import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.model.Settings;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingsRepository {

    Settings getSettingByKey(String key);

}
