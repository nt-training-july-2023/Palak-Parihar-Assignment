package com.grievance.controller;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grievance.dto.EmployeeInDto;
import com.grievance.dto.EmployeeLoginDto;
import com.grievance.dto.EmployeeOutDto;
import com.grievance.entity.Employee;
import com.grievance.entity.UserType;
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
    employeeOutDto=new EmployeeOutDto();
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
    employeeInDto.setFirstTimeUser(true);
    employeeInDto.setFullName("Ayushi");
    employeeInDto.setPassword("Ayushi#123");
    employeeInDto.setUserType(UserType.MEMBER);
    
    objectMapper = new ObjectMapper();
  }

    @Test
    void when_authorised_user_successfully_fetch_employees() throws Exception {
	  List<EmployeeOutDto> employeeOutDtos = new ArrayList<EmployeeOutDto>();
	  employeeOutDtos.add(employeeOutDto);
	  
	  when(employeeService.checkUserIsAdmin(Mockito.anyString(), Mockito.anyString())).thenReturn(true);
	  
	  when(employeeService.listAllEmployees()).thenReturn(Optional.of(employeeOutDtos));
	  
	  
	  mockMvc.perform(MockMvcRequestBuilders.get("/listAllEmployees")
			  .contentType(MediaType.APPLICATION_JSON)
			  .param("email", "ayushi@nucleusteq.com")
  			  .param("password", "Ayushi#123")
			  ).andExpect(status().isAccepted()).andDo(MockMvcResultHandlers.print());
  }

    @Test
    void when_unauthorised_user_fails_to_fetch_employees() throws Exception {
	  
	  when(employeeService.checkUserIsAdmin(Mockito.anyString(), Mockito.anyString())).thenReturn(false);	  
	  
	  mockMvc.perform(MockMvcRequestBuilders.get("/listAllEmployees")
			  .contentType(MediaType.APPLICATION_JSON)
			  .param("email", "ayushi@nucleusteq.com")
  			  .param("password", "Ayushi#123")
			  ).andExpect(status().isUnauthorized()).andDo(MockMvcResultHandlers.print());
  }

    @Test
    void when_successfully_login_return_employee() throws JsonProcessingException, Exception {
	  when(employeeService.loginEmployee(Mockito.any(EmployeeLoginDto.class))).thenReturn(Optional.of(employeeOutDto));
	  
	  mockMvc.perform(MockMvcRequestBuilders.post("/login")
			  .contentType(MediaType.APPLICATION_JSON)
			  .content(objectMapper.writeValueAsString(employeeLoginDto))
			  ).andExpect(status().isAccepted()).andDo(MockMvcResultHandlers.print());
  }

    @Test
    void when_login_failed() throws JsonProcessingException, Exception {
    	employeeLoginDto.setPassword("Example#123");
	  when(employeeService.loginEmployee(Mockito.any(EmployeeLoginDto.class))).thenReturn(Optional.empty());
	  
	  mockMvc.perform(MockMvcRequestBuilders.post("/login")
			  .contentType(MediaType.APPLICATION_JSON)
			  .content(objectMapper.writeValueAsString(employeeLoginDto))
			  ).andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());
  }
    
    @Test
    void when_save_employee_by_authorised_user_sucesses_return_saved_employee() throws JsonProcessingException, Exception {
    	when(employeeService.checkUserIsAdmin(Mockito.anyString(), Mockito.anyString())).thenReturn(true);
    	
    	when(employeeService.saveEmployee(Mockito.any(EmployeeInDto.class))).thenReturn(Optional.ofNullable(employeeOutDto));
    	
    	mockMvc.perform(MockMvcRequestBuilders.post("/saveEmployee")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(objectMapper.writeValueAsString(employeeInDto))
    			.param("email", "ayushi@nucleusteq.com")
    			.param("password", "Ayushi#123")
    			).andExpect(status().isAccepted()).andDo(MockMvcResultHandlers.print());
    }
    
    @Test
    void when_save_employee_by_authorised_user_fails_return_user() throws JsonProcessingException, Exception {
    	when(employeeService.checkUserIsAdmin(Mockito.anyString(), Mockito.anyString())).thenReturn(true);
    	
    	when(employeeService.saveEmployee(Mockito.any(EmployeeInDto.class))).thenReturn(Optional.empty());
    	
    	mockMvc.perform(MockMvcRequestBuilders.post("/saveEmployee")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(objectMapper.writeValueAsString(employeeInDto))
    			.param("email", "ayushi@nucleusteq.com")
    			.param("password", "Ayushi#123")
    			).andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());
    }
    
    @Test
    void when_save_employee__by_unauthorised_user_return_status() throws JsonProcessingException, Exception {
        when(employeeService.checkUserIsAdmin(Mockito.anyString(), Mockito.anyString())).thenReturn(false);
    	    	
    	mockMvc.perform(MockMvcRequestBuilders.post("/saveEmployee")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(objectMapper.writeValueAsString(employeeInDto))
    			.param("email", "ayushi@nucleusteq.com")
    			.param("password", "Ayushi#123")
    			).andExpect(status().isUnauthorized()).andDo(MockMvcResultHandlers.print());
    }
    
    
}
















