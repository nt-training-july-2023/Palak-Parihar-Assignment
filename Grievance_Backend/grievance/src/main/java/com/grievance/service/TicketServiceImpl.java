/**
 *
 */
package com.grievance.service;

import com.grievance.dto.TicketInDto;
import com.grievance.dto.TicketOutDto;
import com.grievance.entity.Ticket;
import com.grievance.repository.TicketRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class TicketServiceImpl implements TicketService {
  /**
   * The employeeRepository instance provide data access method
   * for interacting with database.
   */
  @Autowired
  private ModelMapper modelMapper;
  /**
   * ticket repository instance provide access
   * method for interacting with database.
   */
  @Autowired
  private TicketRepository ticketRepository;

  /**
   * save a new ticket in ticket table in database.
   */
  @Override
  public Optional<TicketOutDto> saveTicket(final TicketInDto ticketInDto) {
	System.out.println(ticketInDto);
    Ticket ticket = convertToEntity(ticketInDto);
    ticketRepository.save(ticket);
    TicketOutDto ticketOutDto = convertToDto(ticket);
    return Optional.ofNullable(ticketOutDto);
  }

  /**
   * return list of All tickets present in database.
   * @return optional of list of ticketOut DTO.
   */
  @Override
  public Optional<List<TicketOutDto>> listOfAllTickets() {
    List<TicketOutDto> tickets = new ArrayList<TicketOutDto>();
    ticketRepository
      .findAll()
      .forEach(
        e -> {
          tickets.add(convertToDto(e));
        }
      );
    return Optional.ofNullable(tickets);
  }

  /**
   * Converts an Ticket entity object into an
   * TicketOutDto data transfer object (DTO).
   *
   * @param ticket The Ticket entity to be converted.
   * @return An TicketOutDto representing the employee's data.
   */
  public TicketOutDto convertToDto(final Ticket ticket) {
    TicketOutDto ticketOutDto = modelMapper.map(ticket, TicketOutDto.class);
    return ticketOutDto;
  }

  /**
   * Converts an TicketInDto dto object into an
   * Ticket entity.
   *
   * @param ticketInDto The Ticket entity to be converted.
   * @return An Employee representing the employee's data.
   */
  public Ticket convertToEntity(final TicketInDto ticketInDto) {
    Ticket ticket = modelMapper.map(ticketInDto, Ticket.class);
    return ticket;
  }
}
