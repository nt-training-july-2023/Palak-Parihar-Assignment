package com.grievance.repository;

import org.springframework.data.repository.CrudRepository;

import com.grievance.entity.Ticket;

public interface TicketRepository extends CrudRepository<Ticket, Integer> {

}

