package com.grievance;


import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import com.grievance.dto.EmployeeInDto;
import com.grievance.dto.EmployeeLoginDto;
import com.grievance.dto.EmployeeOutDto;
import com.grievance.entity.Department;
import com.grievance.entity.Employee;
import com.grievance.entity.UserType;
import com.grievance.repository.EmployeeRepository;
import com.grievance.service.EmployeeServiceImpl;


@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {
	
	@Mock
	ModelMapper modelMapper;
	
	@Mock
	EmployeeRepository employeeRepository;
	
	private Employee employee;
	
	private EmployeeOutDto employeeOutDto;
	
	private EmployeeInDto employeeInDto;
	
	private EmployeeLoginDto employeeLoginDto;
	
	
	
//	@Autowired
	@InjectMocks
	EmployeeServiceImpl employeeService;

    @BeforeEach
    void setUp() {
		employeeOutDto = new EmployeeOutDto();
		employeeOutDto.setDepartment(null);
		employeeOutDto.setEmail("palak@nucleusteq.com");
		employeeOutDto.setFirstTimeUser(true);
		employeeOutDto.setFullName("Palak Parihar");
		employeeOutDto.setTickets(null);
		employeeOutDto.setUserType(UserType.MEMBER);
//		System.out.println(employeeOutDto);
		
		employee = new Employee();
		employee.setDepartment(null);
		employee.setEmail("palak@nucleusteq.com");
		employee.setFirstTimeUser(true);
		employee.setFullName("Palak Parihar");
		employee.setTickets(null);
		employee.setUserType(UserType.MEMBER);
		
		employeeInDto = new EmployeeInDto();
		employeeInDto.setEmail("palak@nucleusteq.com");
		
		employeeLoginDto = new EmployeeLoginDto();
		employeeLoginDto.setEmail("palak@nucleusteq.com");
		employeeLoginDto.setPassword("Palak#123");
		
		
		lenient().when(modelMapper.map(employee, EmployeeOutDto.class)).thenReturn(employeeOutDto);
		
	   lenient().when(modelMapper.map(employeeInDto, Employee.class)).thenReturn(employee);
		
	}

    @Test
    void when_save_employee_return_employee() {
		
		when(employeeRepository.save(Mockito.any(Employee.class))).thenReturn(employee);
		
		Optional<EmployeeOutDto> dto = employeeService.saveEmployee(employeeInDto);
		
		assertEquals(dto.get(), employeeOutDto);
		
	}


    @Test
    void when_successfully_login_employee_return_employee() {
		employee.setPassword("Palak#123");
		when(employeeRepository.findByEmail(Mockito.anyString())).thenReturn(employee);
		
		Optional<EmployeeOutDto> dto = employeeService.loginEmployee(employeeLoginDto);
		
		assertEquals(dto.get(), employeeOutDto);
	}


    @Test
    void when_login_failed_return_empty_optional() {
		employee.setPassword("Palak#123");
		when(employeeRepository.findByEmail(Mockito.anyString())).thenReturn(null);
		
		Optional<EmployeeOutDto> dto = employeeService.loginEmployee(employeeLoginDto);
				
		assertEquals(dto, Optional.empty());
	}

    @Test
    void listAllEmployees_return_list_of_all_employees() {
		List<EmployeeOutDto> employeeOutDtosExpected = new ArrayList<EmployeeOutDto>();
		employeeOutDtosExpected.add(employeeOutDto);
		
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(employee);
		
		when(employeeRepository.findAll()).thenReturn(employees);
		
		Optional<List<EmployeeOutDto>> employeeOutDtosReceived = employeeService.listAllEmployees();
		
		assertEquals(employeeOutDtosReceived.get(), employeeOutDtosExpected);
	}
}

