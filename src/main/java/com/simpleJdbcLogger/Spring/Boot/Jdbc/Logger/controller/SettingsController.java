package com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.controller;

import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.service.SettingService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/settings")
@RequiredArgsConstructor
public class SettingsController {

    final SettingService settingsService;

    @GetMapping("/{key}")
    public ResponseEntity<Object> getSettingByKey(
            @Parameter(description = "Example cache key", example = "CUSTOM_JDBC_LOGGER_ENABLED")
            @PathVariable String key) {
        return ResponseEntity.ok(settingsService.getSettingByKey(key));
    }
}
