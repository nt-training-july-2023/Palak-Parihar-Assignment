/**
 *
 */
package com.grievance.service;

import com.grievance.dto.TicketInDto;
import com.grievance.dto.TicketOutDto;
import com.grievance.entity.Department;
import com.grievance.entity.Employee;
import com.grievance.entity.Ticket;
import com.grievance.exception.EmployeeNotFoundException;
import com.grievance.exception.TicketNotFoundException;
import com.grievance.exception.UnauthorisedUserException;
import com.grievance.repository.DepartmentRepository;
import com.grievance.repository.EmployeeRepository;
import com.grievance.repository.TicketRepository;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
   * department repository instance provide access
   * method for interacting with database.
   */
  @Autowired
  private DepartmentRepository departmentRepository;

  /**
   * employee repository instance provide access
   * method for interacting with database.
   */
  @Autowired
  private EmployeeRepository employeeRepository;

  /**
   * save a new ticket in ticket table in database.
   */
  @Override
  public Optional<TicketOutDto> saveTicket(final TicketInDto ticketInDto) {
    Ticket ticket = convertToEntity(ticketInDto);
    ticket = ticketRepository.save(ticket);
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
   * method to access rickets by their department Name.
   * @param departmentName
   * @return list of ticket out DTO
   */
  @Override
  public Optional<List<TicketOutDto>> listOfAllTicketsByDepartmentName(
    final String departmentName
  ) {
    Department department = departmentRepository.findByDepartmentName(
         departmentName);
    List<TicketOutDto> ticketOutDtos = new ArrayList<TicketOutDto>();
    ticketRepository
      .findByDepartment(department)
      .forEach(
        e -> {
          ticketOutDtos.add(convertToDto(e));
        }
      );
    return Optional.ofNullable(ticketOutDtos);
  }

  /**
   * method to update ticket.
   * @param ticketInDto
   * @return updated ticket.
   */
  @Override
  public Optional<TicketOutDto> updateTicket(
          final TicketInDto ticketInDto, final Integer ticketId,
          final String email
          ) {
       Employee employee = employeeRepository.findByEmail(email);
       if (Objects.isNull(employee)) {
          throw new EmployeeNotFoundException(email);
       } else {
             if (!employee.getDepartment().getDepartmentName().equals(
                    ticketInDto.getDepartment().getDepartmentName())) {
                throw new UnauthorisedUserException(email);
         }
      }
    Optional<Ticket> ticket = ticketRepository.findById(ticketId);
    if (ticket.isPresent()) {
        Date date = new Date(System.currentTimeMillis());
        ticket.get().setLastUpdated(date);
        ticket.get().setDescription(ticketInDto.getDescription());
        ticket.get().setStatus(ticketInDto.getStatus());
        ticket.get().setTicketType(ticketInDto.getTicketType());
        Department department = new Department();
        department.setDepartmentId(
                  ticketInDto.getDepartment().getDepartmentId());
        ticket.get().setDepartment(department);

        Ticket updatedTicket = ticketRepository.save(ticket.get());
        TicketOutDto ticketOutDto = convertToDto(updatedTicket);
        return Optional.ofNullable(ticketOutDto);
    } else {
        throw new TicketNotFoundException(ticketId);
    }
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
