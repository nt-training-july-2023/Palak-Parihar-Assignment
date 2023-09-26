package com.grievance.exceptionhandler;

import com.grievance.exception.*;
import com.grievance.response.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import javax.validation.ConstraintViolationException;

public class GlobalExceptionHandlerTest {

  private GlobalExceptionHandler globalExceptionHandler;

  @BeforeEach
  void setUp() {
    globalExceptionHandler = new GlobalExceptionHandler();
  }

  @Test
  void handleConstraintViolationException() {
    ConstraintViolationException ex = mock(ConstraintViolationException.class);

    ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleConstraintViolationException(ex);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    assertThat(response.getBody()).isNotNull();
    assertThat(response.getBody().getMessage()).isEqualTo("Validation error");
    assertThat(response.getBody().getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
  }

  @Test
  void handleTicketNotFoundException() {
    TicketNotFoundException ex = mock(TicketNotFoundException.class);

    ResponseEntity<?> response = globalExceptionHandler.handleTicketNotFoundException(ex);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    assertThat(response.getBody()).isNotNull();
  }

  @Test
  void handleUnauthorisedException() {
    UnauthorisedUserException ex = mock(UnauthorisedUserException.class);

    ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleUnauthorisedException(ex);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    assertThat(response.getBody()).isNotNull();
  }

  @Test
  void handleHttpMessageNotRedableException() {
    HttpMessageNotReadableException ex = mock(HttpMessageNotReadableException.class);

    ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleHttpMessageNotRedableException(ex);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    assertThat(response.getBody()).isNotNull();
  }

  @Test
  void handleEmployeeAlreadyExistsException() {
    EmployeeAlreadyExistException ex = mock(EmployeeAlreadyExistException.class);

    ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleEmployeeAlreadyExistsException(ex);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
    assertThat(response.getBody()).isNotNull();
  }

  @Test
  void handleDepartmentAlreadyExistsException() {
    DepartmentAlreadyExistsException ex = mock(DepartmentAlreadyExistsException.class);

    ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleDepartmentAlreadyExistsException(ex);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
    assertThat(response.getBody()).isNotNull();
  }

  @Test
  void handleEmployeeNotFoundException() {
    EmployeeNotFoundException ex = mock(EmployeeNotFoundException.class);

    ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleEmployeeNotFoundException(ex);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    assertThat(response.getBody()).isNotNull();
  }

  @Test
  void handlePasswordMatchException() {
    PasswordMatchException ex = mock(PasswordMatchException.class);

    ResponseEntity<ErrorResponse> response = globalExceptionHandler.handlePasswordMatchException(ex);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
    assertThat(response.getBody()).isNotNull();
  }
  
  @Test
  void handleCommentNotFoundException() {
    CommentNotFoundException ex = mock(CommentNotFoundException.class);
    
    ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleCommentNotFoundException(ex);
    
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    assertThat(response.getBody()).isNotNull();
  }

}
