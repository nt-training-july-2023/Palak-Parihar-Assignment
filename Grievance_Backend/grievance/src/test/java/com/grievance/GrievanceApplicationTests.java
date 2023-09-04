package com.grievance;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.grievance.entity.Status;
import com.grievance.entity.Ticket;
import com.grievance.entity.TicketType;
import com.grievance.repository.TicketRepository;



@SpringBootTest
class GrievanceApplicationTests {

	@Autowired
	TicketRepository ticketRepository;
	
  @Test
  public void context() {
	  Ticket ticket = new Ticket();
	  ticket.setTitle("Reimbursement issue");
	  ticket.setTicketType(TicketType.FEEDBACK);
	  ticket.setStatus(Status.OPEN);
	  ticket.setEmployee(null);
	  ticket.setDescription("The amount of reimbursement is not according to the request");
	  ticket.setDepartment(null);
	  ticket.setComments(null);
	  
	  ticketRepository.save(ticket);
  }
}






