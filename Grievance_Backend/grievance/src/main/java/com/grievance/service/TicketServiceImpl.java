/**
 *
 */
package com.grievance.service;

import com.grievance.constants.ErrorConstants;
import com.grievance.dto.TicketInDto;
import com.grievance.dto.TicketOutDto;
import com.grievance.dto.TicketOutWOComment;
import com.grievance.dto.TicketUpdateDto;
import com.grievance.entity.Comment;
import com.grievance.entity.Department;
import com.grievance.entity.Employee;
import com.grievance.entity.Status;
import com.grievance.entity.Ticket;
import com.grievance.entity.UserType;
import com.grievance.exception.CustomException;
import com.grievance.exception.ResourceNotFoundException;
import com.grievance.exception.UnauthorisedUserException;
import com.grievance.repository.DepartmentRepository;
import com.grievance.repository.EmployeeRepository;
import com.grievance.repository.TicketRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
   * Intance to create loggers.
   */
  private static final Logger LOGGER = LoggerFactory
      .getLogger(DepartmentServiceImpl.class);
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
   * department repository instance provide access method for interacting with
   * database.
   */
  @Autowired
  private DepartmentRepository departmentRepository;

  /**
   * variable to store pageSize for pagination.
   */
  private final Integer pageSize = 10;

  /**
   * save a new ticket in ticket table in database.
   */
  @Override
  public Optional<TicketOutDto> saveTicket(final TicketInDto ticketInDto) {
    LOGGER.info("Saving ticket : {}", ticketInDto.getTitle());
    Ticket ticket = convertToEntity(ticketInDto);
    ticket = ticketRepository.save(ticket);
    TicketOutDto ticketOutDto = convertToDto(ticket);
    LOGGER.info("Ticket saved successfully");
    return Optional.ofNullable(ticketOutDto);
  }

  /**
   * return list of All tickets present in database.
   *
   * @return optional of list of ticketOut DTO.
   */
  @Override
  public Optional<List<TicketOutWOComment>> listOfAllTickets(
      final Integer page) {
    LOGGER.info("Listing all tickets");
    List<TicketOutWOComment> tickets = new ArrayList<TicketOutWOComment>();
    ticketRepository
        .findAll(
            PageRequest.of(page, pageSize).withSort(Sort.by("status")))
        .forEach(
            e -> {
              tickets.add(convertToWOCommentDto(e));
            });
    return Optional.ofNullable(tickets);
  }

  /**
   * method to access rickets by their department Name.
   *
   * @param email
   * @return list of ticket out DTO
   */
  @Override
  public Optional<List<TicketOutWOComment>>
  listOfAllTicketsByEmployeeDepartment(
      final String email,
      final Integer page) {
    LOGGER.info("Listing all tickets by user {}'s department", email);
    Employee employee = employeeRepository.findByEmail(email);
    if (Objects.isNull(employee)) {
      throw new ResourceNotFoundException(email);
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
   *
   * @param ticketUpdateDto
   * @return updated ticket.
   */
  @Override
  public Optional<TicketOutDto> updateTicket(
      final TicketUpdateDto ticketUpdateDto, final Integer ticketId,
      final String email) {
    LOGGER.info("Updating ticket with Ticket Id {} : ", ticketId);
    Employee employee = employeeRepository.findByEmail(email);
    if (Objects.isNull(employee)) {
      LOGGER.info("Employee with email {} not found for ticket update");
      throw new ResourceNotFoundException(email);
    } else {
      Optional<Ticket> ticket = ticketRepository.findById(ticketId);
      if (ticket.isPresent()) {
        Boolean belongsToUser = ticket.get().getEmployee()
            .equals(employee);
        Boolean belongstoUserDepartment = employee.getDepartment()
            .equals(ticket.get().getDepartment());
        if (!belongsToUser && !belongstoUserDepartment) {
          LOGGER.info(
              "Ticket with ticketId {},"
              + "doesn't belong to Employee with email {} ",
              ticketId, email);
          throw new UnauthorisedUserException(email);
        }

        if (!Objects.isNull(ticketUpdateDto.getStatus())) {
          LOGGER.info("Updating status {}, of Ticket with Ticket Id {} ",
              ticketUpdateDto.getStatus(),
              ticketId);
          ticket.get().setStatus(ticketUpdateDto.getStatus());
        }
        Date date = new Date();
        ticket.get().setLastUpdated(date);
        Comment comment = new Comment(ticketUpdateDto.getDescription(),
            email, ticket.get());
        ticket.get().getComments().add(comment);
        Ticket updatedTicket = ticketRepository.save(ticket.get());
        LOGGER.info("Ticket with Ticket Id {},"
            + "updated successfully with comment.", ticketId);
        return Optional.ofNullable(convertToDto(updatedTicket));
      }
      throw new ResourceNotFoundException(ErrorConstants.TICKET_NOT_FOUND);
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
    LOGGER.info("Listing all tickets raised by user {}", email);
    Employee employee = employeeRepository.findByEmail(email);
    if (Objects.isNull(employee)) {
      LOGGER.info("Employee with email {} not found", email);
      throw new ResourceNotFoundException(email);
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
   * @param ticketId
   * @return ticket
   */
  @Override
  public Optional<TicketOutDto> findTicketByTicketId(
      final Integer ticketId) {
    LOGGER.info("Finding ticket by Id {} ", ticketId);
    Optional<Ticket> ticket = ticketRepository.findById(ticketId);
    if (ticket.isPresent()) {
      Optional<TicketOutDto> optionalTicketOut = Optional
          .ofNullable(convertToDto(ticket.get()));
      LOGGER.info("Found ticket by Id {} ", ticketId);
      return optionalTicketOut;
    }
    LOGGER.info("Ticket not found by Id {} ", ticketId);
    throw new ResourceNotFoundException(ErrorConstants.TICKET_NOT_FOUND);
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
    LOGGER.info("Listing tickets raised by user "
        + "{} filtering by status {}", email, status);
    Employee employee = employeeRepository.findByEmail(email);
    if (Objects.isNull(employee)) {
      throw new ResourceNotFoundException(email);
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
    LOGGER.info("Liting tickets filtered by status {} ", status);
    List<TicketOutWOComment> list = new ArrayList<TicketOutWOComment>();
    ticketRepository.findByStatus(status,
        PageRequest.of(page, pageSize).withSort(Sort.by("status")))
        .forEach(e -> {
          list.add(convertToWOCommentDto(e));
        });
    return Optional.ofNullable(list);
  }

  /**
   * @param email
   * @param status
   * @param page
   * @return list of tickets by department and status.
   */
  @Override
  public Optional<List<TicketOutWOComment>>
  listTicketsByUserDepartmentAndStatus(
      final String email,
      final Status status,
      final Integer page) {
    LOGGER.info("Listing tickets by "
        + "user {} department and status {} ", email, status);
    Employee employee = employeeRepository.findByEmail(email);
    if (Objects.isNull(employee)) {
      throw new ResourceNotFoundException(email);
    }
    List<TicketOutWOComment> list = new ArrayList<TicketOutWOComment>();
    ticketRepository.findByDepartmentAndStatus(
        employee.getDepartment(),
        status, PageRequest.of(page, pageSize))
        .forEach(e -> {
          list.add(convertToWOCommentDto(e));
        });
    return Optional.ofNullable(list);
  }

  /**
  *
  * @param departmentId
  * @param status
  * @param page
  * @return list of tickets by department and status
  */
  @Override
  public Optional<List<TicketOutWOComment>> listTicketsByDepartmentAndStatus(
      final Integer departmentId,
      final Status status,
      final Integer page) {
    Department department =
        departmentRepository.findById(departmentId).orElseThrow(() -> {
      throw new CustomException(ErrorConstants.DEPARTMENT_NOT_FOUND);
    });
    List<TicketOutWOComment> list = new ArrayList<TicketOutWOComment>();
    ticketRepository.findByDepartmentAndStatus(
        department,
        status,
        PageRequest.of(page, pageSize))
        .forEach(e -> {
          list.add(convertToWOCommentDto(e));
        });
    return Optional.ofNullable(list);
  }

  /**
  *
  * @param departmentId
  * @param page
  * @return list of tickets by department
  */
  @Override
  public Optional<List<TicketOutWOComment>> listTicketsByDepartment(
      final Integer departmentId,
      final Integer page) {
    Department department =
        departmentRepository.findById(departmentId).orElseThrow(() -> {
      throw new CustomException(ErrorConstants.DEPARTMENT_NOT_FOUND);
    });
    List<TicketOutWOComment> list = new ArrayList<TicketOutWOComment>();
    ticketRepository.findByDepartment(
        department,
        PageRequest.of(page, pageSize))
        .forEach(e -> {
          list.add(convertToWOCommentDto(e));
        });
    return Optional.ofNullable(list);
  }

  /**
  *
  * @param departmentId
  * @param status
  * @param page
  * @return list of tickets by department
  */
  @Override
  public Optional<List<TicketOutWOComment>>
  listTicketsRaisedByUserByDepartmentAndStatus(
      final String email,
      final Integer departmentId,
      final Status status,
      final Integer page) {
    Employee employee = employeeRepository.findByEmail(email);
    if (Objects.isNull(employee)) {
      throw new CustomException(ErrorConstants.EMPLOYEE_NOT_FOUND);
    }
    Department department = departmentRepository
        .findById(departmentId).orElseThrow(() -> {
      throw new CustomException(ErrorConstants.DEPARTMENT_NOT_FOUND);
    });
    List<TicketOutWOComment> list = new ArrayList<TicketOutWOComment>();
    ticketRepository.findByDepartmentAndStatusAndEmployee(
        department,
        status,
        employee,
        PageRequest.of(page, pageSize))
    .forEach((e) -> {
      list.add(convertToWOCommentDto(e));
    });
    return Optional.ofNullable(list);
  }

  /**
   * @param email
   * @param departmentId
   * @param page
   * @return list of tickets by department
   */
  @Override
  public Optional<List<TicketOutWOComment>> listTicketsRaisedByUserByDepartment(
      final String email,
      final Integer departmentId,
      final Integer page) {
    Employee employee = employeeRepository.findByEmail(email);
    if (Objects.isNull(employee)) {
      throw new CustomException(ErrorConstants.EMPLOYEE_NOT_FOUND);
    }
    Department department = departmentRepository
        .findById(departmentId).orElseThrow(() -> {
      throw new CustomException(ErrorConstants.DEPARTMENT_NOT_FOUND);
    });
    List<TicketOutWOComment> list = new ArrayList<TicketOutWOComment>();
    ticketRepository.findByDepartmentAndEmployee(
        department,
        employee,
        PageRequest.of(page, pageSize))
    .forEach((e) -> {
      list.add(convertToWOCommentDto(e));
    });
    return Optional.ofNullable(list);
  }

  /**
   * @param email
   * @param page
   * @param status
   * @param myTickets
   * @param department
   * @return list of tickets.
   */
  @Override
  public Optional<List<TicketOutWOComment>> listAllTickets(
      final String email,
      final Integer page,
      final Status status,
      final Boolean myTickets,
      final Integer department) {
    LOGGER.info("Listing Tickets");
    Boolean isAdmin = employeeRepository.existsByEmailAndUserType(
        email, UserType.ADMIN);
    if (Objects.isNull(myTickets)) {
      if (isAdmin) {
        if (!Objects.isNull(status) && !Objects.isNull(department)) {
          return listTicketsByDepartmentAndStatus(department, status, page);
        } else {
          if (!Objects.isNull(status)) {
            return listTicketsByStatus(status, page);
          }
          if (!Objects.isNull(department)) {
            return listTicketsByDepartment(department, page);
          }
        }
        return listOfAllTickets(page);
      } else {
        if (Objects.isNull(status)) {
          return listOfAllTicketsByEmployeeDepartment(email, page);
        } else {
          return listTicketsByUserDepartmentAndStatus(email, status,
              page);
        }
      }
    } else {
      if (!Objects.isNull(department) && !Objects.isNull(status)) {
        return listTicketsRaisedByUserByDepartmentAndStatus(
            email, department, status, page);
      } else {
        if (!Objects.isNull(status)) {
          return listTicketByStatusAndEmployee(page, status, email);
        }
        if (!Objects.isNull(department)) {
          return listTicketsRaisedByUserByDepartment(email, department, page);
        }
      }
      return listTicketsRaisedByUser(page, email);
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
    TicketOutDto ticketOutDto = modelMapper.map(ticket,
        TicketOutDto.class);
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
    TicketOutWOComment ticketOutWOComment = modelMapper.map(ticket,
        TicketOutWOComment.class);
    return ticketOutWOComment;
  }
}

