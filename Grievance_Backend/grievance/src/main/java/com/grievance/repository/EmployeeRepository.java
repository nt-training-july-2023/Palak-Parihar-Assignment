package com.grievance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.grievance.entity.Employee;
import java.util.List;


public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	Employee findByEmail(String email);
}

