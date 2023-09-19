package com.grievance.service;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.grievance.dto.ChangePasswordInDto;
import com.grievance.dto.EmployeeInDto;
import com.grievance.dto.EmployeeLoginDto;
import com.grievance.dto.EmployeeOutDto;
import com.grievance.entity.Employee;
import com.grievance.entity.UserType;
import com.grievance.exception.EmployeeAlreadyExistException;
import com.grievance.exception.EmployeeNotFoundException;
import com.grievance.exception.PasswordMatchException;
import com.grievance.repository.EmployeeRepository;


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
		employee.setPassword("Ayushi#123");
		
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
		
    	when(employeeRepository.findByEmail(Mockito.anyString())).thenReturn(null);
    	
    	when(employeeRepository.save(Mockito.any(Employee.class))).thenReturn(employee);
    			
		Optional<EmployeeOutDto> dto = employeeService.saveEmployee(employeeInDto);
		
		assertEquals(dto.get(), employeeOutDto);
		
	}
    
    @Test
    void when_save_employee_fails_return_exception() {
    	
    	when(employeeRepository.findByEmail(Mockito.anyString())).thenReturn(employee);	
		assertThrows(EmployeeAlreadyExistException.class, ()->{
			employeeService.saveEmployee(employeeInDto);
		});
    }


    @Test
    void when_successfully_login_employee_return_employee() {
		employee.setPassword("QXl1c2hpIzEyNA==");
		when(employeeRepository.findByEmail(Mockito.anyString())).thenReturn(employee);
		
		Optional<EmployeeOutDto> dto = employeeService.loginEmployee(employeeLoginDto);
		
		assertEquals(dto.get(), employeeOutDto);
	}


    @Test
    void when_login_failed_return_empty_optional() {
		employee.setPassword("QXl1c2hpIzEyNA==");
		when(employeeRepository.findByEmail(Mockito.anyString())).thenReturn(null);
		
		Optional<EmployeeOutDto> dto = employeeService.loginEmployee(employeeLoginDto);
				
		assertEquals(dto, Optional.empty());
	}
    
    @Test
    void when_login_failed_by_false_credentials_return_empty_optional() {
    	employee.setPassword("QXl1c2hpIzEyNA==");
		when(employeeRepository.findByEmail(Mockito.anyString())).thenReturn(employee);
		
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
    
    @Test
    void password_change_successfully() {
    	employee.setPassword("QXl1c2hpIzEyNA==");
    	when(employeeRepository.findByEmail(Mockito.anyString())).thenReturn(employee);
    	
    	Employee changedEmployee = new Employee();
    	
    	when(employeeRepository.save(employee)).thenReturn(changedEmployee);
    	
    	ChangePasswordInDto changePasswordInDto = new ChangePasswordInDto();
    	changePasswordInDto.setOldPassword("QXl1c2hpIzEyNA==");
    	changePasswordInDto.setNewPassword("QXl2c2hpIzEyNA==");
    	
    	Boolean changedSuccessfully = employeeService.changePassword(changePasswordInDto, "ayushi@nucleusteq.com");
    	
    	assertThat(changedSuccessfully);
    }
    
    @Test
    void password_change_failed_when_employee_does_not_exist() {
    	employee.setPassword("Ayushi#124");
    	when(employeeRepository.findByEmail(Mockito.anyString())).thenReturn(null);
    	
    	ChangePasswordInDto changePasswordInDto = new ChangePasswordInDto();
    	changePasswordInDto.setOldPassword("QXl1c2hpIzEyNA==");
    	changePasswordInDto.setNewPassword("QX21c2hpIzEyNA==");
    	
    	assertThrows(EmployeeNotFoundException.class, ()->{
    		employeeService.changePassword(changePasswordInDto, "ayushi@nucleusteq.com");
    	});
    }
    
    @Test
    void changePassword_fails_when_password_mismatch() {
    	employee.setPassword("QXl1c2hpIzEyNA==");
    	when(employeeRepository.findByEmail(Mockito.anyString())).thenReturn(employee);
    	
    	ChangePasswordInDto changePasswordInDto = new ChangePasswordInDto();
    	changePasswordInDto.setOldPassword("QXl1c2hpIzEyNA==");
    	changePasswordInDto.setNewPassword("QXl2c2hpIzEyNA==");
    	
    	assertThrows(PasswordMatchException.class, ()->{
    		employeeService.changePassword(changePasswordInDto, "ayushi@nucleusteq.com");
    	});
    }
}

