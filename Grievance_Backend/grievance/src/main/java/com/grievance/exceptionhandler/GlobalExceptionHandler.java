package com.grievance.exceptionhandler;

import com.grievance.exception.CommentNotFoundException;
import com.grievance.exception.DepartmentAlreadyExistsException;
import com.grievance.exception.DepartmentNotFound;
import com.grievance.exception.EmployeeAlreadyExistException;
import com.grievance.exception.EmployeeNotFoundException;
import com.grievance.exception.PasswordMatchException;
import com.grievance.exception.TicketNotFoundException;
import com.grievance.exception.UnauthorisedUserException;
import com.grievance.response.ErrorResponse;
import java.util.HashSet;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  /**
   * default constructor.
   */
  public GlobalExceptionHandler() {
  }

  /**
   *
   * @param ex
   * @return responseentity with error messages.
   */
  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ErrorResponse> handleConstraintViolationException(
      final ConstraintViolationException ex) {
    Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
    Set<String> validationMessages = new HashSet<>();

    for (ConstraintViolation<?> violation : violations) {
      validationMessages.add(violation.getMessage());
    }

    ErrorResponse errorResponse = new ErrorResponse(
        "Validation error",
        HttpStatus.BAD_REQUEST.value(),
        validationMessages);
    errorResponse.setValidationErrors(validationMessages);

    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  /**
   *
   * @param ex
   * @return response
   */
  @ExceptionHandler(TicketNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleTicketNotFoundException(
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
  * @return response
  */
 @ExceptionHandler(CommentNotFoundException.class)
 public ResponseEntity<ErrorResponse> handleCommentNotFoundException(
     final CommentNotFoundException ex) {
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
  public ResponseEntity<ErrorResponse> handleUnauthorisedException(
      final UnauthorisedUserException ex) {
    ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(),
        HttpStatus.UNAUTHORIZED.value(), null);
    return new ResponseEntity<>(
        errorResponse,
        HttpStatus.UNAUTHORIZED);
  }

  /**
   *
   * @param ex
   * @return responseEntity
   */
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ErrorResponse> handleHttpMessageNotRedableException(
      final HttpMessageNotReadableException ex) {
    ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(),
        HttpStatus.BAD_REQUEST.value(), null);
    return new ResponseEntity<>(
        errorResponse,
        HttpStatus.BAD_REQUEST);
  }

  /**
   *
   * @param ex
   * @return responseEntity.
   */
  @ExceptionHandler(EmployeeAlreadyExistException.class)
  public ResponseEntity<ErrorResponse> handleEmployeeAlreadyExistsException(
      final EmployeeAlreadyExistException ex) {
    ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(),
        HttpStatus.CONFLICT.value(), null);
    return new ResponseEntity<>(
        errorResponse,
        HttpStatus.CONFLICT);
  }

  /**
   *
   * @param ex
   * @return responseEntity.
   */
  @ExceptionHandler(DepartmentAlreadyExistsException.class)
  public ResponseEntity<ErrorResponse> handleDepartmentAlreadyExistsException(
      final DepartmentAlreadyExistsException ex) {
    ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(),
        HttpStatus.CONFLICT.value(), null);
    return new ResponseEntity<>(
        errorResponse,
        HttpStatus.CONFLICT);
  }

  /**
   *
   * @param ex
   * @return responseEntity.
   */
  @ExceptionHandler(EmployeeNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleEmployeeNotFoundException(
      final EmployeeNotFoundException ex) {
    ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(),
        HttpStatus.NOT_FOUND.value(), null);
    return new ResponseEntity<>(
        errorResponse,
        HttpStatus.NOT_FOUND);
  }

  /**
   *
   * @param ex
   * @return responseEntity.
   */
  @ExceptionHandler(PasswordMatchException.class)
  public ResponseEntity<ErrorResponse> handlePasswordMatchException(
      final PasswordMatchException ex) {
    ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(),
        HttpStatus.CONFLICT.value(), null);
    return new ResponseEntity<>(
        errorResponse,
        HttpStatus.CONFLICT);
  }

  /**
   *
   * @param ex
   * @return responseEntity.
   */
  @ExceptionHandler(DepartmentNotFound.class)
  public ResponseEntity<ErrorResponse> handleEmptyResultDataAccessException(
      final DepartmentNotFound ex) {
    ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(),
        HttpStatus.NOT_FOUND.value(), null);
    return new ResponseEntity<>(
        errorResponse,
        HttpStatus.NOT_FOUND);
  }
}
