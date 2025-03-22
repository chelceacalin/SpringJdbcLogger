package com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.mapper;

import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.common.MapperUtil;
import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.model.Settings;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;

@Component
public class SettingsMapper extends MapperUtil implements RowMapper<Settings> {
    @Override
    public Settings mapRow(ResultSet rs, int rowNum) {
        Settings settings = new Settings();
        settings.setId(getInt(rs, "id"));
        settings.setKey(getString(rs,"key"));
        settings.setValue(getString(rs,"value"));
        settings.setDescription(getString(rs,"description"));
        return settings;
    }
}
