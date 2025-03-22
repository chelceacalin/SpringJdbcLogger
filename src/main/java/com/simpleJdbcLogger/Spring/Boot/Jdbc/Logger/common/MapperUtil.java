package com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.common;

import java.sql.ResultSet;

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
            return "";
        }
    }
}
