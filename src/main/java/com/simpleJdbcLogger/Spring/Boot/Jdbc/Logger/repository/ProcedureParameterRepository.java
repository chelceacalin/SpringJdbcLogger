package com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.repository;

import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.model.ProcedureParameter;

import java.util.List;

public interface ProcedureParameterRepository {

    List<ProcedureParameter> findAll();
}
