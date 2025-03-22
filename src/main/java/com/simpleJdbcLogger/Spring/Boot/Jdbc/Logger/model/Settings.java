package com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Settings {
    private int id;
    private String key;
    private String value;
    private String description;
}
