package com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.service;

import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.model.Settings;

public interface SettingService {

    Settings getSettingByKey(String key);
}
