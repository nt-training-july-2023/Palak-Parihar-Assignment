package com.grievance.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grievance.authentication.AuthenticatingUser;
import com.grievance.dto.DepartmentInDto;
import com.grievance.dto.DepartmentOutDto;
import com.grievance.exception.DepartmentAlreadyExists;
import com.grievance.service.DepartmentService;
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
public class DepartmentControllerTests {
  @Mock
  private DepartmentService departmentService;

  @Mock
  private AuthenticatingUser authenticatingUser;

  @InjectMocks
  private DepartmentController departmentController;

  @Autowired
  MockMvc mockMvc;

  private ObjectMapper objectMapper;

  private DepartmentInDto departmentInDto;
  private DepartmentOutDto departmentOutDto;

  @BeforeEach
  void setUp() {
    objectMapper = new ObjectMapper();

    departmentInDto = new DepartmentInDto();
    departmentOutDto = new DepartmentOutDto();

    mockMvc = MockMvcBuilders.standaloneSetup(departmentController).build();
  }

  @Test
  void when_authorised_user_save_department_successfully_return_saved_department()
    throws JsonProcessingException, Exception {
    when(authenticatingUser.checkIfUserIsAdmin(Mockito.anyString(), Mockito.anyString()))
      .thenReturn(true);

    when(departmentService.saveDepartment(Mockito.any(DepartmentInDto.class)))
      .thenReturn(Optional.ofNullable(departmentOutDto));

    mockMvc
      .perform(
        MockMvcRequestBuilders
          .post("/department/save")
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(departmentInDto))
          .header("email", "ayushi@gmail.com")
          .header("password", "Ayushi#123")
      )
      .andExpect(status().isCreated())
      .andDo(MockMvcResultHandlers.print());
  }
  
  @Test
  void when_authorised_user_save_department_fails_return_exception()
    throws JsonProcessingException, Exception {
    when(authenticatingUser.checkIfUserIsAdmin(Mockito.anyString(), Mockito.anyString()))
      .thenReturn(true);

    when(departmentService.saveDepartment(Mockito.any(DepartmentInDto.class)))
      .thenThrow(DepartmentAlreadyExists.class);

    mockMvc
      .perform(
        MockMvcRequestBuilders
          .post("/department/save")
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(departmentInDto))
          .header("email", "ayushi@gmail.com")
          .header("password", "Ayushi#123")
      )
      .andExpect(status().isConflict())
      .andDo(MockMvcResultHandlers.print());
  }

  @Test
  void when_unauthorised_user_save_department_return_fail()
    throws JsonProcessingException, Exception {
    when(authenticatingUser.checkIfUserIsAdmin(Mockito.anyString(), Mockito.anyString()))
      .thenReturn(false);

    mockMvc
      .perform(
        MockMvcRequestBuilders
          .post("/department/save")
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(departmentInDto))
          .header("email", "ayushi@gmail.com")
          .header("password", "Ayushi#123")
      )
      .andExpect(status().isUnauthorized())
      .andDo(MockMvcResultHandlers.print());
  }

  @Test
  void when_authorised_user_fetchAllDepartments_return_departments() throws Exception {
	  
	  when(authenticatingUser.checkIfUserIsAdmin(Mockito.anyString(), Mockito.anyString())).thenReturn(true);
	  
    List<DepartmentOutDto> departmentOutDtos = new ArrayList<DepartmentOutDto>();
    departmentOutDtos.add(departmentOutDto);
    when(departmentService.listAllDepartment()).thenReturn(Optional.ofNullable(departmentOutDtos));

    mockMvc
      .perform(
        MockMvcRequestBuilders
          .get("/department/listDepartments")
          .contentType(MediaType.APPLICATION_JSON)
          .header("email", "ayushi@gmail.com")
          .header("password", "Ayushi#123")
      )
      .andExpect(status().isAccepted())
      .andDo(MockMvcResultHandlers.print());
  }
  
  @Test
  void when_unauthorised_user_fetchAllDepartments_fails() throws Exception {
	  
	when(authenticatingUser.checkIfUserIsAdmin(Mockito.anyString(), Mockito.anyString())).thenReturn(false);
	  
    mockMvc
      .perform(
        MockMvcRequestBuilders
          .get("/department/listDepartments")
          .contentType(MediaType.APPLICATION_JSON)
          .header("email", "ayushi@gmail.com")
          .header("password", "Ayushi#123")
      )
      .andExpect(status().isUnauthorized())
      .andDo(MockMvcResultHandlers.print());
  }
  
}
