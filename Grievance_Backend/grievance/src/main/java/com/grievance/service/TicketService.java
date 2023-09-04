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
   *
   * @return optional of list of all tickets.
   */
  Optional<List<TicketOutDto>> listOfAllTickets();
}
