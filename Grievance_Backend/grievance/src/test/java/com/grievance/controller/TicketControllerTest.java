package com.grievance.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grievance.constants.ControllerURLS;
import com.grievance.dto.DepartmentInDto;
import com.grievance.dto.EmployeeInDto;
import com.grievance.dto.TicketInDto;
import com.grievance.dto.TicketOutDto;
import com.grievance.dto.TicketOutWOComment;
import com.grievance.dto.TicketUpdateDto;
import com.grievance.entity.Status;
import com.grievance.entity.TicketType;
import com.grievance.exception.ResourceNotFoundException;
import com.grievance.service.TicketService;

@ExtendWith(MockitoExtension.class)
public class TicketControllerTest {

  @Mock
  private TicketService ticketService;

  @InjectMocks
  private TicketController ticketController;

  @Autowired
  private MockMvc mockMvc;

  private ObjectMapper objectMapper;

  private TicketInDto ticketInDto;
  private TicketOutDto ticketOutDto;
  private String baseURL = ControllerURLS.TICKET_BASE_URL;

  @BeforeEach
  void setUp() {
    ticketInDto = new TicketInDto();
    ticketInDto.setDepartment(new DepartmentInDto());
    ticketInDto.setDescription("Malfunction");
    ticketInDto.setEmployee(new EmployeeInDto());
    ticketInDto.setStatus(Status.BEING_ADDRESSED);
    ticketInDto.setTicketType(TicketType.GRIEVANCE);
    ticketInDto.setTitle("Title");

    ticketOutDto = new TicketOutDto();
    ticketOutDto.setDepartment(null);
    ticketOutDto.setDescription("Malfunction");
    ticketOutDto.setEmployee("ayushi@nucleusteq.com");
    ticketOutDto.setLastUpdated(new Date());
    ticketOutDto.setStatus(Status.BEING_ADDRESSED);
    ticketOutDto.setTicketType(TicketType.GRIEVANCE);

    objectMapper = new ObjectMapper();
    mockMvc = MockMvcBuilders.standaloneSetup(ticketController).build();
  }

@Test
	public void when_save_ticket_return_saved_ticket() throws JsonProcessingException, Exception {
		when(ticketService.saveTicket(Mockito.any(TicketInDto.class))).thenReturn(Optional.ofNullable(ticketOutDto));
		
		mockMvc.perform(MockMvcRequestBuilders.post(baseURL+ControllerURLS.SAVE_DATA)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(ticketInDto))
				).andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
	}

  @Test
  public void fetch_all_tickets() throws Exception {
    List<TicketOutWOComment> ticketOutDtos = new ArrayList<TicketOutWOComment>();

    when(ticketService.listAllTickets(Mockito.anyString(),
        Mockito.anyInt(), Mockito.any(Status.class), Mockito.anyBoolean(), Mockito.anyInt()))
        .thenReturn(Optional.of(ticketOutDtos));

    mockMvc
        .perform(MockMvcRequestBuilders
            .get(baseURL + ControllerURLS.GET_ALL_DATA)
            .contentType(MediaType.APPLICATION_JSON)
            .header("email", "ayushi@nucleusteq.com")
            .header("password", "Ayushi#123")
            .param("myTickets", "true")
            .param("department", "1")
            .param("status", "OPEN")
            .param("page", "0"))
        .andExpect(status().isOk())
        .andDo(MockMvcResultHandlers.print());
  }

@Test
	public void update_ticket_successfully() throws Exception {
		
		when(ticketService.updateTicket(Mockito.any(TicketUpdateDto.class), Mockito.anyInt(), Mockito.anyString())).thenReturn(Optional.of(ticketOutDto));
		
    mockMvc.perform(MockMvcRequestBuilders.put(baseURL+ControllerURLS.UPDATE_DATA_BY_ID)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(ticketInDto))
				.header("email", "ayushi@nucleusteq.com")
				.header("password", "Ayushi#123")
				.param("ticketId", "66")
				).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
	}

@Test
	public void update_ticket_failed() throws Exception {
		
		when(ticketService.updateTicket(Mockito.any(TicketUpdateDto.class), Mockito.anyInt(), Mockito.anyString())).thenThrow(ResourceNotFoundException.class);
		mockMvc.perform(MockMvcRequestBuilders.put(baseURL+ControllerURLS.UPDATE_DATA_BY_ID)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(ticketInDto))
				.header("email", "ayushi@nucleusteq.com")
				.header("password", "Ayushi#123")
				.param("ticketId", "66")
				).andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());
	}

@Test
	public void get_ticket_by_id_success() throws Exception {
		when(ticketService.findTicketByTicketId(Mockito.anyInt())).thenReturn(Optional.of(ticketOutDto));
		
		mockMvc.perform(MockMvcRequestBuilders.get(baseURL+ControllerURLS.GET_DATA_BY_ID)
				.contentType(MediaType.APPLICATION_JSON)
				.header("email", "ayushi@nucleusteq.com")
				.header("password", "Ayushi#123")
				.param("ticketId", "1")
				).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
	}

@Test
	public void get_ticket_by_id_fails() throws Exception {
		when(ticketService.findTicketByTicketId(Mockito.anyInt())).thenThrow(ResourceNotFoundException.class);
		
		mockMvc.perform(MockMvcRequestBuilders.get(baseURL+ControllerURLS.GET_DATA_BY_ID)
				.contentType(MediaType.APPLICATION_JSON)
				.header("email", "ayushi@nucleusteq.com")
				.header("password", "Ayushi#123")
				.param("ticketId", "1")
				).andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());
	}
}
