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
 Optional<TicketOutDto> findTicketByTicketId(Integer ticketId);


  /**
   * @param page
   * @return optional of list of all tickets.
   */
  Optional<List<TicketOutWOComment>> listOfAllTickets(Integer page);

  /**
   * method to access rickets by their department Name.
   * @param email
   * @param page
   * @return list of ticket out DTO
   */
  Optional<List<TicketOutWOComment>> listOfAllTicketsByEmployeeDepartment(
      String email,
      Integer page);

  /**
   *
   * @param email
   * @param page
   * @return list of tickets raised by user.
   */
  Optional<List<TicketOutWOComment>> listTicketsRaisedByUser(
      Integer page, String email);

  /**
   * @param page
   * @param status
   * @param email
   * @return list of tickets by page, status, email.
   */
  Optional<List<TicketOutWOComment>> listTicketByStatusAndEmployee(
      Integer page, Status status, String email);

  /**
   * @param status
   * @param page
   * @return list of tickets by status.
   */
  Optional<List<TicketOutWOComment>> listTicketsByStatus(
      Status status, Integer page);

  /**
   * @param email
   * @param page
   * @param status
   * @param myTickets
   * @return list of tickets.
   */
  Optional<List<TicketOutWOComment>> listAllTickets(
      String email, Integer page, Status status,
      Boolean myTickets);

  /**
   *
   * @param email
   * @param status
   * @param page
   * @return list of tickets by department and status.
   */
  Optional<List<TicketOutWOComment>> listTicketsRaisedByDepartmentAndStatus(
      String email,
      Status status,
      Integer page);

}
