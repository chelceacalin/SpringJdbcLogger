package com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.repository.impl;

import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.mapper.SettingsMapper;
import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.model.Settings;
import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.repository.SettingsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;

import static com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.common.CommonUtil.GET_SETTINGS_BY_KEY;

@Repository
@RequiredArgsConstructor
public class SettingsRepositoryImpl implements SettingsRepository {

    final JdbcTemplate jdbcTemplate;
    final SettingsMapper settingsMapper;

    @Override
    public Settings getSettingByKey(String key) {

        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName(GET_SETTINGS_BY_KEY)
                .declareParameters(
                        new SqlParameter("key", Types.VARCHAR)
                ).withoutProcedureColumnMetaDataAccess()
                .returningResultSet("results", settingsMapper);


        MapSqlParameterSource in = new MapSqlParameterSource()
                .addValue("key", key);

        var output = simpleJdbcCall.execute(in);
        var settings = (List<Settings>) output.get("results");
        return settings != null ? settings.get(0) : null;
    }
}
