package com.grievance.exceptionhandler;

import com.grievance.constants.ErrorConstants;
import com.grievance.exception.RecordAlreadyExistException;
import com.grievance.exception.ResourceNotFoundException;
import com.grievance.exception.CustomException;
import com.grievance.exception.UnauthorisedUserException;
import com.grievance.response.ErrorResponse;

import java.util.HashSet;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
  @ExceptionHandler(RecordAlreadyExistException.class)
  public ResponseEntity<ErrorResponse> handleEmployeeAlreadyExistsException(
      final RecordAlreadyExistException ex) {
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
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleResourceNotFoundException(
      final ResourceNotFoundException ex) {
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
 @ExceptionHandler(CustomException.class)
 public ResponseEntity<ErrorResponse> handleCustomException(
     final CustomException ex) {
   ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(),
       HttpStatus.BAD_REQUEST.value(), null);
   return new ResponseEntity<>(
       errorResponse,
       HttpStatus.BAD_REQUEST);
 }
  /**
   *
   * @param ex
   * @return responseentity.
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handlerMethodArgumentNotValid(
      final MethodArgumentNotValidException ex) {
    Set<String> validationErrors = new HashSet<String>();

    ex
        .getAllErrors()
        .forEach(
            error -> {
              String fieldName = ((FieldError) error).getField();
              String errorMessage = error.getDefaultMessage();
              validationErrors.add(fieldName + " : " + errorMessage);
            });

    ErrorResponse errorResponse = new ErrorResponse(
        ErrorConstants.METHOD_ARGUMENT_NOT_VALID,
        HttpStatus.BAD_REQUEST.value(), validationErrors);
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }
}
