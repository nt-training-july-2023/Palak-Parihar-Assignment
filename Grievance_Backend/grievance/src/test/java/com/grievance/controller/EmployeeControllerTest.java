package com.grievance.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grievance.authentication.AuthenticatingUser;
import com.grievance.dto.ChangePasswordInDto;
import com.grievance.dto.EmployeeInDto;
import com.grievance.dto.EmployeeLoginDto;
import com.grievance.dto.EmployeeOutDto;
import com.grievance.entity.Employee;
import com.grievance.entity.UserType;
import com.grievance.exception.EmployeeAlreadyExistException;
import com.grievance.exception.EmployeeNotFoundException;
import com.grievance.exception.PasswordMatchException;
import com.grievance.service.EmployeeService;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {
  @Mock
  EmployeeService employeeService;

  @Mock
  private AuthenticatingUser authenticatingUser;

  @InjectMocks
  EmployeeController employeeController;

  private ObjectMapper objectMapper;

  @Autowired
  MockMvc mockMvc;

  private EmployeeOutDto employeeOutDto;

  private EmployeeLoginDto employeeLoginDto;

  private EmployeeInDto employeeInDto;

  @Autowired
  private Employee employee;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    employeeOutDto = new EmployeeOutDto();
    employeeOutDto.setEmail("example@nucleusteq.com");
    employeeOutDto.setDepartment(null);
    employeeOutDto.setFirstTimeUser(true);
    employeeOutDto.setFullName("Example ");
    employeeOutDto.setTickets(null);
    employeeOutDto.setUserType(UserType.MEMBER);

    employee = new Employee();
    employee.setEmail("palak@nucleusteq.com");
    employee.setPassword("Example#123");

    employeeLoginDto = new EmployeeLoginDto();
    employeeLoginDto.setEmail("palak@nucleusteq.com");

    employeeInDto = new EmployeeInDto();
    employeeInDto.setDepartmentDto(null);
    employeeInDto.setEmail("ayushi@nucleusteq.com");
    employeeInDto.setFullName("Ayushi");
    employeeInDto.setPassword("Ayushi#123");
    employeeInDto.setUserType(UserType.MEMBER);

    objectMapper = new ObjectMapper();
  }

  @Test
  void when_user_successfully_fetch_employees() throws Exception {
    List<EmployeeOutDto> employeeOutDtos = new ArrayList<EmployeeOutDto>();
    employeeOutDtos.add(employeeOutDto);

    when(employeeService.listAllEmployees()).thenReturn(Optional.of(employeeOutDtos));

    mockMvc.perform(MockMvcRequestBuilders.get("/employee/listAllEmployees")
        .contentType(MediaType.APPLICATION_JSON)
        .header("email", "ayushi@nucleusteq.com")
        .header("password", "Ayushi#123")).andExpect(status().isAccepted()).andDo(MockMvcResultHandlers.print());
  }

  @Test
    void when_successfully_login_return_employee() throws JsonProcessingException, Exception {
	  when(employeeService.loginEmployee(Mockito.any(EmployeeLoginDto.class))).thenReturn(Optional.of(employeeOutDto));
	  
	  mockMvc.perform(MockMvcRequestBuilders.post("/employee/login")
			  .contentType(MediaType.APPLICATION_JSON)
			  .content(objectMapper.writeValueAsString(employeeLoginDto))
			  ).andExpect(status().isAccepted()).andDo(MockMvcResultHandlers.print());
  }

  @Test
  void when_login_failed() throws JsonProcessingException, Exception {
    employeeLoginDto.setPassword("Example#123");
    when(employeeService.loginEmployee(Mockito.any(EmployeeLoginDto.class))).thenThrow(EmployeeNotFoundException.class);

    mockMvc.perform(MockMvcRequestBuilders.post("/employee/login")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(employeeLoginDto))).andExpect(status().isNotFound())
        .andDo(MockMvcResultHandlers.print());

  }

  @Test
    void when_save_employee_by_user_sucesses_return_saved_employee() throws JsonProcessingException, Exception {
    	
    	when(employeeService.saveEmployee(Mockito.any(EmployeeInDto.class))).thenReturn(Optional.ofNullable(employeeOutDto));
    	
    	mockMvc.perform(MockMvcRequestBuilders.post("/employee/saveEmployee")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(objectMapper.writeValueAsString(employeeInDto))
    			.header("email", "ayushi@nucleusteq.com")
    			.header("password", "Ayushi#123")
    			).andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
    }

  @Test
    void when_save_employee_by_user_fails() throws JsonProcessingException, Exception {
    	
    	when(employeeService.saveEmployee(Mockito.any(EmployeeInDto.class))).thenThrow(new EmployeeAlreadyExistException());
    	
    	mockMvc.perform(MockMvcRequestBuilders.post("/employee/saveEmployee")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(objectMapper.writeValueAsString(employeeInDto))
    			.header("email", "ayushi@nucleusteq.com")
    			.header("password", "Ayushi#123")
    			)	.andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());
    }

  @Test
  void when_change_password_by_authorised_user_success() throws JsonProcessingException, Exception {

    doNothing().when(employeeService).changePassword(Mockito.any(ChangePasswordInDto.class), Mockito.anyString());

    ChangePasswordInDto changePasswordInDto = new ChangePasswordInDto();
    changePasswordInDto.setOldPassword("Ayushi#123");
    changePasswordInDto.setNewPassword("Ayushi#125");

    mockMvc.perform(MockMvcRequestBuilders.put("/employee/changePassword")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(changePasswordInDto))
        .header("email", "ayushi@nucleusteq.com")
        .header("password", "Ayushi#123")).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
  }

  @Test
    void when_change_password_by_user_fails() throws JsonProcessingException, Exception {
    
    doThrow(PasswordMatchException.class).when(employeeService).changePassword(Mockito.any(ChangePasswordInDto.class), Mockito.anyString());
    
    	ChangePasswordInDto changePasswordInDto = new ChangePasswordInDto();
    	changePasswordInDto.setOldPassword("Ayushi#123");
    	changePasswordInDto.setNewPassword("Ayushi#123");
    	
    	mockMvc.perform(MockMvcRequestBuilders.put("/employee/changePassword")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(objectMapper.writeValueAsString(changePasswordInDto))
    			.header("email", "ayushi@nucleusteq.com")
    			.header("password", "Ayushi#123")
    			).andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());
    }
  
  @Test
  void when_delete_employee_success() throws Exception {
    
    doNothing().when(employeeService).deleteEmployeeById(Mockito.anyString());
    
    mockMvc.perform(MockMvcRequestBuilders.delete("/employee/delete")
        .contentType(MediaType.APPLICATION_JSON)
        .header("email", "ayushi@nucleusteq.com")
        .header("password", "Ayushi#123")
        .param("email", "ayushi@nucleusteq.com")
        ).andExpect(status().isNoContent()).andDo(MockMvcResultHandlers.print());
  }
  
  @Test
  
  void when_delete_employee_fails() throws Exception {
    
    doThrow(EmployeeNotFoundException.class).when(employeeService).deleteEmployeeById(Mockito.anyString());
    mockMvc.perform(MockMvcRequestBuilders.delete("/employee/delete")
        .contentType(MediaType.APPLICATION_JSON)
        .header("email", "ayushi@nucleusteq.com")
        .header("password", "Ayushi#123")
        .param("email", "ayushi@nucleusteq.com")
        ).andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());
  }


}
