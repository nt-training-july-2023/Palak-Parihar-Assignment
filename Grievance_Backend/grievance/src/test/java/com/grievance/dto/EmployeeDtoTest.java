package com.grievance.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.grievance.entity.Department;
import com.grievance.entity.Employee;
import com.grievance.entity.UserType;

@SpringBootTest
class EmployeeDtoTest {
	@Autowired
	ModelMapper modelMapper;

    @Test
    void when_convert_employee_to_employeeOutDto() {
		Employee employee = new Employee();
		Department department = new Department();
		department.setDepartmentName("HR");
		employee.setDepartment(department);
		employee.setEmail("palak@nucleusteq.com");
		employee.setFirstTimeUser(true);
		employee.setFullName("Palak");
		employee.setPassword("Palak#123");
		employee.setUserType(UserType.ADMIN);
		employee.setTickets(null);
		
		EmployeeOutDto employeeOutDto = modelMapper.map(employee, EmployeeOutDto.class);
		
		assertEquals(employee.getEmail(), employeeOutDto.getEmail());
		assertEquals(employee.getDepartment().getDepartmentName(), employeeOutDto.getDepartment());
		assertEquals(employee.getFirstTimeUser(), employeeOutDto.getFirstTimeUser());
		assertEquals(employee.getFullName(), employeeOutDto.getFullName());
		assertEquals(employee.getUserType(), employeeOutDto.getUserType());
		assertEquals(employee.getTickets(), employeeOutDto.getTickets());
		
	}

    @Test
    void when_convert_employeeInDto_to_employee() {
		EmployeeInDto employeeInDto = new EmployeeInDto();
		Department department = new Department();
		department.setDepartmentName("HR");
//		employeeInDto.setDepartmentDto(department);
		employeeInDto.setEmail("palak@nucleusteq.com");
		employeeInDto.setFirstTimeUser(true);
		employeeInDto.setFullName("Palak");
		employeeInDto.setPassword("Palak#123");
		employeeInDto.setUserType(UserType.ADMIN);
//		employeeInDto.setTickets(null);
		
		Employee employee = modelMapper.map(employeeInDto, Employee.class);
		
		assertEquals(employeeInDto.getEmail(), employee.getEmail());
		assertEquals(employeeInDto.getDepartmentDto(), employee.getDepartment());
		assertEquals(employeeInDto.getFirstTimeUser(), employee.getFirstTimeUser());
		assertEquals(employeeInDto.getFullName(), employee.getFullName());
//		assertEquals(employeeInDto.getTickets(), employee.getTickets());
		assertEquals(employeeInDto.getUserType(), employee.getUserType());
	}
}

