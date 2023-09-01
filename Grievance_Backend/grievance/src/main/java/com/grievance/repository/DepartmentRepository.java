/**
 * 
 */
package com.grievance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.grievance.entity.Department;

/**
 *
 */
public interface DepartmentRepository extends JpaRepository<Department, Integer>{
	Department findByDepartmentName(String departmentName);
}

