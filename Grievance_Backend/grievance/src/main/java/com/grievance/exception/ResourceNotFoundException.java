package com.grievance.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.grievance.constants.ErrorConstants;

/**
 * Custom exception class for handling resource not found errors.
 * This exception is thrown when an attempt
 * to retrieve a resouce fails.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND,
    reason = ErrorConstants.RESOURCE_NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
  private static final long serialVersionUID = -3332292346834265371L;

  /**
   * Constructs a new ResourceNotFoundException with a specific email address.
   *
   * @param data
   */
  public ResourceNotFoundException(final String data) {
    super(data);
  }
}
