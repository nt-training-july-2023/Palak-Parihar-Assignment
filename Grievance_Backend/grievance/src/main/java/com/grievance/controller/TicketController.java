package com.grievance.controller;

import com.grievance.constants.ControllerURLS;
import com.grievance.dto.TicketInDto;
import com.grievance.dto.TicketOutDto;
import com.grievance.dto.TicketOutWOComment;
import com.grievance.dto.TicketUpdateDto;
import com.grievance.entity.Status;
import com.grievance.service.TicketService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Controller for managing tickets.
 */
@CrossOrigin("*")
@RestController
@RequestMapping(path = ControllerURLS.TICKET_BASE_URL)
public class TicketController {
  /**
   * Instance for creating loggers.
   */
  private static final Logger LOGGER = LoggerFactory
      .getLogger(TicketController.class);

  /**
   * Instance to access method os ticketService layers.
   */
  @Autowired
  private TicketService ticketService;

  /**
   * Controller method to create a new ticket.
   *
   * @param ticketInDto The TicketInDto object.
   * @return ResponseEntity with optional of TicketOut DTO.
   */
  @PostMapping(path = ControllerURLS.SAVE_DATA)
  public ResponseEntity<?> saveTicket(
      @Valid @RequestBody final TicketInDto ticketInDto) {
    LOGGER.info("Received request to create a new ticket.");
    ticketService.saveTicket(ticketInDto);
    LOGGER.info("Ticket created successfully.");
    return new ResponseEntity<>("Ticket created successfully",
        HttpStatus.CREATED);
  }

  /**
   * Controller method to return list of all tickets.
   *
   * @param email     The email of the user making the request.
   * @param page      The page number for pagination.
   * @param status    The status of the tickets (optional).
   * @param myTickets Flag indicating whether to return only the user's tickets
   *                  (optional).
   * @return ResponseEntity with optional of list of all tickets.
   */
  @GetMapping(path = ControllerURLS.GET_ALL_DATA)
  public ResponseEntity<List<TicketOutWOComment>> listAllTickets(
      @RequestHeader final String email,
      @RequestParam final Integer page,
      @RequestParam(required = false) final Status status,
      @RequestParam(required = false) final Boolean myTickets) {
    LOGGER.info("Received request to list all tickets.");
    Optional<List<TicketOutWOComment>> response = ticketService
        .listAllTickets(email, page, status, myTickets);
    LOGGER.info("Listed all tickets successfully.");
    return new ResponseEntity<>(response.get(), HttpStatus.ACCEPTED);
  }

  /**
   * Controller method to update a ticket.
   *
   * @param email           The email of the user making the request.
   * @param ticketId        The ID of the ticket to be updated.
   * @param ticketUpdateDto The TicketUpdateDto object.
   * @return ResponseEntity with optional of updated TicketOut DTO.
   */
  @PutMapping(path = ControllerURLS.UPDATE_DATA_BY_ID)
  public ResponseEntity<TicketOutDto> updateTickets(
      @RequestHeader final String email,
      @RequestParam final Integer ticketId,
      @Valid @RequestBody final TicketUpdateDto ticketUpdateDto) {
    LOGGER.info("Received request to update a ticket with ID: {}",
        ticketId);
    Optional<TicketOutDto> optionalTicketOutDto = ticketService
        .updateTicket(
            ticketUpdateDto, ticketId, email);
    LOGGER.info("Ticket with ID: {} updated successfully.", ticketId);
    return new ResponseEntity<>(optionalTicketOutDto.get(), HttpStatus.OK);
  }

  /**
   * Controller method to return a ticket by ID.
   *
   * @param ticketId The ID of the ticket.
   * @return ResponseEntity with optional of TicketOut DTO.
   */
  @GetMapping(path = ControllerURLS.GET_DATA_BY_ID)
  public ResponseEntity<TicketOutDto> getTicketById(
      @RequestParam final Integer ticketId) {
    LOGGER.info("Received request to fetch a ticket with ID: {}",
        ticketId);
    Optional<TicketOutDto> ticketOut = ticketService
        .findTicketByTicketId(ticketId);
    LOGGER.info("Fetched ticket with ID: {} successfully.", ticketId);
    return new ResponseEntity<>(ticketOut.get(), HttpStatus.OK);
  }
}
