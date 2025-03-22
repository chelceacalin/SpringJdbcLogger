package com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.controller;

import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.service.ProcedureParameterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/procedureParameter")
@RequiredArgsConstructor
public class ProcedureParameterController {

    final ProcedureParameterService procedureParameterService;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        return ResponseEntity.ok(procedureParameterService.findAll());
    }
}
