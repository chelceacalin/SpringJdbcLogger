package com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.controller;

import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.service.CacheService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/cache")
@RequiredArgsConstructor
public class CacheController {

    final CacheManager cacheManager;
    final CacheService cacheService;

    @GetMapping
    public ResponseEntity<Object> getCachedSettings(@RequestParam(required = false, defaultValue = "settings") String cacheName) {
        var cache = cacheManager.getCache(cacheName);
        Map<String, String> result = cacheService.getCachedSettings(cache);
        if (cache == null) {
            result.put("error", "Cache 'settings' not found.");
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/clear")
    void clearCaches() {
        cacheService.clearCache();
    }

    @DeleteMapping("/clear/{cacheName}")
    public ResponseEntity<Object> clearCacheByName(
            @Parameter(description = "Remove cache by cache name", example = "settings")
            @PathVariable String cacheName) {
        try {
            cacheService.clearCacheByName(cacheName);
            return ResponseEntity.ok("Successfully cleared cache '" + cacheName + "'.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
