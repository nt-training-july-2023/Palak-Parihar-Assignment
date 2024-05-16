package com.grievance.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
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
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.google.common.base.Converter;
import com.grievance.dto.TicketOutWOComment;
import com.grievance.entity.Department;
import com.grievance.entity.Employee;
import com.grievance.entity.Status;
import com.grievance.entity.Ticket;
import com.grievance.entity.TicketType;
import com.grievance.entity.UserType;
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

  private List<Ticket> listTickets;
  
  private Page<Ticket> tickets;

  @Mock
  ModelMapper modelMapper;
  
  @Spy
  Converter<Ticket, TicketOutWOComment> converter;

  @BeforeEach
  void setUp() {
    department = new Department();
    department.setDepartmentId(101);
    department.setDepartmentName("HR");
    employee = new Employee(1, "ayushi@nucleusteq.com", "Full Name",
        "QWertf", UserType.ADMIN, true, department);
    ticket = new Ticket("Reimbursement", TicketType.GRIEVANCE,
        department, "Description", Status.BEING_ADDRESSED, new Date(),
        employee);

    listTickets = new ArrayList<Ticket>();
    listTickets.add(ticket);
    
    tickets = new PageImpl<Ticket>(listTickets);

    lenient().when(modelMapper.map(ticket, TicketOutWOComment.class))
        .thenReturn(ticketOutWOComment);
    

  }


  @Test
  void list_all_tickets_return_list_ticket_by_status() {
    when(departmentRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(new Department()));
    when(employeeRepository.findByEmail(Mockito.anyString())).thenReturn(employee);
    when(ticketRepository.findByDepartmentAndStatus(Mockito.any(Department.class), Mockito.any(Status.class), Mockito.any(PageRequest.class))).thenReturn(tickets);
    List<TicketOutWOComment> result = ticketService.listAllTickets("ayushi@nucleusteq.com",0, Status.OPEN,null, 1).get().getContent();
    
//    assertThat(list).isNotNull();
    
//    List<TicketOutWOComment> result = list.get().getContent();
    
    for (int i = 0; i < result.size(); i++) {
      assertThat(result.get(i).equals(tickets.getContent().get(i)));
    }

  }

  @Test
  void list_all_tickets_return_list_of_all_tickets() {
    when(employeeRepository.findByEmail(Mockito.anyString())).thenReturn(employee);
    when(ticketRepository.findAll(Mockito.any(PageRequest.class))).thenReturn(tickets);

    Optional<Page<TicketOutWOComment>> result = ticketService.listAllTickets("ayushi@nucleusteq.com",0, null,null, null);
    assertThat(result).isNotNull();
    
    assertThat(result).hasValueSatisfying(ticketOutWOComments -> {
      assertThat(ticketOutWOComments).hasSize(tickets.getContent().size());
    });
  }

  @Test
  void list_all_tickets_if_not_admin_when_status_is_null() {
    when(departmentRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(department));
    when(employeeRepository.findByEmail(Mockito.anyString())).thenReturn(employee);
    when(ticketRepository.findByDepartment(Mockito.any(Department.class), Mockito.any(PageRequest.class))).thenReturn(tickets);
    Optional<Page<TicketOutWOComment>> result = ticketService.listAllTickets("ayushi@nucleusteq.com",0, null,null, 1);
    
    assertThat(result).isNotNull();
    
    assertThat(result).hasValueSatisfying(ticketOutWOComments -> {
      assertThat(ticketOutWOComments).hasSize(tickets.getContent().size());
    });
  }

  @Test
  void list_all_tickets_if_not_admin_when_status_is_not_null() {
    when(departmentRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(department));
    when(employeeRepository.findByEmail(Mockito.anyString())).thenReturn(employee);
    when(ticketRepository.findByDepartmentAndStatus(Mockito.any(Department.class), Mockito.any(Status.class), Mockito.any(PageRequest.class))).thenReturn(tickets);
    Optional<Page<TicketOutWOComment>> result = ticketService.listAllTickets("ayushi@nucleusteq.com",0, Status.OPEN,null,1);
    
    assertThat(result).isNotNull();
    
    assertThat(result).hasValueSatisfying(ticketOutWOComments -> {
      assertThat(ticketOutWOComments).hasSize(tickets.getContent().size());
    });
  }

  @Test
  void list_all_tickets_return_raised_by_user() {
    when(employeeRepository.findByEmail(Mockito.anyString())).thenReturn(employee);
    when(ticketRepository.findByEmployee(Mockito.any(Employee.class), Mockito.any(PageRequest.class))).thenReturn(tickets);
    Optional<Page<TicketOutWOComment>> result = ticketService.listAllTickets("ayushi@nucleusteq.com",0, null,true,null);
    
    assertThat(result).isNotNull();
    
    assertThat(result).hasValueSatisfying(ticketOutWOComments -> {
      assertThat(ticketOutWOComments).hasSize(tickets.getContent().size());
    });
  }

  @Test
  void list_all_tickets_return_raised_by_user_by_status() {
    when(departmentRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(new Department()));
    when(employeeRepository.findByEmail(Mockito.anyString())).thenReturn(employee);
    when(ticketRepository.findByDepartmentAndStatusAndEmployee(Mockito.any(Department.class), Mockito.any(Status.class), Mockito.any(Employee.class), Mockito.any(PageRequest.class))).thenReturn(tickets);
    Optional<Page<TicketOutWOComment>> result = ticketService.listAllTickets("ayushi@nucleusteq.com",0, Status.BEING_ADDRESSED,true,1);
    
    assertThat(result).isNotNull();
    
    assertThat(result).hasValueSatisfying(ticketOutWOComments -> {
      assertThat(ticketOutWOComments).hasSize(tickets.getContent().size());
    });
  }


  @Test
  void list_all_tickets_if_admin_by_status() {
    when(employeeRepository.findByEmail(Mockito.anyString())).thenReturn(employee);
    when(ticketRepository.findByStatus(Mockito.any(Status.class), Mockito.any(PageRequest.class))).thenReturn(tickets);
    Optional<Page<TicketOutWOComment>> result = ticketService.listAllTickets("ayushi@nucleusteq.com",0,Status.OPEN, null, null);
    
    assertThat(result).isNotNull();
    
    assertThat(result).hasValueSatisfying(ticketOutWOComments -> {
      assertThat(ticketOutWOComments).hasSize(tickets.getContent().size());
    });
  }

  @Test
  void list_all_tickets_if_admin_by_department() {
    when(departmentRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(new Department()));
    when(employeeRepository.findByEmail(Mockito.anyString())).thenReturn(employee);
    when(ticketRepository.findByDepartmentAndEmployee(Mockito.any(Department.class),Mockito.any(Employee.class), Mockito.any(PageRequest.class))).thenReturn(tickets);
    Optional<Page<TicketOutWOComment>> result = ticketService.listAllTickets("ayushi@nucleusteq.com",0,null, true, 1);
    
    assertThat(result).isNotNull();
    
    assertThat(result).hasValueSatisfying(ticketOutWOComments -> {
      assertThat(ticketOutWOComments).hasSize(tickets.getContent().size());
    });
  }

  @Test
  void list_tickets_raised_by_user_success() {
    when(employeeRepository.findByEmail(Mockito.anyString())).thenReturn(employee);
    when(ticketRepository.findByEmployee(Mockito.any(Employee.class), Mockito.any(PageRequest.class))).thenReturn(tickets);
    Optional<Page<TicketOutWOComment>> result = ticketService.listAllTickets("ayushi@nucleusteq.com",0,null, true, null);

    assertThat(result).isNotNull();
    
    assertThat(result).hasValueSatisfying(ticketOutWOComments -> {
      assertThat(ticketOutWOComments).hasSize(tickets.getContent().size());
    });
    
  }
  
  @Test
  void list_tickets_by_department() {
    when(employeeRepository.findByEmail(Mockito.anyString())).thenReturn(employee);
    when(departmentRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(department));
    when(ticketRepository.findByDepartment(Mockito.eq(department), Mockito.any(PageRequest.class))).thenReturn(tickets);
    Optional<Page<TicketOutWOComment>> result = ticketService.listAllTickets("ayushi@nucleusteq.com",0,null, null, 100);

    assertThat(result).isNotNull();
    
    assertThat(result).hasValueSatisfying(ticketOutWOComments -> {
      assertThat(ticketOutWOComments).hasSize(tickets.getContent().size());
    });
  }
  
  @Test 
  void list_tickets_by_status_and_employee(){
    when(employeeRepository.findByEmail(Mockito.anyString())).thenReturn(employee);
    when(ticketRepository.findByStatusAndEmployee(Mockito.any(Status.class), Mockito.any(Employee.class), Mockito.any(PageRequest.class))).thenReturn(tickets);
    Optional<Page<TicketOutWOComment>> result = ticketService.listAllTickets("ayushi@nucleusteq.com",0,Status.BEING_ADDRESSED, true, null);

    assertThat(result).isNotNull();
    
    assertThat(result).hasValueSatisfying(ticketOutWOComments -> {
      assertThat(ticketOutWOComments).hasSize(tickets.getContent().size());
    });
  }
  

}
