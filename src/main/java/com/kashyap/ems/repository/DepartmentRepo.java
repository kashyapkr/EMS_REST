package com.kashyap.ems.repository;

import com.kashyap.ems.entity.Departments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<Departments,Long> {
}
