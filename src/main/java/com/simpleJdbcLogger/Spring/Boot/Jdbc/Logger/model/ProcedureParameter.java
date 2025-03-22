package com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.model;

import lombok.Data;

@Data
public class ProcedureParameter {
    String procedureName;
    String parameterName;
    String parameterType;
}
