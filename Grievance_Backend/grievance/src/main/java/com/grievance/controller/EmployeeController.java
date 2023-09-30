/**
 * User Controller.
 */

package com.grievance.controller;

import com.grievance.constants.ControllerURLS;
import com.grievance.dto.ChangePasswordInDto;
import com.grievance.dto.EmployeeInDto;
import com.grievance.dto.EmployeeLoginDto;
import com.grievance.dto.EmployeeOutDto;
import com.grievance.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Rest Controller.
 */
@RestController
@CrossOrigin("*")
@RequestMapping(path = ControllerURLS.EMPLOYEE_BASE_URL)
public class EmployeeController {
  /**
   * Instance for creating loggers.
   */
  private static final Logger LOGGER = LoggerFactory
      .getLogger(EmployeeController.class);

  /**
   * Autowiring Service.
   */
  @Autowired
  private EmployeeService employeeService;

  /**
   * Controller method for login in an Employee.
   *
   * @param employeeLoginDto for EmployeeLoginDto.
   * @return ResponseEntity.
   * @throws ResourceNotFoundException
   */
  @PostMapping(path = ControllerURLS.EMPLOYEE_LOGIN)
  public ResponseEntity<?> loginUser(
      @Valid @RequestBody final EmployeeLoginDto employeeLoginDto) {
    LOGGER.info("Login request received for email: {}",
        employeeLoginDto.getEmail());
    Optional<EmployeeOutDto> employeeDtoOptional = employeeService
        .loginEmployee(employeeLoginDto);
    LOGGER.info("Login successful for email: {}",
        employeeLoginDto.getEmail());
    return new ResponseEntity<>(employeeDtoOptional.get(),
        HttpStatus.ACCEPTED);
  }

  /**
   * Controller method to return list of All Employees.
   *
   * @param email
   * @param password
   * @param page
   * @return ResponseEntity with list of All Employees.
   */
  @GetMapping(path = ControllerURLS.GET_ALL_DATA)
  public ResponseEntity<?> listAllEmployees(
      @RequestHeader final String email,
      @RequestHeader final String password,
      @RequestParam final Integer page) {
    LOGGER.info("Listing all employees for page: {}", page);
    Optional<List<EmployeeOutDto>> listOfAllEmployees = employeeService
        .listAllEmployees(page);
    LOGGER.info("Listed all employees for page: {}", page);
    return new ResponseEntity<>(listOfAllEmployees.get(),
        HttpStatus.ACCEPTED);
  }

  /**
   * @param email
   * @param password
   * @param employeeInDto
   * @return Response
   */
  @PostMapping(path = ControllerURLS.SAVE_DATA)
  public ResponseEntity<?> saveEmployee(
      @RequestHeader final String email,
      @RequestHeader final String password,
      @RequestBody final EmployeeInDto employeeInDto) {
    LOGGER.info("Saving employee for email: {}", email);
    Optional<EmployeeOutDto> optional = employeeService
        .saveEmployee(employeeInDto);
    LOGGER.info("Saved employee for email: {}", email);
    return new ResponseEntity<>(optional.get(), HttpStatus.CREATED);
  }

  /**
   * @param email
   * @param changePasswordInDto
   * @return ResponseEntity
   */
  @PutMapping(path = ControllerURLS.EMPLOYEE_CHANGE_PASSWORD)
  public ResponseEntity<?> changePassword(
      @RequestHeader final String email,
      @RequestBody final ChangePasswordInDto changePasswordInDto) {
    LOGGER.info("Changing password for email: {}", email);
    employeeService.changePassword(changePasswordInDto, email);
    LOGGER.info("Password changed successfully for email: {}", email);
    return new ResponseEntity<>("Password changed successfully",
        HttpStatus.OK);
  }

  /**
   * @param email
   * @return ResponseEntity.
   */
  @DeleteMapping(path = ControllerURLS.DELETE_DATA_BY_ID)
  public ResponseEntity<?> deleteEmployee(
      @RequestParam final String email) {
    LOGGER.info("Deleting employee for email: {}", email);
    employeeService.deleteEmployeeById(email);
    LOGGER.info("Deleted employee successfully for email: {}", email);
    return new ResponseEntity<>("Employee deleted successfully",
        HttpStatus.NO_CONTENT);
  }
}
