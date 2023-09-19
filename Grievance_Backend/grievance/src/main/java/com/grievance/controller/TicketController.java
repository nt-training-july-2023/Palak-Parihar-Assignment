package com.grievance.controller;

import com.grievance.authentication.AuthenticatingUser;
import com.grievance.dto.TicketInDto;
import com.grievance.dto.TicketOutDto;
import com.grievance.exception.TicketNotFoundException;
import com.grievance.exception.UnauthorisedUserException;
import com.grievance.service.TicketService;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/ticket")
public class TicketController {
  /**
   *
   */
  @Autowired
  private TicketService ticketService;

  /**
   *
   */
  @Autowired
  private AuthenticatingUser authenticatingUser;

  /**
   *
   * Controller method to create a new ticket.
   * @param ticketInDto
   * @return Responseentity with optional of TicketOut DTO.
   */
  @PostMapping("/addTicket")
  public ResponseEntity<?> saveTicket(
            @RequestBody final TicketInDto ticketInDto) {
    Optional<TicketOutDto> optionalTicketOutDto =
            ticketService.saveTicket(ticketInDto);
    return new ResponseEntity<>(optionalTicketOutDto, HttpStatus.CREATED);
  }

  /**
   * controller method to return list of all tickets.
   *
   * @param departmentName
   * @param email
   * @param password
   * @param page
   * @return ResponseEntity with optional of list of all tickets.
   */
  @GetMapping("/listAllTickets")
  public ResponseEntity<?> listAllTickets(
    @RequestParam(required = false) final String departmentName,
    @RequestHeader final String email,
    @RequestHeader final String password,
    @RequestParam final Integer page
  ) {
    try {
      if (Objects.isNull(departmentName)) {
        if (!authenticatingUser.checkIfUserIsAdmin(email, password)) {
           throw new UnauthorisedUserException(email);
        }
        Optional<List<TicketOutDto>> optionalListOfTickets =
              ticketService.listOfAllTickets(page);
        return new ResponseEntity<>(optionalListOfTickets, HttpStatus.ACCEPTED);
      } else {
        Optional<List<TicketOutDto>> optionalListOfTickets =
              ticketService.listOfAllTicketsByDepartmentName(
          departmentName
        );
        return new ResponseEntity<>(optionalListOfTickets, HttpStatus.ACCEPTED);
      }
    } catch (UnauthorisedUserException e) {
      return new ResponseEntity<>("Unauthorised User", HttpStatus.UNAUTHORIZED);
    }
  }

  /**
   * controller method to return list of all tickets.
   * @param ticketId
   * @param ticketInDto
   * @param email
   * @param password
   * @return Responseentity with optional of updated TicketOut DTO.
   */
  @PutMapping("/update")
  public ResponseEntity<?> updateTickets(
          @RequestHeader final String email,
          @RequestHeader final String password,
          @RequestParam final Integer ticketId,
          @RequestBody final TicketInDto ticketInDto) {
      try {
           try {
              authenticatingUser.checkIfUserExists(email, password);
           } catch (UnauthorisedUserException e) {
              return new ResponseEntity<>("Unauthorised User",
                    HttpStatus.UNAUTHORIZED);
          }
      Optional<TicketOutDto>  optionalTicketOutDto = ticketService.updateTicket(
            ticketInDto, ticketId, email);
      return new ResponseEntity<>(optionalTicketOutDto, HttpStatus.OK);
       } catch (TicketNotFoundException e) {
            return new ResponseEntity<>("Ticket with id not found",
                                    HttpStatus.NOT_FOUND);
       }
  }

}
