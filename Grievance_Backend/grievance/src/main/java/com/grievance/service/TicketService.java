package com.grievance.service;

import com.grievance.dto.TicketInDto;
import com.grievance.dto.TicketOutDto;
import com.grievance.dto.TicketOutWOComment;
import com.grievance.dto.TicketUpdateDto;
import com.grievance.entity.Status;

import java.util.List;
import java.util.Optional;

/**
 *
 */
public interface TicketService {
  /**
   * @param ticketInDto
   * @return optional of TicketOut DTO.
   */
  Optional<TicketOutDto> saveTicket(TicketInDto ticketInDto);

  /**
   * method to update ticket.
   * @param ticketUpdateDto
   * @param ticketId
   * @param email
   * @return updated ticket.
   */
  Optional<TicketOutDto> updateTicket(
      TicketUpdateDto ticketUpdateDto, Integer ticketId, String email);

  /**
  *
  * @param ticketId
  * @return ticket
  */
 Optional<TicketOutDto> findTicketById(Integer ticketId);

 /**
  * @param email
  * @param page
  * @param status
  * @param myTickets
  * @param department
  * @return list of tickets.
  */
 Optional<List<TicketOutWOComment>> listAllTickets(
     String email, Integer page, Status status,
     Boolean myTickets, Integer department);


}

