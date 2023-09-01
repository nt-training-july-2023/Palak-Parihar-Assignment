/**
 * 
 */
package com.grievance.service;

import java.util.List;
import java.util.Optional;
import com.grievance.dto.DepartmentDto;
import com.grievance.entity.Department;

/**
 *
 */
public interface DepartmentService {
	Optional<DepartmentDto> saveDepartment(final DepartmentDto departmentDto);
//	
//	Optional<?> deleteDepartment(DepartmentDto departmentDto);
//	
	Optional<List<DepartmentDto>> listAllDepartment();
//	
//	Department saveDepartment(Department department);
//	
//	List<Department> listAllDepartments();
}


