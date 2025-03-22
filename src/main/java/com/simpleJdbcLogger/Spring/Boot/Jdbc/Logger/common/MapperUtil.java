package com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.common;

import java.sql.ResultSet;
import java.time.LocalDate;

public class MapperUtil {

    public int getInt(ResultSet rs, String columnName) {
        try {
            return rs.getInt(columnName);
        } catch (Exception e) {
            return 0;
        }
    }

    public String getString(ResultSet rs, String columnName) {
        try {
            return rs.getString(columnName);
        } catch (Exception e) {
            return "Wrong column name";
        }
    }

    public LocalDate getLocalDate(ResultSet rs, String columnName) {
        try {
            return rs.getDate(columnName).toLocalDate();
        } catch (Exception e) {
            return LocalDate.now();
        }
    }
}
