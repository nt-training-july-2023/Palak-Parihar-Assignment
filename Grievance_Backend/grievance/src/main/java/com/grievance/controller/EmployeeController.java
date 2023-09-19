/**
 * User Controller.
 */

package com.grievance.controller;

import com.grievance.authentication.AuthenticatingUser;
import com.grievance.dto.ChangePasswordInDto;
import com.grievance.dto.EmployeeInDto;
import com.grievance.dto.EmployeeLoginDto;
import com.grievance.dto.EmployeeOutDto;
import com.grievance.exception.EmployeeAlreadyExistException;
import com.grievance.exception.EmployeeNotFoundException;
import com.grievance.exception.UnauthorisedUserException;
import com.grievance.service.EmployeeService;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest Controller.
 */
@RestController
@CrossOrigin("*")
public class EmployeeController {
  /**
   *Autowiring Service.
   */
  @Autowired
  private EmployeeService employeeService;


  /**
   * The authenicatingUser instance
   * provides information about user being authorised.
   */
  @Autowired
  private AuthenticatingUser authenticatingUser;

  /**
   * Controller method for login in a Employee.
   *
   * @param employeeLoginDto for EmployeeLoginDto.
   * @return ResponseEntity.
 * @throws EmployeeNotFoundException
   */
  @PostMapping("/login")
  public ResponseEntity<?> loginUser(
      @Valid @RequestBody final EmployeeLoginDto employeeLoginDto
  ) throws EmployeeNotFoundException {
    try {
        Optional<EmployeeOutDto>
        employeeDtoOptional = employeeService.loginEmployee(employeeLoginDto);
          return new ResponseEntity<>(employeeDtoOptional, HttpStatus.ACCEPTED);
    } catch (EmployeeNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
  }

  /**
   * Controller method to return list of All Employees.
   *
   * @param email
   * @param password
   * @return ResponseEntity with list of All Employees.
   */
  @GetMapping(value = "/listAllEmployees", produces = "application/json")
  public ResponseEntity<?> listAllEmployees(
      @RequestHeader final String email,
      @RequestHeader final String password) {
         try {
             authenticatingUser.checkIfUserIsAdmin(email, password);
         } catch  (UnauthorisedUserException e) {
           return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
         }
          Optional<List<EmployeeOutDto>> listOfAllEmployees =
               employeeService.listAllEmployees();
          return new ResponseEntity<>(listOfAllEmployees, HttpStatus.ACCEPTED);
  }

  /**
   *
   * @param email
   * @param password
   * @param employeeInDto
   * @return Response
   */
  @PostMapping("/saveEmployee")
  public ResponseEntity<?> saveEmployee(
          @RequestHeader final String email,
          @RequestHeader final String password,
          @RequestBody final EmployeeInDto employeeInDto) {

     try {
          authenticatingUser.checkIfUserIsAdmin(email, password);
     } catch (UnauthorisedUserException e) {
          return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
     }
     Optional<EmployeeOutDto> optional = null;
       try {
            optional =
            employeeService.saveEmployee(employeeInDto);
       } catch (EmployeeAlreadyExistException e) {
            return new ResponseEntity<>(
                  e.getMessage(), HttpStatus.CONFLICT);
       }
     return new ResponseEntity<>(optional, HttpStatus.CREATED);
  }

  /**
   *
   * @param email
   * @param changePasswordInDto
   * @return ResponseEntity
   */
  @PutMapping("/changePassword")
  public ResponseEntity<?> changePassword(
          @RequestHeader final String email,
          @RequestBody final ChangePasswordInDto changePasswordInDto
         ) {
       try {
          employeeService.changePassword(changePasswordInDto, email);
          return new ResponseEntity<>("Password changes successfully",
                HttpStatus.OK);
       } catch (Exception e) {
          return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
       }
  }
}

