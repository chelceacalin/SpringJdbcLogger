package com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.controller;

import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.dto.employee.EmployeeAdd;
import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Object> addEmployee(@RequestBody EmployeeAdd employeeAdd) {
        return ResponseEntity.ok(employeeService.addEmployee(employeeAdd));
    }

    @GetMapping
    public ResponseEntity<Object> getEmployees() {
        return ResponseEntity.ok(employeeService.findALl());
    }
}
