package com.grievance.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception class for handling if user is
 * trying to delete itself.
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class SelfDeletionException extends RuntimeException {

  private static final long serialVersionUID = -3332292346834265674L;

  /**
   * Constructs a new ResourceNotFoundException with a specific email address.
   * @param message
   *
   */
  public SelfDeletionException(final String message) {
    super(message);
  }

}
