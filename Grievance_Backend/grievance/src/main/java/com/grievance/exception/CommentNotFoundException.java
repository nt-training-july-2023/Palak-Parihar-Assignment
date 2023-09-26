package com.grievance.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception class for handling  not found errors.
 * This exception is thrown when an attempt
 * to retrieve an employee by email fails.
 */
@ResponseStatus(
  value = HttpStatus.NOT_FOUND,
  reason = "Ticket with this ticket Id doesn't exist"
)
public class CommentNotFoundException extends RuntimeException {
  private static final long serialVersionUID = -3332292346834265371L;

  /**
   * Constructs a new ResourceNotFoundException
   * with a specific email address.
   */
  public CommentNotFoundException() {
    super("Please leave a comment before closing the ticket");
  }
}
