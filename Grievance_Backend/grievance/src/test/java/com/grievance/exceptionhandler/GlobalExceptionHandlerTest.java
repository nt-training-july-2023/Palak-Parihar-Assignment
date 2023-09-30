package com.grievance.exceptionhandler;

import com.grievance.exception.*;
import com.grievance.response.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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
  void handleResourceNotFoundException() {
    ResourceNotFoundException ex = mock(ResourceNotFoundException.class);

    ResponseEntity<?> response = globalExceptionHandler.handleResourceNotFoundException(ex);

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
  void handleRecordAlreadyExistsException() {
    RecordAlreadyExistException ex = mock(RecordAlreadyExistException.class);

    ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleEmployeeAlreadyExistsException(ex);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
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
  void handleMethodArgumentNotValid() {
    MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);

    List<ObjectError> errors = new ArrayList<>();
    errors.add(new FieldError("objectName", "fieldName1", "Error message 1"));
    errors.add(new FieldError("objectName", "fieldName2", "Error message 2"));
    when(ex.getAllErrors()).thenReturn(errors);

    ResponseEntity<ErrorResponse> response = globalExceptionHandler.handlerMethodArgumentNotValid(ex);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    assertThat(response.getBody()).isNotNull();
  }

}
