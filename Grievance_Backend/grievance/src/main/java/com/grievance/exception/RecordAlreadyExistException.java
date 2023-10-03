package com.grievance.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception class for handling record already exists.
 */
@ResponseStatus(
  value = HttpStatus.CONFLICT,
  reason = "Employee with this email already exist"
)
public class RecordAlreadyExistException extends RuntimeException {
  private static final long serialVersionUID = -3332292346834265671L;

  /**
   * Constructs a new RecordAlreadyExistException.
   * @param message
   */
  public RecordAlreadyExistException(final String message) {
    super(message);
  }
}
