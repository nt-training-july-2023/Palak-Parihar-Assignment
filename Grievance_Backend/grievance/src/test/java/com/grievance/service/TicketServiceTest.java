package com.grievance.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

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
import org.modelmapper.ModelMapper;

import com.grievance.dto.DepartmentInDto;
import com.grievance.dto.EmployeeInDto;
import com.grievance.dto.TicketInDto;
import com.grievance.dto.TicketOutDto;
import com.grievance.entity.Department;
import com.grievance.entity.Employee;
import com.grievance.entity.Status;
import com.grievance.entity.Ticket;
import com.grievance.entity.TicketType;
import com.grievance.exception.TicketNotFoundException;
import com.grievance.repository.DepartmentRepository;
import com.grievance.repository.TicketRepository;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {
	
	@Mock
	private TicketRepository ticketRepository;
	
	@Mock
	private DepartmentRepository departmentRepository;
	
	@InjectMocks
	private TicketServiceImpl ticketService;
	
	
	private TicketInDto ticketInDto;
	
	private TicketOutDto ticketOutDto;
	
	private Ticket ticket;
	
	@Mock
	ModelMapper modelMapper;
	
	@BeforeEach
	void setUp() {
		DepartmentInDto department = new DepartmentInDto();
		department.setDepartmentName("FINANCE");
		ticketInDto = new TicketInDto();
		ticketInDto.setDepartment(department);
		ticketInDto.setDescription("Reimbursement");
		ticketInDto.setEmployeeInDto(new EmployeeInDto());
		ticketInDto.setStatus(Status.INPROGRESS);
		ticketInDto.setTicketType(TicketType.GRIEVANCE);
		ticketInDto.setTitle("Reimbursement");
		
		
		ticketOutDto = new TicketOutDto();
		ticketOutDto.setComments(null);
		ticketOutDto.setDepartment("FINANCE");
		ticketOutDto.setDescription("Reimbursement");
		ticketOutDto.setEmployee(null);
		ticketOutDto.setStatus(Status.INPROGRESS);
		ticketOutDto.setTitle("Reimbursement");
		
		ticket = new Ticket();
		ticket.setComments(null);
		ticket.setDepartment(new Department("FINANCE"));
		ticket.setDescription("Reimbursement");
		ticket.setEmployee(new Employee());
		ticket.setStatus(Status.INPROGRESS);
		ticket.setTitle("Reimbursement");
		ticket.setDateOpened(new Date());
		ticket.setLastUpdated(new Date());
		
		lenient().when(modelMapper.map(ticketInDto, Ticket.class)).thenReturn(ticket);
		
		lenient().when(modelMapper.map(ticket, TicketOutDto.class)).thenReturn(ticketOutDto);
	}
	
	@Test
	void when_save_employee_successfully_return_employee() {
		when(ticketRepository.save(Mockito.any(Ticket.class))).thenReturn(ticket);
		
		Optional<TicketOutDto> dto = ticketService.saveTicket(ticketInDto);
		
		assertEquals(dto.get().getTitle(), ticketOutDto.getTitle());
	}
	
	@Test
	void fetch_all_tickets() {
		List<TicketOutDto> dtos = new ArrayList<TicketOutDto>();
		dtos.add(ticketOutDto);
		List<Ticket> list = new ArrayList<Ticket>();
		list.add(ticket);
		when(ticketRepository.findAll()).thenReturn(list);
		
		Optional<List<TicketOutDto>> list2 = ticketService.listOfAllTickets();
		assertEquals(list.get(0).getTitle(), list2.get().get(0).getTitle());
	}
	
	@Test
	void filter_list_of_tickets_by_department_name() {
		List<TicketOutDto> ticketOutDtos = new ArrayList<TicketOutDto>();
		ticketOutDtos.add(ticketOutDto);
		
		List<Ticket> list = new ArrayList<Ticket>();
		list.add(ticket);
		when(departmentRepository.findByDepartmentName("FINANCE")).thenReturn(new Department("FINANCE"));
		
		when(ticketRepository.findByDepartment(Mockito.any(Department.class))).thenReturn(list);
		
		Optional<List<TicketOutDto>> optional = ticketService.listOfAllTicketsByDepartmentName("FINANCE");
		
		assertEquals("FINANCE", optional.get().get(0).getDepartment());
	}
	
	@Test
	void ticket_updated_successfully() {
		ticket.setTicketId(66);
		Department department = new Department();
		department.setDepartmentId(503);
		department.setDepartmentName("HR");
		ticket.setDepartment(department);
		
		
		Ticket ticket1 = new Ticket();
		ticket1.setTicketId(66);
		ticket1.setDescription("Hello");
		ticket1.setDepartment(department);
		
		ticketInDto.setTicketId(66);
		ticketOutDto.setTicketId(66);
		
		when(ticketRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(ticket));
		
		when(ticketRepository.save(Mockito.any(Ticket.class))).thenReturn(ticket);
	
		Optional<TicketOutDto> optional = ticketService.updateTicket(ticketInDto, 66);
		System.out.println(modelMapper.map(ticket, TicketOutDto.class).getTicketId());
		System.out.print(optional);
//		assertEquals(ticket.getTicketId(), optional.get().getTicketId());
//		assertEquals(ticket.getDescription(),optional.get().getDescription());
	}
	
	@Test
	void ticket_updated_fails() {
		ticket.setTicketId(66);
		Department department = new Department();
		department.setDepartmentId(503);
		department.setDepartmentName("HR");
		ticket.setDepartment(department);
		
		
		Ticket ticket1 = new Ticket();
		ticket1.setTicketId(66);
		ticket1.setDescription("Hello");
		ticket1.setDepartment(department);
		
		ticketInDto.setTicketId(66);
		
		when(ticketRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		
		assertThrows(TicketNotFoundException.class, ()->{
			ticketService.updateTicket(ticketInDto, 66);
		});
	}
}
