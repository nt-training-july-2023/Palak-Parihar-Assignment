/**
 * 
 */
package com.grievance.dto;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.grievance.dto.DepartmentInDto;
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
		
		DepartmentInDto departmentInDto = modelMapper.map(department, DepartmentInDto.class);
		assertEquals(department.getDepartmentId(), departmentInDto.getDepartmentId());
		assertEquals(department.getDepartmentName(), departmentInDto.getDepartmentName());		
	}

    @Test
    void when_convert_departmentDto_to_department() {
		DepartmentInDto departmentInDto = new DepartmentInDto();
		departmentInDto.setDepartmentId(102);
		departmentInDto.setDepartmentName("HR");
		
		Department department = modelMapper.map(departmentInDto, Department.class);
		
		assertEquals(department.getDepartmentId(), departmentInDto.getDepartmentId());
		assertEquals(department.getDepartmentName(), departmentInDto.getDepartmentName());
	}
}


