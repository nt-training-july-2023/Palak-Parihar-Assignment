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

import com.grievance.dto.TicketOutWOComment;
import com.grievance.entity.Department;
import com.grievance.entity.Employee;
import com.grievance.entity.Status;
import com.grievance.entity.Ticket;
import com.grievance.entity.TicketType;
import com.grievance.entity.UserType;
import com.grievance.exception.ResourceNotFoundException;
import com.grievance.repository.DepartmentRepository;
import com.grievance.repository.EmployeeRepository;
import com.grievance.repository.TicketRepository;

@ExtendWith(MockitoExtension.class)
public class TicketListServiceTest {

  @Mock
  private TicketRepository ticketRepository;

  @Mock
  private DepartmentRepository departmentRepository;

  @Mock
  private EmployeeRepository employeeRepository;

  @InjectMocks
  private TicketServiceImpl ticketService;

  @Mock
  private TicketService service;

  private TicketOutWOComment ticketOutWOComment;

  private Ticket ticket;

  private Employee employee;

  private Department department;

  List<Ticket> tickets;

  @Mock
  ModelMapper modelMapper;

  @BeforeEach
  void setUp() {
    department = new Department("HR");
    employee = new Employee("ayushi@nucleusteq.com", "Full Name", "QWertf", UserType.ADMIN, true, department, null);
    ticket = new Ticket("Reimbursement", TicketType.GRIEVANCE, department, "Description", Status.BEING_ADDRESSED, new Date(),
        employee);

    tickets = new ArrayList<Ticket>();

    lenient().when(modelMapper.map(ticket, TicketOutWOComment.class)).thenReturn(ticketOutWOComment);

  }

  @Test
  void list_tickets_by_status() {   
        
    when(ticketRepository.findByStatus(Mockito.eq(Status.BEING_ADDRESSED), Mockito.any(PageRequest.class))).thenReturn(tickets);
    
    Optional<List<TicketOutWOComment>> result = ticketService.listTicketsByStatus(Status.BEING_ADDRESSED, 1);
    
    assertThat(result).isNotNull();

    assertThat(result).hasValueSatisfying(ticketOutWOComments -> {
      assertThat(ticketOutWOComments).hasSize(tickets.size());
    });
  }

  @Test
  void list_tickets_raised_by_user() {

    tickets.add(ticket);

    when(employeeRepository.findByEmail(Mockito.anyString())).thenReturn(employee);

    when(ticketRepository.findByEmployee(Mockito.eq(employee), Mockito.any(PageRequest.class))).thenReturn(tickets);

    Optional<List<TicketOutWOComment>> result = ticketService.listTicketsRaisedByUser(1, "ayushi@nucleusteq.com");

    assertThat(result).isNotNull();

    assertThat(result).hasValueSatisfying(ticketOutWOComments -> {
      assertThat(ticketOutWOComments).hasSize(tickets.size());
    });
  }

  @Test
  void list_tickets_raised_by_fails() {
    assertThrows(ResourceNotFoundException.class, () -> {
      ticketService.listTicketsRaisedByUser(1, "ayushi@nucleusteq.com");
    });
  }

  @Test
  void list_tickets_by_department_name() {

    tickets.add(ticket);

    when(employeeRepository.findByEmail(Mockito.anyString())).thenReturn(employee);

    when(ticketRepository.findByDepartment(Mockito.eq(department), Mockito.any(PageRequest.class))).thenReturn(tickets);

    Optional<List<TicketOutWOComment>> result = ticketService
        .listOfAllTicketsByEmployeeDepartment("ayushi@nucleusteq.com", 1);

    assertThat(result).isNotNull();

    assertThat(result).hasValueSatisfying(ticketOutWOComments -> {
      assertThat(ticketOutWOComments).hasSize(tickets.size());
    });
  }

  @Test
  void list_tickets_department_name_fails() {
    assertThrows(ResourceNotFoundException.class, () -> {
      ticketService.listOfAllTicketsByEmployeeDepartment("ayushi@nucleusteq.com", 1);
    });
  }

  @Test
  void list_tickets_by_department_and_status() {

    tickets.add(ticket);

    when(employeeRepository.findByEmail(Mockito.anyString())).thenReturn(employee);

    when(ticketRepository.findByDepartmentAndStatus(Mockito.eq(department),
        Mockito.any(Status.class),
        Mockito.any(PageRequest.class))).thenReturn(tickets);

    Optional<List<TicketOutWOComment>> result = ticketService
        .listTicketsByUserDepartmentAndStatus("ayushi@nucleusteq.com", Status.BEING_ADDRESSED, 1);

    assertThat(result).isNotNull();

    assertThat(result).hasValueSatisfying(ticketOutWOComments -> {
      assertThat(ticketOutWOComments).hasSize(tickets.size());
    });
  }

  @Test
  void list_tickets_by_department_and_status_fails() {
    assertThrows(ResourceNotFoundException.class, () -> {
      ticketService.listTicketsByUserDepartmentAndStatus("ayushi@nucleusteq.com", Status.BEING_ADDRESSED, 1);
    });
  }

  @Test
  void list_tickets_by_status_employee() {

    tickets.add(ticket);

    when(employeeRepository.findByEmail(Mockito.anyString())).thenReturn(employee);

    when(ticketRepository.findByStatusAndEmployee(
        Mockito.eq(Status.BEING_ADDRESSED),
        Mockito.eq(employee),
        Mockito.any(PageRequest.class))).thenReturn(tickets);

    Optional<List<TicketOutWOComment>> result = ticketService
        .listTicketByStatusAndEmployee(1, Status.BEING_ADDRESSED, "ayushi@nucleusteq.com");

    assertThat(result).isNotNull();

    assertThat(result).hasValueSatisfying(ticketOutWOComments -> {
      assertThat(ticketOutWOComments).hasSize(tickets.size());
    });
  }

  @Test
  void list_tickets_by_department_and_fails() {
    assertThrows(ResourceNotFoundException.class, () -> {
      ticketService
          .listTicketByStatusAndEmployee(1, Status.BEING_ADDRESSED, "ayushi@nucleusteq.com");
    });
  }

  @Test
  void list_of_all_tickets() {
    tickets.add(ticket);
    Page<Ticket> page = new PageImpl<Ticket>(tickets);
    when(ticketRepository.findAll(Mockito.any(PageRequest.class))).thenReturn(page);

    Optional<List<TicketOutWOComment>> result = ticketService
        .listOfAllTickets(Mockito.anyInt());

    assertThat(result).isNotNull();

    assertThat(result).hasValueSatisfying(ticketOutWOComments -> {
      assertThat(ticketOutWOComments).hasSize(tickets.size());
    });
  }

  @Test
  void list_all_tickets_return_list_ticket_by_status() {
    when(employeeRepository.existsByEmailAndUserType(Mockito.anyString(), Mockito.any(UserType.class))).thenReturn(true);

    Optional<List<TicketOutWOComment>> result = ticketService.listAllTickets("ayushi@nucleusteq.com",0, Status.OPEN,null);
    assertThat(result).isNotNull();
    
    assertThat(result).hasValueSatisfying(ticketOutWOComments -> {
      assertThat(ticketOutWOComments).hasSize(tickets.size());
    });
  }

  @Test
  void list_all_tickets_return_list_of_all_tickets() {
    when(employeeRepository.existsByEmailAndUserType(Mockito.anyString(), Mockito.any(UserType.class))).thenReturn(true);
    tickets.add(ticket);
    Page<Ticket> page = new PageImpl<Ticket>(tickets);
    when(ticketRepository.findAll(Mockito.any(PageRequest.class))).thenReturn(page);

    Optional<List<TicketOutWOComment>> result = ticketService.listAllTickets("ayushi@nucleusteq.com",0, null,null);
    assertThat(result).isNotNull();
    
    assertThat(result).hasValueSatisfying(ticketOutWOComments -> {
      assertThat(ticketOutWOComments).hasSize(tickets.size());
    });
  }

  @Test
  void list_all_tickets_if_not_admin_when_status_is_null() {
    when(employeeRepository.existsByEmailAndUserType(Mockito.anyString(), Mockito.any(UserType.class))).thenReturn(false);
    when(employeeRepository.findByEmail(Mockito.anyString())).thenReturn(employee);
    Optional<List<TicketOutWOComment>> result = ticketService.listAllTickets("ayushi@nucleusteq.com",0, null,null);
    
    assertThat(result).isNotNull();
    
    assertThat(result).hasValueSatisfying(ticketOutWOComments -> {
      assertThat(ticketOutWOComments).hasSize(tickets.size());
    });
  }
  
  @Test
  void list_all_tickets_if_not_admin_when_status_is_not_null() {
    when(employeeRepository.existsByEmailAndUserType(Mockito.anyString(), Mockito.any(UserType.class))).thenReturn(false);
    when(employeeRepository.findByEmail(Mockito.anyString())).thenReturn(employee);
    Optional<List<TicketOutWOComment>> result = ticketService.listAllTickets("ayushi@nucleusteq.com",0, Status.OPEN,null);
    
    assertThat(result).isNotNull();
    
    assertThat(result).hasValueSatisfying(ticketOutWOComments -> {
      assertThat(ticketOutWOComments).hasSize(tickets.size());
    });
  }
  
  @Test
  void list_all_tickets_return_raised_by_user() {
    when(employeeRepository.existsByEmailAndUserType(Mockito.anyString(), Mockito.any(UserType.class))).thenReturn(false);
    when(employeeRepository.findByEmail(Mockito.anyString())).thenReturn(employee);
    Optional<List<TicketOutWOComment>> result = ticketService.listAllTickets("ayushi@nucleusteq.com",0, null,true);
    
    assertThat(result).isNotNull();
    
    assertThat(result).hasValueSatisfying(ticketOutWOComments -> {
      assertThat(ticketOutWOComments).hasSize(tickets.size());
    });
  }
  
  @Test
  void list_all_tickets_return_raised_by_user_status() {
    when(employeeRepository.existsByEmailAndUserType(Mockito.anyString(), Mockito.any(UserType.class))).thenReturn(false);
    when(employeeRepository.findByEmail(Mockito.anyString())).thenReturn(employee);
    Optional<List<TicketOutWOComment>> result = ticketService.listAllTickets("ayushi@nucleusteq.com",0, Status.BEING_ADDRESSED,true);
    
    assertThat(result).isNotNull();
    
    assertThat(result).hasValueSatisfying(ticketOutWOComments -> {
      assertThat(ticketOutWOComments).hasSize(tickets.size());
    });
  }
}
