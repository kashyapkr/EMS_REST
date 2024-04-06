package com.kashyap.ems.service;

import com.kashyap.ems.dto.EmployeeDto;

import java.util.List;

public interface EmployeeServie {
    EmployeeDto addEmployee(EmployeeDto employeeDto);
    EmployeeDto getEmployee(Long id);

    List<EmployeeDto> getAllEmployees();

    EmployeeDto updateEmployee(Long id,EmployeeDto updatedemployeeDto);

    void deleteEmployee(Long id);
}
