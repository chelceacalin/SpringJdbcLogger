package com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.service.impl;

import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.model.ProcedureParameter;
import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.repository.ProcedureParameterRepository;
import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.service.ProcedureParameterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProcedureParameterServiceImpl implements ProcedureParameterService {

    final ProcedureParameterRepository procedureParameterRepository;

    @Override
    public List<ProcedureParameter> findAll() {
        return procedureParameterRepository.findAll();
    }
}
