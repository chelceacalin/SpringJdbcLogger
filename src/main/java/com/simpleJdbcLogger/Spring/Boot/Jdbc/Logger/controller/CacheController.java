package com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.controller;

import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.common.CacheKeys;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/cache")
@RequiredArgsConstructor
public class CacheController {

    final CacheManager cacheManager;

    @GetMapping
    public ResponseEntity<Object> getCachedSettings() {
        var cache = cacheManager.getCache("settings");
        Map<String, String> result = new LinkedHashMap<>();

        if (cache == null) {
            result.put("error", "Cache 'settings' not found.");
            return ResponseEntity.ok(result);
        }

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
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/clear")
    void clearCaches() {
        cacheManager.getCacheNames().forEach(name -> Objects.requireNonNull(cacheManager.getCache(name)).clear());
    }

    @DeleteMapping("/clear/{cacheName}")
    public ResponseEntity<Object> clearCacheByName(
            @Parameter(description = "Remove cache by cache name", example = "settings")
            @PathVariable String cacheName) {
        try {
            var c = cacheManager.getCache(cacheName);
            if (c == null) {
                throw new Exception("Cache '" + cacheName + "' not found.");
            }
            c.clear();
            return ResponseEntity.ok("Successfully cleared cache '" + cacheName + "'.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
