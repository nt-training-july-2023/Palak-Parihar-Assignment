package com.grievance.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception class for handling resource not found errors.
 * This exception is thrown when an attempt
 * to retrieve an employee by email fails.
 */
@ResponseStatus(
  value = HttpStatus.CONFLICT,
  reason = "New Password must not be equal to old Password"
)
public class PasswordMatchException extends RuntimeException {
  // HTTP 404
  private static final long serialVersionUID = -3332292346834265371L;

  /**
   * Constructs a new PasswordMismatchException .
   *
   */
  public PasswordMatchException() {
    super("New Password must not be equal to old Password");
  }
}
