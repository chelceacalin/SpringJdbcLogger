package com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.service.impl;

import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.model.Settings;
import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.repository.SettingsRepository;
import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.service.SettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SettingServiceImpl implements SettingService {

    final SettingsRepository settingsRepository;

    @Override
    public Settings getSettingByKey(String key) {
        return settingsRepository.getSettingByKey(key);
    }
}
