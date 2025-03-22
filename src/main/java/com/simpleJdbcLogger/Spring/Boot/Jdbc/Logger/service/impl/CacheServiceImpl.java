package com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.service.impl;

import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.common.CacheKeys;
import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.service.CacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CacheServiceImpl implements CacheService {
    final CacheManager cacheManager;

    @Override
    public void clearCache() {
        cacheManager.getCacheNames().forEach(name -> Objects.requireNonNull(cacheManager.getCache(name)).clear());
    }

    @Override
    public void clearCacheByName(String cacheName) {
        var c = cacheManager.getCache(cacheName);
        if (c == null) {
            throw new RuntimeException("Cache '" + cacheName + "' not found.");
        }
        c.clear();
    }

    @Override
    public Map<String, String> getCachedSettings(Cache cache) {
        Map<String, String> result = new HashMap<>();
        for (Field field : CacheKeys.class.getDeclaredFields()) {
            if (field.getType().equals(String.class)) {
                try {
                    String key = (String) field.get(null);
                    var wrapper = cache.get(key);
                    result.put(key, wrapper != null && wrapper.get() != null ? Objects.requireNonNull(wrapper.get()).toString() : "null");
                } catch (IllegalAccessException e) {
                    result.put(field.getName(), "ERROR: " + e.getMessage());
                }
            }
        }
        return result;
    }
}
