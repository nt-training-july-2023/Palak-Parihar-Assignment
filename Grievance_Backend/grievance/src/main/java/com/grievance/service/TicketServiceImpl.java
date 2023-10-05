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
   * ticket repository instance provide access method for interacting withs
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
   * @param page
   * @return optional of list of ticketOut DTO.
   */
  public Optional<List<TicketOutWOComment>> findAll(
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
   * @Id
   * @param page
   * @param departmentId
   * @return list of ticket out DTO
   */
  public Optional<List<TicketOutWOComment>>
  findByDepartment(
      final Integer departmentId,
      final Integer page) {
    LOGGER.info("Listing all tickets by user {}'s department",
        departmentId);
    List<TicketOutWOComment> ticketOutDtos =
        new ArrayList<TicketOutWOComment>();
    Optional<Department> department =
        departmentRepository.findById(departmentId);
    if (!department.isPresent()) {
      throw new ResourceNotFoundException(ErrorConstants.DEPARTMENT_NOT_FOUND);
    }
    ticketRepository
        .findByDepartment(department.get(),
            PageRequest.of(page, pageSize).withSort(Sort.by("status")))
        .forEach(
            e -> {
              ticketOutDtos.add(convertToWOCommentDto(e));
            });
    return Optional.ofNullable(ticketOutDtos);
  }

  /**
   * method to update ticket.
   * @param ticketId
   * @param email
   * @param ticketUpdateDto
   * @return updated ticket.
   */
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
   * list Tickets Raised By User.
   *
   * @param page
   * @param email
   * @return list of tickets raised by user.
   */
  public Optional<List<TicketOutWOComment>> findByEmployee(
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
   * find Ticket By Ticket Id.
   *
   * @param ticketId
   * @return ticket
   */
  public Optional<TicketOutDto> findTicketById(
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
   * list Ticket By Status And Employee.
   *
   * @param status
   * @param page
   * @param email
   * @return list of tickets.
   */
  public Optional<List<TicketOutWOComment>> findByStatusAndEmployee(
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
   * list Tickets By Status.
   *
   * @param status
   * @param page
   * @return list of tickets.
   */
  public Optional<List<TicketOutWOComment>> findByStatus(
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
  * list Tickets By Department And Status.
  *
  * @param departmentId
  * @param status
  * @param page
  * @return list of tickets by department and status
  */
  public Optional<List<TicketOutWOComment>> findByDepartmentAndStatus(
      final Integer departmentId,
      final Status status,
      final Integer page) {
    LOGGER.info("Listing tickets by department {}, "
        + "and status {}", departmentId, status);
    Optional<Department> department =
        departmentRepository.findById(departmentId);
    if (!department.isPresent()) {
      throw new CustomException(ErrorConstants.DEPARTMENT_NOT_FOUND);
    }
    List<TicketOutWOComment> list = new ArrayList<TicketOutWOComment>();
    ticketRepository.findByDepartmentAndStatus(
        department.get(),
        status,
        PageRequest.of(page, pageSize))
        .forEach(e -> {
          list.add(convertToWOCommentDto(e));
        });
    return Optional.ofNullable(list);
  }


  /**
  * list Tickets Raised By User By Department And Status.
  *
  * @param departmentId
  * @param status
  * @param page
  * @param email
  * @return list of tickets by department
  */
  public Optional<List<TicketOutWOComment>>
  findByDepartmentStatusAndEmployee(
      final String email,
      final Integer departmentId,
      final Status status,
      final Integer page) {
    LOGGER.info("Listing tickets by Raised by user {}, by department {}, "
        + "and status {}", email, departmentId, status);
    Employee employee = employeeRepository.findByEmail(email);
    if (Objects.isNull(employee)) {
      throw new CustomException(ErrorConstants.EMPLOYEE_NOT_FOUND);
    }
    Optional<Department> department =
        departmentRepository.findById(departmentId);
    if (!department.isPresent()) {
      throw new CustomException(ErrorConstants.DEPARTMENT_NOT_FOUND);
    }
    List<TicketOutWOComment> list = new ArrayList<TicketOutWOComment>();
    ticketRepository.findByDepartmentAndStatusAndEmployee(
        department.get(),
        status,
        employee,
        PageRequest.of(page, pageSize))
    .forEach((e) -> {
      list.add(convertToWOCommentDto(e));
    });
    return Optional.ofNullable(list);
  }

  /**
   * list Tickets Raised By User By Department.
   *
   * @param email
   * @param departmentId
   * @param page
   * @return list of tickets by department
   */
  public Optional<List<TicketOutWOComment>> findByDepartmentAndEmployee(
      final String email,
      final Integer departmentId,
      final Integer page) {
    LOGGER.info("Listing tickets Raised by user {}, "
        + "by department {}", email, departmentId);
    Employee employee = employeeRepository.findByEmail(email);
    if (Objects.isNull(employee)) {
      throw new CustomException(ErrorConstants.EMPLOYEE_NOT_FOUND);
    }
    Optional<Department> department =
        departmentRepository.findById(departmentId);
    if (!department.isPresent()) {
      throw new CustomException(ErrorConstants.DEPARTMENT_NOT_FOUND);
    }
    List<TicketOutWOComment> list = new ArrayList<TicketOutWOComment>();
    ticketRepository.findByDepartmentAndEmployee(
        department.get(),
        employee,
        PageRequest.of(page, pageSize))
    .forEach((e) -> {
      list.add(convertToWOCommentDto(e));
    });
    return Optional.ofNullable(list);
  }

  /**
   * list All Tickets.
   *
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
    Employee employee = employeeRepository.findByEmail(email);
    Boolean isAdmin = employee.getUserType().equals(UserType.ADMIN);
    if (Objects.isNull(myTickets)) {
      if (isAdmin) {
        if (!Objects.isNull(status) && !Objects.isNull(department)) {
          return findByDepartmentAndStatus(department, status, page);
        } else {
          if (!Objects.isNull(status)) {
            return findByStatus(status, page);
          }
          if (!Objects.isNull(department)) {
            return findByDepartment(department, page);
          }
          return findAll(page);
        }
      } else {
        if (Objects.isNull(status)) {
          return findByDepartment(
              employee.getDepartment().getDepartmentId(), page);
        } else {
          return findByDepartmentAndStatus(
              employee.getDepartment().getDepartmentId(), status, page);
        }
      }
    } else {
      if (!Objects.isNull(department) && !Objects.isNull(status)) {
        return findByDepartmentStatusAndEmployee(
            email, department, status, page);
      } else {
        if (!Objects.isNull(status)) {
          return findByStatusAndEmployee(page, status, email);
        }
        if (!Objects.isNull(department)) {
          return findByDepartmentAndEmployee(email, department, page);
        }
      }
      return findByEmployee(page, email);
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

