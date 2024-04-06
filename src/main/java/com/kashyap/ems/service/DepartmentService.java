package com.kashyap.ems.service;

import com.kashyap.ems.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {
    DepartmentDto createDepartment(DepartmentDto departmentDto);
    DepartmentDto getDepartment(Long id);

    List<DepartmentDto> getAllDepartments();

    void deleteDepartment(Long id);

    DepartmentDto updateDepartment(Long id, DepartmentDto updatedDepartmentdto);

}
