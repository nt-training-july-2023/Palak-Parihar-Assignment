package com.grievance.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.grievance.dto.ChangePasswordInDto;
import com.grievance.dto.DepartmentInDto;
import com.grievance.dto.EmployeeInDto;
import com.grievance.dto.EmployeeLoginDto;
import com.grievance.dto.EmployeeOutDto;
import com.grievance.entity.Employee;
import com.grievance.entity.UserType;
import com.grievance.exception.RecordAlreadyExistException;
import com.grievance.exception.ResourceNotFoundException;
import com.grievance.exception.CustomException;
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

  @InjectMocks
  EmployeeServiceImpl employeeService;

  @BeforeEach
  void setUp() {
    employeeOutDto = new EmployeeOutDto();
    employeeOutDto.setEmployeeId(1);
    employeeOutDto.setDepartment(null);
    employeeOutDto.setEmail("palak@nucleusteq.com");
    employeeOutDto.setFirstTimeUser(true);
    employeeOutDto.setFullName("Palak Parihar");
    employeeOutDto.setUserType(UserType.MEMBER);

    employee = new Employee();
    employee.setEmployeeId(1);
    employee.setDepartment(null);
    employee.setEmail("palak@nucleusteq.com");
    employee.setFirstTimeUser(true);
    employee.setFullName("Palak Parihar");
    employee.setTickets(null);
    employee.setUserType(UserType.MEMBER);
    employee.setPassword("Ayushi#123");

    employeeInDto = new EmployeeInDto();
    employeeInDto.setEmployeeId(1);
    employeeInDto.setEmail("palak@nucleusteq.com");
    employeeInDto.setPassword("U3VwZXJAMTIz");
    employeeInDto.setDepartmentDto(new DepartmentInDto(101, "HR"));

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
    			
		     Optional<EmployeeOutDto> employeeOutDto = employeeService.saveEmployee(employeeInDto);
				    
		    assertThat(employeeOutDto.equals(employeeOutDto));
		
	}

  @Test
    void when_save_employee_fails_return_exception() {
    employeeInDto.setPassword("UGFsYWs=");	
		    assertThrows(CustomException.class, ()->{
			         employeeService.saveEmployee(employeeInDto);
		     });
    }
  
  @Test
  void when_save_employee_fails_due_to_password_violationsreturn_exception() {
  employeeInDto.setPassword("QXl1c2hpIzEyNA==");
  when(employeeRepository.findByEmail(Mockito.anyString())).thenReturn(employee);
  assertThrows(RecordAlreadyExistException.class, ()->{
       employeeService.saveEmployee(employeeInDto);
   });
  }

  @Test
  void when_successfully_login_employee_return_employee() {
    employee.setPassword("QXl1c2hpIzEyNA==");
    when(employeeRepository.findByEmailAndPassword(Mockito.anyString(), Mockito.anyString())).thenReturn(employee);

    Optional<EmployeeOutDto> employeeOutDto = employeeService.loginEmployee(employeeLoginDto);

    assertThat(employee.equals(employeeOutDto));
  }

  @Test
  void when_login_failed_throws_exception() {
    employee.setPassword("QXl1c2hpIzEyNA==");
    
    assertThrows(ResourceNotFoundException.class, () -> {
      employeeService.loginEmployee(employeeLoginDto);
    });
  }

  @Test
  void listAllEmployees_return_list_of_all_employees() {

    List<Employee> employees = new ArrayList<Employee>();
    employees.add(employee);

    when(employeeRepository.findAll(Mockito.any(PageRequest.class))).thenReturn(new PageImpl<Employee>(employees));

    Optional<List<EmployeeOutDto>> employeeOutDtosReceived = employeeService.listAllEmployees(0);

    for(int i=0; i<employees.size(); i++) {
      assertThat(employees.get(i).equals(employeeOutDtosReceived.get().get(i)));
    }
  }

  @Test
  void password_change_successfully() {
    employee.setPassword("QXl1c2hpIzEyNA==");
    when(employeeRepository.findByEmailAndPassword(Mockito.anyString(), Mockito.anyString())).thenReturn(employee);

    Employee changedEmployee = new Employee();

    when(employeeRepository.save(employee)).thenReturn(changedEmployee);

    ChangePasswordInDto changePasswordInDto = new ChangePasswordInDto();
    changePasswordInDto.setOldPassword("QXl1c2hpIzEyNA==");
    changePasswordInDto.setNewPassword("QXl2c2hpIzEyVA==");

    employeeService.changePassword(changePasswordInDto, "palak@nucleusteq.com");

    Mockito.verify(employeeRepository, Mockito.times(1)).save(Mockito.any(Employee.class));

  }

  @Test
  void password_change_failed_when_employee_does_not_exist() {
    employee.setPassword("Ayushi#124");
    ChangePasswordInDto changePasswordInDto = new ChangePasswordInDto();

    assertThrows(ResourceNotFoundException.class, () -> {
      employeeService.changePassword(changePasswordInDto, "ayushi@nucleusteq.com");
    });
  }

  @Test
  void changePassword_fails_when_password_match_with_old_password() {
    employee.setPassword("QXl1c2hpIzEyNA==");
    when(employeeRepository.findByEmailAndPassword(Mockito.anyString(), Mockito.anyString())).thenReturn(employee);

    ChangePasswordInDto changePasswordInDto = new ChangePasswordInDto();
    changePasswordInDto.setOldPassword("QXl1c2hpIzEyNA==");
    changePasswordInDto.setNewPassword("QXl1c2hpIzEyNA==");

    assertThrows(CustomException.class, () -> {
      employeeService.changePassword(changePasswordInDto, "palak@nucleusteq.com");
    });
  }
  
  @Test
  void delete_employee_successfully() {
    
    when(employeeRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(employee));
    doNothing().when(employeeRepository).delete(employee);
    
    employeeService.deleteEmployeeById("ayushi@nucleusteq.com", 1);
    
    Mockito.verify(employeeRepository, Mockito.times(1)).delete(employee);
  }
  
  @Test
  void delete_employee_fails() {
    assertThrows(ResourceNotFoundException.class, ()->{
      employeeService.deleteEmployeeById("ayushi@nucleusteq.com", 1);
    });
  }
  
  @Test
  void delete_employee_fails_when_admin_tries_to_delete_itself() {
    when(employeeRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(employee));
    assertThrows(CustomException.class, ()->{
      employeeService.deleteEmployeeById("palak@nucleusteq.com", 1);
    });
  }
}
