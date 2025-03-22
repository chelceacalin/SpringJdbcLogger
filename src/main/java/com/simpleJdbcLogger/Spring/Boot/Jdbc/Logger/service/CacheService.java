package com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.service;

import org.springframework.cache.Cache;

import java.util.Map;

public interface CacheService {
    void clearCache();

    void clearCacheByName(String cacheName);

    Map<String, String> getCachedSettings(Cache cache);
}
