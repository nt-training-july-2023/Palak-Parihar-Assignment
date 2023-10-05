package com.grievance.service;

import static org.assertj.core.api.Assertions.assertThat;
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
import com.grievance.exception.ResourceNotFoundException;
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

  private Employee employee;

  private Department department;
  @Mock
  ModelMapper modelMapper;

  @BeforeEach
  void setUp() {

    department = new Department("HR");
    department.setDepartmentId(101);
    employee = new Employee(1,"ayushi@nucleusteq.com", "Full Name", "QWertf", UserType.ADMIN, true, department);
    ticket = new Ticket("Reimbursement", TicketType.GRIEVANCE, department, "Description", Status.BEING_ADDRESSED, new Date(),
        employee);
    ticket.setDateOpened(new Date());

    List<Ticket> tickets = new ArrayList<Ticket>();
    tickets = new ArrayList<Ticket>();

    Comment comment1 = new Comment();
    comment1.setDescription("Hello1");
    comment1.setUserName("ayushi@nucleusteq.com");

    Comment comment2 = new Comment();
    comment2.setDescription("Hello2");
    comment2.setUserName("ayushi@nucleusteq.com");

    List<Comment> comments = new ArrayList<Comment>();
    comments.add(comment1);
    comments.add(comment2);

    ticket = new Ticket("Reimbursement", TicketType.GRIEVANCE, department, "Description", Status.BEING_ADDRESSED, new Date(),
        employee);
    ticket.setComments(comments);
    ticket.setDateOpened(new Date());
    ticket.setTicketId(1);
    
    ticketInDto = new TicketInDto();
    ticketInDto.setDepartment(new DepartmentInDto());
    ticketInDto.setDescription("Reimbursement");
    ticketInDto.setEmployee(new EmployeeInDto());
    ticketInDto.setStatus(Status.BEING_ADDRESSED);
    ticketInDto.setTicketType(TicketType.GRIEVANCE);
    ticketInDto.setTitle("Reimbursement");

    ticketOutWOComment = new TicketOutWOComment();
    ticketOutWOComment.setTicketId(1);
    ticketOutWOComment.setDepartment("FINANCE");
    ticketOutWOComment.setEmployee("ayushi@nucleusteq.com");
    ticketOutWOComment.setTicketType(TicketType.GRIEVANCE);
    ticketOutWOComment.setTitle("Reimbursement");
    
    ticketOutDto = new TicketOutDto();
    ticketOutDto.setTicketId(1);
    ticketOutDto.setDepartment("FINANCE");
    ticketOutDto.setEmployee("ayushi@nucleusteq.com");
    ticketOutDto.setTicketType(TicketType.GRIEVANCE);
    ticketOutDto.setTitle("Reimbursement");
    
    ticketUpdateDto = new TicketUpdateDto();
    ticketUpdateDto.setDescription("Hello");
    ticketUpdateDto.setStatus(Status.BEING_ADDRESSED);

    lenient().when(modelMapper.map(ticketInDto, Ticket.class)).thenReturn(ticket);

    lenient().when(modelMapper.map(ticket, TicketOutDto.class)).thenReturn(ticketOutDto);

    lenient().when(modelMapper.map(ticket, TicketOutWOComment.class)).thenReturn(ticketOutWOComment);

  }

@Test
	void when_save_employee_successfully_return_employee() {
		when(ticketRepository.save(Mockito.any(Ticket.class))).thenReturn(ticket);
		
		Optional<TicketOutDto> dto = ticketService.saveTicket(ticketInDto);
		
assertThat(dto.equals(ticket));
	}

  @Test
  void fetch_all_tickets() {
    List<Ticket> listOfTickets = new ArrayList<Ticket>();
    listOfTickets.add(ticket);
    Page<Ticket> list = new PageImpl<Ticket>(listOfTickets);
    when(ticketRepository.findAll(Mockito.any(PageRequest.class))).thenReturn(list);

    List<TicketOutWOComment> list2 = ticketService.findAll(0).get();

    for (int i = 0; i < list2.size(); i++) {
      assertThat(list2.get(i).equals(listOfTickets.get(i)));
    }

  }


  @Test
  void ticket_updated_successfully() {
    ticket.setTicketId(66);
    
    when(employeeRepository.findByEmail(Mockito.eq("ayushi@nucleusteq.com"))).thenReturn(employee);

    when(ticketRepository.findById(Mockito.eq(66))).thenReturn(Optional.of(ticket));

    when(ticketRepository.save(Mockito.eq(ticket))).thenReturn(ticket);
    
    Optional<TicketOutDto> ticketOutDto = ticketService.updateTicket(ticketUpdateDto, 66, "ayushi@nucleusteq.com");

    System.out.println(ticketOutDto);
    assertThat(ticketUpdateDto.equals(ticketOutDto));
  }

  @Test
  void ticket_updated_fails() {
    ticket.setTicketId(66);
    ticket.setDepartment(department);

    employee.getDepartment().setDepartmentName("HR");

    when(employeeRepository.findByEmail(Mockito.anyString())).thenReturn(null);

    assertThrows(ResourceNotFoundException.class, () -> {
      ticketService.updateTicket(ticketUpdateDto, 66, "ayushi@gmail.com");
    });
  }

  @Test
  void ticket_updated_fails_when_ticket_not_exist() {
    when(employeeRepository.findByEmail(Mockito.anyString())).thenReturn(employee);

    when(ticketRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

    assertThrows(ResourceNotFoundException.class, () -> {
      ticketService.updateTicket(ticketUpdateDto, 66, "ayushi@gmail.com");
    });
  }

  @Test
  void when_find_ticket_by_id_succeed() {
    ticket.setTicketId(1);
    
    when(ticketRepository.findById(Mockito.eq(1))).thenReturn(Optional.of(ticket));
        
    TicketOutDto result = ticketService.findTicketById(1).get();
    assertThat(result.equals(ticket));
    
  }

  @Test
  void when_find_ticket_by_id_fails() {
    when(ticketRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

    assertThrows(ResourceNotFoundException.class, () -> {
      ticketService.findTicketById(1);
    });
  }
  
  @Test
  void when_update_tickets_fails() {
    Employee employee1 = new Employee(1, "sneha@nucleusteq.com", "Sneha", "WEDFGHJ==", UserType.MEMBER, false, new Department("CRM"));
    ticket.setEmployee(new Employee());
    when(employeeRepository.findByEmail(Mockito.anyString())).thenReturn(employee1);
    when(ticketRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(ticket));
    
    assertThrows(UnauthorisedUserException.class, () -> {
      ticketService.updateTicket(ticketUpdateDto, 1, "ayushi@nucleusteq.com");
    });
  }
  
}
