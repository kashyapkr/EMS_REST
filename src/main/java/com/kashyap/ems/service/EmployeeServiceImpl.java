package com.kashyap.ems.service;

import com.kashyap.ems.dto.EmployeeDto;
import com.kashyap.ems.entity.Departments;
import com.kashyap.ems.entity.Employee;
import com.kashyap.ems.exceptions.ResourceNotFound;
import com.kashyap.ems.mapper.EmployeeMapper;
import com.kashyap.ems.repository.DepartmentRepo;
import com.kashyap.ems.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements  EmployeeServie {

    private EmployeeRepository employeeRepository;
    private DepartmentRepo departmentRepo;
    private ModelMapper modelMapper;
    private EmployeeMapper employeeMapper;


    @Override
    public EmployeeDto addEmployee(EmployeeDto employeeDto) {
//        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
//        Employee newEmployee = modelMapper.map(employeeDto,Employee.class);
        Employee newEmployee = employeeMapper.mapToEmployee(employeeDto);
        Departments departments  = departmentRepo.findById(employeeDto.getDeptId()).orElseThrow(()->new ResourceNotFound("Department Id not found"+employeeDto.getDeptId()));
        newEmployee.setDepartments(departments);
        Employee savedEmployee = employeeRepository.save(newEmployee);
        System.out.println("Dept id of dto before mapping "+employeeDto.getDeptId());
//        EmployeeDto savedDto = modelMapper.map(savedEmployee,EmployeeDto.class);
        EmployeeDto savedDto = employeeMapper.mapToEmployeeDto(savedEmployee);
        System.out.println("Dept id of saved dto = "+ savedDto.getDeptId());
        return savedDto;
    }

    @Override
    public EmployeeDto getEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(()->new ResourceNotFound("Employee with this id not found"+id));
        EmployeeDto employeeDto = modelMapper.map(employee,EmployeeDto.class);
        return employeeDto;
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employeeList  = employeeRepository.findAll();
        return employeeList.stream().map((employee)->modelMapper.map(employee,EmployeeDto.class)).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto updatedemployeeDto) {

        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(()->new ResourceNotFound("Employee not found with"+id));
        existingEmployee.setFirstName(updatedemployeeDto.getFirstName());
        existingEmployee.setLastName(updatedemployeeDto.getLastName());
        existingEmployee.setEmail(updatedemployeeDto.getEmail());
        Departments departments  = departmentRepo.findById(updatedemployeeDto.getDeptId()).orElseThrow(()->new ResourceNotFound("Department Id not found"+updatedemployeeDto.getDeptId()));
        System.out.println("Department id before mapping"+ updatedemployeeDto.getDeptId());
        existingEmployee.setDepartments(departments);
        Employee updatedEmployee = employeeRepository.save(existingEmployee);
//        EmployeeDto newDto =  modelMapper.map(updatedEmployee,EmployeeDto.class);
        EmployeeDto newDto  = employeeMapper.mapToEmployeeDto(updatedEmployee);
        System.out.println(newDto.getDeptId());
        return newDto;
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.findById(id).orElseThrow(()->new ResourceNotFound("Employee does not eixsts with "+id));
         employeeRepository.deleteById(id);
    }
}
