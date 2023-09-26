package com.grievance.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception class for handling employee already exists
 * This exception is thrown when an attempt
 * to save an employee by already existed email.
 */
@ResponseStatus(
  value = HttpStatus.CONFLICT,
  reason = "Department with this Title already exists"
)
public class DepartmentAlreadyExistsException extends RuntimeException {
   private static final long serialVersionUID = -3332292346834265691L;
  /**
   * Constructs a new ResourceNotFoundException with a specific email address.
   *
   *
   */
  public DepartmentAlreadyExistsException() {
    super("Department Already Exist with this title");
  }
}
