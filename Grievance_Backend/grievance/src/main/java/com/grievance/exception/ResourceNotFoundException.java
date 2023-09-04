package com.grievance.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception class for handling resource not found errors.
 * This exception is thrown when an attempt
 * to retrieve an employee by email fails.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND,
    reason = "Employee with given credentials doesn't exist") // HTTP 404
public class ResourceNotFoundException extends RuntimeException {
  private static final long serialVersionUID = -3332292346834265371L;

  /**
   * Constructs a new ResourceNotFoundException with a specific email address.
   *
   * @param email The email address for which the employee was not found.
   */
  public ResourceNotFoundException(final String email) {
    super("EmployeeNotFoundException with email=" + email);
  }
}
