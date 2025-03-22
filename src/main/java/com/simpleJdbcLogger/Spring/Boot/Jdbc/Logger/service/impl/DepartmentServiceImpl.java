package com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.service.impl;

import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.dto.department.DepartmentAdd;
import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.dto.department.DepartmentAdded;
import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.repository.DepartmentRepository;
import com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    final DepartmentRepository departmentRepository;


    public DepartmentAdded addDepartment(DepartmentAdd departmentAdd) {
        return departmentRepository.addDepartment(departmentAdd);
    }

}
