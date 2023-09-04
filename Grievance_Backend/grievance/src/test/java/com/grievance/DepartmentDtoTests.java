/**
 * 
 */
package com.grievance;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.grievance.dto.DepartmentDto;
import com.grievance.entity.Department;

/**
 *
 */
@SpringBootTest
class DepartmentDtoTests {
	@Autowired
	ModelMapper modelMapper;

    @Test
    void when_convert_department_to_departmentDto() {
		Department department = new Department();
		department.setDepartmentId(102);
		department.setDepartmentName("HR");
		department.setEmployees(null);
		department.setTickets(null);
		
		DepartmentDto departmentDto = modelMapper.map(department, DepartmentDto.class);
		assertEquals(department.getDepartmentId(), departmentDto.getDepartmentId());
		assertEquals(department.getDepartmentName(), departmentDto.getDepartmentName());		
	}

    @Test
    void when_convert_departmentDto_to_department() {
		DepartmentDto departmentDto = new DepartmentDto();
		departmentDto.setDepartmentId(102);
		departmentDto.setDepartmentName("HR");
		
		Department department = modelMapper.map(departmentDto, Department.class);
		
		assertEquals(department.getDepartmentId(), departmentDto.getDepartmentId());
		assertEquals(department.getDepartmentName(), departmentDto.getDepartmentName());
	}
}


