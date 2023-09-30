package com.grievance.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grievance.constants.ControllerURLS;
import com.grievance.dto.DepartmentInDto;
import com.grievance.dto.DepartmentOutDto;
import com.grievance.exception.RecordAlreadyExistException;
import com.grievance.exception.ResourceNotFoundException;
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

  @InjectMocks
  private DepartmentController departmentController;

  @Autowired
  MockMvc mockMvc;

  private ObjectMapper objectMapper;

  private DepartmentInDto departmentInDto;
  private DepartmentOutDto departmentOutDto;

  private String baseURL = ControllerURLS.DEPARTMENT_BASE_URL;

  @BeforeEach
  void setUp() {
    objectMapper = new ObjectMapper();

    departmentInDto = new DepartmentInDto();
    departmentOutDto = new DepartmentOutDto();

    mockMvc = MockMvcBuilders.standaloneSetup(departmentController).build();
  }

  @Test
  void whenuser_save_department_successfully_return_saved_department()
    throws JsonProcessingException, Exception {

    when(departmentService.saveDepartment(Mockito.any(DepartmentInDto.class)))
      .thenReturn(Optional.ofNullable(departmentOutDto));

    mockMvc
      .perform(
        MockMvcRequestBuilders
          .post(baseURL+ControllerURLS.SAVE_DATA)
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(departmentInDto))
          .header("email", "ayushi@gmail.com")
          .header("password", "Ayushi#123")
      )
      .andExpect(status().isCreated())
      .andDo(MockMvcResultHandlers.print());
  }

  @Test
  void when_save_department_fails_return_exception()
    throws JsonProcessingException, Exception {

    when(departmentService.saveDepartment(Mockito.any(DepartmentInDto.class)))
      .thenThrow(RecordAlreadyExistException.class);

    mockMvc
      .perform(
        MockMvcRequestBuilders
          .post(baseURL+ControllerURLS.SAVE_DATA)
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(departmentInDto))
          .header("email", "ayushi@gmail.com")
          .header("password", "Ayushi#123")
      )
      .andExpect(status().isConflict())
      .andDo(MockMvcResultHandlers.print());
  }

  @Test
  void when_fetchAllDepartments_fails() throws Exception {

    List<DepartmentOutDto> departmentOutDtos = new ArrayList<DepartmentOutDto>();
    departmentOutDtos.add(departmentOutDto);
    when(departmentService.listAllDepartment(Mockito.anyInt())).thenReturn(Optional.ofNullable(departmentOutDtos));

    mockMvc
        .perform(
            MockMvcRequestBuilders
                .get(baseURL+ControllerURLS.GET_ALL_DATA)
                .contentType(MediaType.APPLICATION_JSON)
                .param("page", "0")
                .header("email", "ayushi@gmail.com")
                .header("password", "Ayushi#123"))
        .andExpect(status().isAccepted())
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  void when_delete_department_success() throws Exception {

    doNothing().when(departmentService).deleteDepartment(Mockito.anyInt());
    mockMvc
        .perform(
            MockMvcRequestBuilders
                .delete(baseURL+ControllerURLS.DELETE_DATA_BY_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .param("departmentId", "101")
                .header("email", "ayushi@gmail.com")
                .header("password", "Ayushi#123"))
        .andExpect(status().isNoContent())
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  void when_delete_department_fails() throws Exception {

    doThrow(ResourceNotFoundException.class).when(departmentService).deleteDepartment(Mockito.anyInt());
    mockMvc
    .perform(
        MockMvcRequestBuilders
            .delete(baseURL+ControllerURLS.DELETE_DATA_BY_ID)
            .contentType(MediaType.APPLICATION_JSON)
            .param("departmentId", "101")
            .header("email", "ayushi@gmail.com")
            .header("password", "Ayushi#123"))
    .andExpect(status().isNotFound())
    .andDo(MockMvcResultHandlers.print());
  }
}
