package com.grievance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grievance.entity.Department;
import com.grievance.entity.Employee;
import java.util.List;


public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	Employee findByEmail(String email);
	
	List<Employee> findByDepartment(Department department);
}

