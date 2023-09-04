package com.grievance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.grievance.dto.EmployeeInDto;
import com.grievance.dto.EmployeeOutDto;
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
		assertEquals(employee.getDepartment().getDepartmentName(), employeeOutDto.getDepartment().getDepartmentName());
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
		employeeInDto.setDepartment(department);
		employeeInDto.setEmail("palak@nucleusteq.com");
		employeeInDto.setFirstTimeUser(true);
		employeeInDto.setFullName("Palak");
		employeeInDto.setPassword("Palak#123");
		employeeInDto.setUserType(UserType.ADMIN);
		employeeInDto.setTickets(null);
		
		Employee employee = modelMapper.map(employeeInDto, Employee.class);
		
		assertEquals(employeeInDto.getEmail(), employee.getEmail());
		assertEquals(employeeInDto.getDepartment(), employee.getDepartment());
		assertEquals(employeeInDto.getFirstTimeUser(), employee.getFirstTimeUser());
		assertEquals(employeeInDto.getFullName(), employee.getFullName());
		assertEquals(employeeInDto.getTickets(), employee.getTickets());
		assertEquals(employeeInDto.getUserType(), employee.getUserType());
	}
}

