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
   * @param page
   * @return optional of list of all tickets.
   */
  Optional<List<TicketOutWOComment>> listOfAllTickets(Integer page);

  /**
   * method to access rickets by their department Name.
   * @param departmentName
   * @return list of ticket out DTO
   */
  Optional<List<TicketOutWOComment>> listOfAllTicketsByDepartmentName(
    String departmentName
  );

  /**
   * method to update ticket.
   * @param ticketUpdateDto
   * @param ticketId
   * @param email
   * @return updated ticket.
   */
  Optional<TicketOutDto> updateTicket(
         TicketUpdateDto ticketUpdateDto, Integer ticketId,
         String email
      );

  /**
   *
   * @param email
   * @param status
   * @param page
   * @return list of tickets raised by user.
   */
  Optional<List<TicketOutWOComment>> listTicketsRaisedByUser(
          Integer page,
          Status status,
          String email
     );

  /**
   *
   * @param ticketId
   * @return ticket
   */
  Optional<TicketOutDto> findTicketByTicketId(Integer ticketId);

  /**
   * @param page
   * @param status
   * @param email
   * @return list of tickets.
   */
  Optional<List<TicketOutWOComment>> listTicketByStatusAndEmployee(
          Integer page,
          Status status,
          String email);

  /**
   * @param status
   * @return list of tickets.
   */
  Optional<List<TicketOutWOComment>> listTicketsByStatus(Status status);
}
