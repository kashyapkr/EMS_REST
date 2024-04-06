package com.kashyap.ems.service;

import com.kashyap.ems.dto.DepartmentDto;
import com.kashyap.ems.entity.Departments;
import com.kashyap.ems.entity.Employee;
import com.kashyap.ems.exceptions.ResourceNotFound;
import com.kashyap.ems.repository.DepartmentRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements  DepartmentService{

    private ModelMapper modelMapper;

    private DepartmentRepo departmentRepo;
    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        Departments departments  = modelMapper.map(departmentDto,Departments.class);
        return modelMapper.map(departmentRepo.save(departments),DepartmentDto.class) ;
    }

    @Override
    public DepartmentDto getDepartment(Long id) {
        Departments existingDepartment = departmentRepo.findById(id).orElseThrow(()->new ResourceNotFound("Department not found with id "+id));
        return modelMapper.map(existingDepartment,DepartmentDto.class);
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        List<Departments> allDepartments =  departmentRepo.findAll();
        return allDepartments.stream().map((department)->modelMapper.map(department,DepartmentDto.class)).toList();
    }

    @Override
    public void deleteDepartment(Long id) {
        departmentRepo.findById(id).orElseThrow(()->new ResourceNotFound("Department not found with "+ id));
        departmentRepo.deleteById(id);
    }

    @Override
    public DepartmentDto updateDepartment(Long id, DepartmentDto updatedDepartmentdto) {
        Departments toUpdate = departmentRepo.findById(id).orElseThrow(()->new ResourceNotFound("Not found with id"+id));
        toUpdate.setDept_desc(updatedDepartmentdto.getDept_desc());
        toUpdate.setDept_name(updatedDepartmentdto.getDept_name());
        departmentRepo.save(toUpdate);
        return modelMapper.map(toUpdate,DepartmentDto.class);
    }
}
