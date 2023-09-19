package com.grievance.service;

import com.grievance.dto.TicketInDto;
import com.grievance.dto.TicketOutDto;

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
  Optional<List<TicketOutDto>> listOfAllTickets(Integer page);

  /**
   * method to access rickets by their department Name.
   * @param departmentName
   * @return list of ticket out DTO
   */
  Optional<List<TicketOutDto>> listOfAllTicketsByDepartmentName(
    String departmentName
  );

  /**
   * method to update ticket.
   * @param ticketInDto
   * @param ticketId
   * @param email
   * @return updated ticket.
   */
  Optional<TicketOutDto> updateTicket(
         TicketInDto ticketInDto, Integer ticketId,
         String email
      );
}
