package com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.controller;

import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.dto.DepartmentAdd;
import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department")
@RequiredArgsConstructor
public class DepartmentController {

    final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<Object> addDepartment(@RequestBody DepartmentAdd dto) {
        return ResponseEntity.ok(departmentService.addDepartment(dto));
    }
}
