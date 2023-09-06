package com.grievance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grievance.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

}

