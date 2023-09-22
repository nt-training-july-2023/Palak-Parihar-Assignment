package com.grievance.service;

import static org.assertj.core.api.Assertions.assertThat;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.grievance.dto.DepartmentInDto;
import com.grievance.dto.EmployeeInDto;
import com.grievance.dto.TicketInDto;
import com.grievance.dto.TicketOutDto;
import com.grievance.dto.TicketOutWOComment;
import com.grievance.dto.TicketUpdateDto;
import com.grievance.entity.Comment;
import com.grievance.entity.Department;
import com.grievance.entity.Employee;
import com.grievance.entity.Status;
import com.grievance.entity.Ticket;
import com.grievance.entity.TicketType;
import com.grievance.entity.UserType;
import com.grievance.exception.EmployeeNotFoundException;
import com.grievance.exception.TicketNotFoundException;
import com.grievance.exception.UnauthorisedUserException;
import com.grievance.repository.DepartmentRepository;
import com.grievance.repository.EmployeeRepository;
import com.grievance.repository.TicketRepository;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {
	
	@Mock
	private TicketRepository ticketRepository;
	
	@Mock
	private DepartmentRepository departmentRepository;
	
	@Mock
	private EmployeeRepository employeeRepository;
	
	@InjectMocks
	private TicketServiceImpl ticketService;
	
	
	private TicketInDto ticketInDto;
	
	private TicketOutDto ticketOutDto;
	
	private TicketOutWOComment ticketOutWOComment;
	
	private TicketUpdateDto ticketUpdateDto;
	
	private Ticket ticket;
	
	private DepartmentInDto departmentInDto;
	
	private Employee employee; 
	
	private Ticket updatedTicket; 
	
	private Department department;
	@Mock
	ModelMapper modelMapper;
	
	@BeforeEach
	void setUp() {
		
		department = new Department();
		department.setDepartmentId(503);
		department.setDepartmentName("FINANCE");
		
		employee = new Employee();
		employee.setDepartment(department);
		employee.setEmail("ayushi@nucleusteq,com");
		employee.setFirstTimeUser(true);
		employee.setFullName("Ayushi");
		employee.setPassword("Ayushi#124");
		employee.setUserType(UserType.ADMIN);
		
		departmentInDto = new DepartmentInDto();
		departmentInDto.setDepartmentName("FINANCE");
		ticketInDto = new TicketInDto();
		ticketInDto.setDepartment(departmentInDto);
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
		
		Comment comment1 = new Comment();
		comment1.setDescription("Hello1");
		comment1.setUserName("ayushi@nucleusteq.com");
		
		Comment comment2 = new Comment();
		comment2.setDescription("Hello2");
		comment2.setUserName("ayushi@nucleusteq.com");
		
		List<Comment> list = new ArrayList<Comment>();
		list.add(comment1);
		list.add(comment2);
		
		ticket = new Ticket();
		ticket.setComments(null);
		ticket.setDepartment(department);
		ticket.setDescription("Reimbursement");
		ticket.setEmployee(employee);
		ticket.setStatus(Status.INPROGRESS);
		ticket.setTitle("Reimbursement");
		ticket.setDateOpened(new Date());
		ticket.setLastUpdated(new Date());
		ticket.setComments(list);
		
	    updatedTicket = new Ticket();
		updatedTicket.setTicketId(66);
		updatedTicket.setDescription("Hello");
		updatedTicket.setDepartment(department);
		
		ticketUpdateDto = new TicketUpdateDto();
		ticketUpdateDto.setDescription("Hello");
		ticketUpdateDto.setStatus(Status.INPROGRESS);
		
		ticketOutWOComment = new TicketOutWOComment();
		ticketOutWOComment.setTicketId(1);
		ticketOutWOComment.setDepartment("FINANCE");
		ticketOutWOComment.setDescription("Reimbursement");
		ticketOutWOComment.setEmployee("ayushi@nucleusteq.com");
		ticketOutWOComment.setTicketType(TicketType.GRIEVANCE);
		ticketOutWOComment.setTitle("Reimbursement");
		
		lenient().when(modelMapper.map(ticketInDto, Ticket.class)).thenReturn(ticket);
		
		lenient().when(modelMapper.map(ticket, TicketOutDto.class)).thenReturn(ticketOutDto);
		
		lenient().when(modelMapper.map(ticket, TicketOutWOComment.class)).thenReturn(ticketOutWOComment);
		
		
		
	}
	
	@Test
	void when_save_employee_successfully_return_employee() {
		when(ticketRepository.save(Mockito.any(Ticket.class))).thenReturn(ticket);
		
		Optional<TicketOutDto> dto = ticketService.saveTicket(ticketInDto);
		
		assertEquals(dto.get().getTitle(), ticketOutDto.getTitle());
	}
	
	@Test
	void fetch_all_tickets() {
		List<TicketOutWOComment> dtos = new ArrayList<TicketOutWOComment>();
		dtos.add(ticketOutWOComment);
		List<Ticket> llist = new ArrayList<Ticket>();
		llist.add(ticket);
		Page<Ticket> list = new PageImpl<Ticket>(llist);
		when(ticketRepository.findAll(Mockito.any(PageRequest.class))).thenReturn(list);
		
		Optional<List<TicketOutWOComment>> list2 = ticketService.listOfAllTickets(0);
		System.out.println();
		assertEquals(llist.get(0).getTitle(), list2.get().get(0).getTitle());
	}
	
	@Test
	void filter_list_of_tickets_by_department_name() {
		List<TicketOutDto> ticketOutDtos = new ArrayList<TicketOutDto>();
		ticketOutDtos.add(ticketOutDto);
		
		List<Ticket> list = new ArrayList<Ticket>();
		list.add(ticket);
		when(departmentRepository.findByDepartmentName("FINANCE")).thenReturn(new Department("FINANCE"));
		
		when(ticketRepository.findByDepartment(Mockito.any(Department.class),
				PageRequest.of(0, 10).withSort(Sort.by("status")))).thenReturn(list);
		
		Optional<List<TicketOutWOComment>> optional = ticketService.listOfAllTicketsByDepartmentName("FINANCE");
		
		assertEquals("FINANCE", optional.get().get(0).getDepartment());
	}
	
	@Test
	void ticket_updated_successfully() {
		ticket.setTicketId(66);
		Department department = new Department();
		department.setDepartmentId(503);
		department.setDepartmentName("HR");
		ticket.setDepartment(department);	
			
		
		ticketInDto.setTicketId(66);
		ticketOutDto.setTicketId(66);
		
		when(employeeRepository.findByEmail(Mockito.anyString())).thenReturn(employee);
		
		when(ticketRepository.findById(66)).thenReturn(Optional.of(ticket));
		
		when(ticketRepository.save(ticket)).thenReturn(ticket);
					
		Optional<TicketOutDto> optional = ticketService.updateTicket(ticketUpdateDto, 66, "ayushi@nucleusteq.com");
		
		
		
		assertEquals(ticket.getTicketId(), optional.get().getTicketId());
		assertEquals(ticket.getDescription(),optional.get().getDescription());
	}
	
	@Test
	void ticket_updated_fails() {
		ticket.setTicketId(66);
		ticket.setDepartment(department);
		
		employee.getDepartment().setDepartmentName("HR");

		ticketInDto.setTicketId(66);
		
		when(employeeRepository.findByEmail(Mockito.anyString())).thenReturn(null);
		
				
		assertThrows(EmployeeNotFoundException.class, ()->{
			ticketService.updateTicket(ticketUpdateDto, 66, "ayushi@gmail.com");
		});
	}
	
	@Test
	void ticket_updated_fails_when_ticket_not_exist() {		
		ticketInDto.setTicketId(66);
		
		when(employeeRepository.findByEmail(Mockito.anyString())).thenReturn(employee);
		
		when(ticketRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		
		assertThrows(TicketNotFoundException.class, ()->{
			ticketService.updateTicket(ticketUpdateDto, 66, "ayushi@gmail.com");
		});
	}
	
	@Test
	void when_find_ticket_by_id_succeed() {
		ticket.setTicketId(1);
		when(ticketRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(ticket));
		
		Optional<TicketOutDto> optionalTicket = ticketService.findTicketByTicketId(1);
		
		assertEquals(ticket.getDescription(), optionalTicket.get().getDescription());
	}
	
	@Test
	void when_find_ticket_by_id_fails() {
		ticket.setTicketId(1);
		when(ticketRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
				
		assertThrows(TicketNotFoundException.class, ()->{
			ticketService.findTicketByTicketId(1);
		});
	}
}
