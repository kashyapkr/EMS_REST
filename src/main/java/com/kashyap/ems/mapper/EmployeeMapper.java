package com.kashyap.ems.mapper;

import com.kashyap.ems.dto.EmployeeDto;
import com.kashyap.ems.entity.Employee;
import org.springframework.stereotype.Component;


@Component
public class EmployeeMapper {
    public static EmployeeDto mapToEmployeeDto(Employee employee){
        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartments().getId()
        );
    }
    public  static  Employee mapToEmployee(EmployeeDto employeeDto){
       Employee employee = new Employee();
//       employee.setId(employeeDto.getId());
       employee.setFirstName(employeeDto.getFirstName());
       employee.setLastName(employeeDto.getLastName());
       employee.setEmail(employeeDto.getEmail());
       return employee;
    }
}
