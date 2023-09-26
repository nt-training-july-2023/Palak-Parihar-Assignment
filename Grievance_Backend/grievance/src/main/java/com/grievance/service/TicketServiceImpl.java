/**
 *
 */
package com.grievance.service;

import com.grievance.dto.TicketInDto;
import com.grievance.dto.TicketOutDto;
import com.grievance.dto.TicketOutWOComment;
import com.grievance.dto.TicketUpdateDto;
import com.grievance.entity.Comment;
import com.grievance.entity.Employee;
import com.grievance.entity.Status;
import com.grievance.entity.Ticket;
import com.grievance.entity.UserType;
import com.grievance.exception.CommentNotFoundException;
import com.grievance.exception.EmployeeNotFoundException;
import com.grievance.exception.TicketNotFoundException;
import com.grievance.exception.UnauthorisedUserException;
import com.grievance.repository.EmployeeRepository;
import com.grievance.repository.TicketRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class TicketServiceImpl implements TicketService {
  /**
   * The employeeRepository instance provide data access method for interacting
   * with database.
   */
  @Autowired
  private ModelMapper modelMapper;

  /**
   * ticket repository instance provide access method for interacting with
   * database.
   */
  @Autowired
  private TicketRepository ticketRepository;

  /**
   * employee repository instance provide access method for interacting with
   * database.
   */
  @Autowired
  private EmployeeRepository employeeRepository;

  /**
   * variable to store pageSize for pagination.
   */
  private final Integer pageSize = 10;

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
  public Optional<List<TicketOutWOComment>> listOfAllTickets(
      final Integer page) {
    List<TicketOutWOComment> tickets = new ArrayList<TicketOutWOComment>();
    ticketRepository
        .findAll(PageRequest.of(page, pageSize).withSort(Sort.by("status")))
        .forEach(
            e -> {
              tickets.add(convertToWOCommentDto(e));
            });
    return Optional.ofNullable(tickets);
  }

  /**
   * method to access rickets by their department Name.
   * @param email
   * @return list of ticket out DTO
   */
  @Override
  public Optional<List<TicketOutWOComment>>
  listOfAllTicketsByEmployeeDepartment(
      final String email,
      final Integer page) {
    Employee employee = employeeRepository.findByEmail(email);
    if (Objects.isNull(employee)) {
      throw new EmployeeNotFoundException(email);
    }
    List<TicketOutWOComment> ticketOutDtos =
        new ArrayList<TicketOutWOComment>();
    ticketRepository
        .findByDepartment(employee.getDepartment(),
            PageRequest.of(page, pageSize).withSort(Sort.by("status")))
        .forEach(
            e -> {
              ticketOutDtos.add(convertToWOCommentDto(e));
            });
    return Optional.ofNullable(ticketOutDtos);
  }

  /**
   * method to update ticket.
   * @param ticketUpdateDto
   * @return updated ticket.
   */
  @Override
  public Optional<TicketOutDto> updateTicket(
      final TicketUpdateDto ticketUpdateDto, final Integer ticketId,
      final String email) {
    Employee employee = employeeRepository.findByEmail(email);
    if (Objects.isNull(employee)) {
      throw new EmployeeNotFoundException(email);
    } else {
      Optional<Ticket> ticket = ticketRepository.findById(ticketId);
      if (ticket.isPresent()) {
        if (!employee.getDepartment().equals(ticket.get().getDepartment())) {
          throw new UnauthorisedUserException(email);
        }

      if (!Objects.isNull(ticketUpdateDto.getStatus())) {
        if (ticketUpdateDto.getStatus() == Status.RESOLVED) {
          if (ticket.get().getComments().isEmpty()) {
            throw new CommentNotFoundException();
          }
        }
        ticket.get().setStatus(ticketUpdateDto.getStatus());
      }
      Comment comment = new Comment(ticketUpdateDto.getDescription());
      comment.setTicket(ticket.get());
      comment.setUserName(email);
      ticket.get().getComments().add(comment);
      Ticket updatedTicket = ticketRepository.save(ticket.get());
      return Optional.ofNullable(convertToDto(updatedTicket));
    }
    throw new TicketNotFoundException(ticketId);
    }
  }

  /**
   * @param page
   * @param email
   * @return list of tickets raised by user.
   */
  @Override
  public Optional<List<TicketOutWOComment>> listTicketsRaisedByUser(
      final Integer page,
      final String email) {
    Employee employee = employeeRepository.findByEmail(email);
    if (Objects.isNull(employee)) {
      throw new EmployeeNotFoundException(email);
    } else {
      List<TicketOutWOComment> ticketOutDtos =
          new ArrayList<TicketOutWOComment>();
      ticketRepository.findByEmployee(employee,
          PageRequest.of(page, pageSize).withSort(Sort.by("status")))
          .forEach(e -> {
            ticketOutDtos.add(convertToWOCommentDto(e));
          });
      return Optional.ofNullable(ticketOutDtos);
    }
  }

  /**
   *
   * @param ticketId
   * @return ticket
   */
  @Override
  public Optional<TicketOutDto> findTicketByTicketId(final Integer ticketId) {
    Optional<Ticket> ticket = ticketRepository.findById(ticketId);
    if (ticket.isPresent()) {
      Optional<TicketOutDto> optionalTicketOut =
          Optional.ofNullable(convertToDto(ticket.get()));
      return optionalTicketOut;
    }
    throw new TicketNotFoundException(ticketId);
  }

  /**
   * @param status
   * @param page
   * @param email
   * @return list of tickets.
   */
  @Override
  public Optional<List<TicketOutWOComment>> listTicketByStatusAndEmployee(
      final Integer page,
      final Status status,
      final String email) {
    Employee employee = employeeRepository.findByEmail(email);
    if (Objects.isNull(employee)) {
      throw new EmployeeNotFoundException(email);
    }
    List<TicketOutWOComment> list = new ArrayList<TicketOutWOComment>();
    if (!Objects.isNull(employee)) {
      ticketRepository
          .findByStatusAndEmployee(status, employee,
              PageRequest.of(page, pageSize))
          .forEach(e -> {
            list.add(convertToWOCommentDto(e));
          });
    }
    return Optional.of(list);
  }

  /**
   * @param status
   * @return list of tickets.
   */
  @Override
  public Optional<List<TicketOutWOComment>> listTicketsByStatus(
      final Status status,
      final Integer page) {
    List<TicketOutWOComment> list = new ArrayList<TicketOutWOComment>();
    ticketRepository.findByStatus(status,
        PageRequest.of(page, pageSize).withSort(Sort.by("status")))
        .forEach(e -> {
          list.add(convertToWOCommentDto(e));
        });
    return Optional.ofNullable(list);
  }

  /**
   *
   * @param email
   * @param status
   * @param page
   * @return list of tickets by department and status.
   */
  @Override
  public Optional<List<TicketOutWOComment>>
  listTicketsRaisedByDepartmentAndStatus(
      final String email,
      final Status status,
      final Integer page) {
    Employee employee = employeeRepository.findByEmail(email);
    if (Objects.isNull(employee)) {
      throw new EmployeeNotFoundException(email);
      }
    List<TicketOutWOComment> list = new ArrayList<TicketOutWOComment>();
    ticketRepository.findByDepartmentAndStatusAndEmployee(
        employee.getDepartment(),
        status, employee, PageRequest.of(page, pageSize))
        .forEach(e -> {
          list.add(convertToWOCommentDto(e));
        });
    return Optional.ofNullable(list);
  }

  /**
   * @param email
   * @param page
   * @param status
   * @param myTickets
   * @return list of tickets.
   */
  @Override
  public Optional<List<TicketOutWOComment>> listAllTickets(
      final String email,
      final Integer page,
      final Status status,
      final Boolean myTickets) {
    Boolean isAdmin = employeeRepository.existsByEmailAndUserType(
        email, UserType.ADMIN);
    if (Objects.isNull(myTickets)) {
      if (isAdmin) {
        if (Objects.isNull(status)) {
          return listOfAllTickets(page);
        } else {
          return listTicketsByStatus(status, page);
        }
      } else {
        if (Objects.isNull(status)) {
          return listOfAllTicketsByEmployeeDepartment(email, page);
        } else {
          return listTicketsRaisedByDepartmentAndStatus(email, status, page);
        }
      }
    } else {
      if (Objects.isNull(status)) {
        return listTicketsRaisedByUser(page, email);
      } else {
        return listTicketByStatusAndEmployee(page, status, email);
      }
    }
  }

  /**
   * Converts an Ticket entity object into an TicketOutDto data transfer object
   * (DTO).
   *
   * @param ticket The Ticket entity to be converted.
   * @return An TicketOutDto representing the employee's data.
   */
  public TicketOutDto convertToDto(final Ticket ticket) {
    TicketOutDto ticketOutDto = modelMapper.map(ticket, TicketOutDto.class);
    return ticketOutDto;
  }

  /**
   * Converts an TicketInDto dto object into an Ticket entity.
   *
   * @param ticketInDto The Ticket entity to be converted.
   * @return An Employee representing the employee's data.
   */
  public Ticket convertToEntity(final TicketInDto ticketInDto) {
    Ticket ticket = modelMapper.map(ticketInDto, Ticket.class);
    return ticket;
  }

  /**
   * Converts an Ticket entity object into an TicketOutDto data transfer object
   * (DTO).
   *
   * @param ticket The Ticket entity to be converted.
   * @return An TicketOutWOComment representing the employee's data.
   */
  public TicketOutWOComment convertToWOCommentDto(final Ticket ticket) {
    TicketOutWOComment ticketOutWOComment =
        modelMapper.map(ticket, TicketOutWOComment.class);
    return ticketOutWOComment;
  }
}
