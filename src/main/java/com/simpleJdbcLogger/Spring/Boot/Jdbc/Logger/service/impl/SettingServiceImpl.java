package com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.service.impl;

import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.model.Settings;
import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.repository.SettingsRepository;
import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.service.SettingService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SettingServiceImpl implements SettingService {

    final SettingsRepository settingsRepository;
    final CacheManager cacheManager;
    Cache cache;

    @PostConstruct
    public void init() {
        log.info("[SettingServiceImpl] Initializing cache");
        cache = cacheManager.getCache("settings");
    }

    @Override
    public Settings getSettingByKey(String key) {
        Settings value = getFromCache(key);

        if (value == null) {
            value = settingsRepository.getSettingByKey(key);
            cache.put(key, value);
        } else {
            log.info("[SettingServiceImpl] Setting with key [{}] already exists", key);
        }
        return value;

    }

    @SuppressWarnings("unchecked")
    private <T> T getFromCache(String key) {
        var wrapper = cache.get(key);
        return wrapper != null ? (T) wrapper.get() : null;
    }
}
