package com.grievance.entity;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TicketTest {

  @Test
  void testTicketEquals() {
    Ticket ticket1 = new Ticket("Title", TicketType.GRIEVANCE, new Department("FINANCE"), "description", Status.BEING_ADDRESSED, new Date(), new Employee());

    Ticket ticket2 = new Ticket();
    ticket2.setTitle("Title");
    ticket2.setTicketType(TicketType.GRIEVANCE);
    ticket2.setStatus(Status.BEING_ADDRESSED);
    ticket2.setDescription("description");
    ticket2.setDateOpened(new Date());
    ticket2.setEmployee(new Employee());
    
    assertThat(ticket1.equals(ticket2));
    
    assertThat(!ticket1.equals(null));
    
    assertThat(!ticket1.equals(Employee.class));
    
    assertThat(ticket1.equals(ticket1));
  }
  
  @Test
  void testTicketHashCode() {
    Ticket ticket1 = new Ticket("Title", TicketType.GRIEVANCE, new Department("FINANCE"), "description", Status.BEING_ADDRESSED, new Date(), new Employee());
    Ticket ticket2 = new Ticket("Title", TicketType.GRIEVANCE, new Department("FINANCE"), "description", Status.OPEN, new Date(), new Employee());
    Ticket ticket3 = new Ticket("Title", TicketType.GRIEVANCE, new Department("FINANCE"), "description", Status.BEING_ADDRESSED, new Date(), new Employee());
    
    Assertions.assertEquals(ticket1.hashCode(), ticket3.hashCode());
    
    Assertions.assertNotEquals(ticket1.hashCode(), ticket2.hashCode());
  }
  
}
