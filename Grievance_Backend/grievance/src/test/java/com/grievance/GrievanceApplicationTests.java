package com.grievance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.grievance.repository.TicketRepository;
import com.grievance.service.TicketService;



@SpringBootTest
class GrievanceApplicationTests {

	@Autowired
	TicketRepository ticketRepository;
	
	@Autowired
	TicketService ticketService;
	

}






