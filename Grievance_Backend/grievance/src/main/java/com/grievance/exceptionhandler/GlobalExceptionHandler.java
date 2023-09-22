package com.grievance.exceptionhandler;

import com.grievance.exception.TicketNotFoundException;
import com.grievance.exception.UnauthorisedUserException;
import com.grievance.response.ErrorResponse;
import java.util.HashSet;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  /**
   * default constructor.
   */
  public GlobalExceptionHandler() { }

  /**
   *
   * @param ex
   * @return responseentity with error messages.
   */
  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ErrorResponse> handleConstraintViolationException(
    final ConstraintViolationException ex
  ) {
    Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
    Set<String> validationMessages = new HashSet<>();

    for (ConstraintViolation<?> violation : violations) {
      validationMessages.add(violation.getMessage());
    }

    ErrorResponse errorResponse = new ErrorResponse(
      "Validation error",
      HttpStatus.BAD_REQUEST.value(),
      validationMessages
    );
    errorResponse.setValidationErrors(validationMessages);

    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  /**
   *
   * @param ex
   * @return respons
   */
  @ExceptionHandler(TicketNotFoundException.class)
  public ResponseEntity<?> handleGeneralException(
         final TicketNotFoundException ex) {
    ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(),
           HttpStatus.NOT_FOUND.value(), null);
    return new ResponseEntity<>(
            errorResponse,
            HttpStatus.NOT_FOUND);
  }

  /**
  *
  * @param ex
  * @return respons
  */
 @ExceptionHandler(UnauthorisedUserException.class)
 public ResponseEntity<?> handleGeneralException(
        final UnauthorisedUserException ex) {
   ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(),
          HttpStatus.UNAUTHORIZED.value(), null);
   return new ResponseEntity<>(
           errorResponse,
           HttpStatus.UNAUTHORIZED);
 }
}


