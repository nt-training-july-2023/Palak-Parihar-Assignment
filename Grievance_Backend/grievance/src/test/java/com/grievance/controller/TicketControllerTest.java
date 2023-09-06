package com.grievance.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grievance.authentication.AuthenticatingUser;
import com.grievance.dto.TicketInDto;
import com.grievance.dto.TicketOutDto;
import com.grievance.entity.Ticket;
import com.grievance.service.TicketService;

@ExtendWith(MockitoExtension.class)
public class TicketControllerTest {
	
	@Mock
	private TicketService ticketService;
	
	@Mock
	private AuthenticatingUser authenticatingUser;
	
	@InjectMocks
	private TicketController ticketController;
	
	@Autowired
	private MockMvc mockMvc;
	
	private ObjectMapper objectMapper;
	
	private TicketInDto ticketInDto;	
	private TicketOutDto ticketOutDto;
	
	
	@BeforeEach
	void setUp() {
		ticketInDto = new TicketInDto();
		ticketOutDto = new TicketOutDto();
		objectMapper = new ObjectMapper();
		mockMvc = MockMvcBuilders.standaloneSetup(ticketController).build();
	}
	
	@Test
	public void when_save_ticket_return_saved_ticket() throws JsonProcessingException, Exception {
		when(ticketService.saveTicket(Mockito.any(TicketInDto.class))).thenReturn(Optional.ofNullable(ticketOutDto));
		
		mockMvc.perform(MockMvcRequestBuilders.post("/ticket/addTicket")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(ticketInDto))
				).andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void fetch_all_employees() throws Exception {
		List<TicketOutDto> ticketOutDtos = new ArrayList<TicketOutDto>();
		
		when(ticketService.listOfAllTickets()).thenReturn(Optional.of(ticketOutDtos));
		
		mockMvc.perform(MockMvcRequestBuilders.get("/ticket/listAllTickets")
				.contentType(MediaType.APPLICATION_JSON)
				).andExpect(status().isAccepted()).andDo(MockMvcResultHandlers.print());
	}
}

