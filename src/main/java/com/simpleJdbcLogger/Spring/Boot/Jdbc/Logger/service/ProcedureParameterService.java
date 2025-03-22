package com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.service;

import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.model.ProcedureParameter;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcedureParameterService {

    List<ProcedureParameter> findAll();
}
