package com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.controller;

import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.dto.employee.EmployeeAdd;
import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Object> addEmployee(@RequestBody EmployeeAdd employeeAdd) {
        return ResponseEntity.ok(employeeService.addEmployee(employeeAdd));
    }

    @PostMapping("/multiple")
    public ResponseEntity<Object> addEmployees(@RequestBody List<EmployeeAdd> employeeAdds) {
        return ResponseEntity.ok(employeeService.addEmployees(employeeAdds));
    }


    @GetMapping
    public ResponseEntity<Object> getEmployees() {
        return ResponseEntity.ok(employeeService.findALl());
    }
}
