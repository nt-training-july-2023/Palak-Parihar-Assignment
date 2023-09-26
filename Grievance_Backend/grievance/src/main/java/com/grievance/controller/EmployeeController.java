/**
 * User Controller.
 */

package com.grievance.controller;

import com.grievance.dto.ChangePasswordInDto;
import com.grievance.dto.EmployeeInDto;
import com.grievance.dto.EmployeeLoginDto;
import com.grievance.dto.EmployeeOutDto;
import com.grievance.service.EmployeeService;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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

/**
 * Rest Controller.
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/employee")
public class EmployeeController {
  /**
   * Autowiring Service.
   */
  @Autowired
  private EmployeeService employeeService;
  /**
   * Controller method for login in a Employee.
   *
   * @param employeeLoginDto for EmployeeLoginDto.
   * @return ResponseEntity.
   * @throws EmployeeNotFoundException
   */
  @PostMapping("/login")
  public ResponseEntity<?> loginUser(
      @Valid @RequestBody final EmployeeLoginDto employeeLoginDto) {
      Optional<EmployeeOutDto> employeeDtoOptional =
          employeeService.loginEmployee(employeeLoginDto);
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
  @GetMapping(value = "/listAllEmployees")
  public ResponseEntity<?> listAllEmployees(
      @RequestHeader final String email,
      @RequestHeader final String password,
      @RequestParam final Integer page) {
    Optional<List<EmployeeOutDto>> listOfAllEmployees =
        employeeService.listAllEmployees(page);
    return new ResponseEntity<>(listOfAllEmployees.get(), HttpStatus.ACCEPTED);
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
    Optional<EmployeeOutDto> optional =
        employeeService.saveEmployee(employeeInDto);
    return new ResponseEntity<>(optional.get(), HttpStatus.CREATED);
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
      @RequestBody final ChangePasswordInDto changePasswordInDto) {
      employeeService.changePassword(changePasswordInDto, email);
      return new ResponseEntity<>("Password changes successfully",
          HttpStatus.OK);
  }

  /**
   * @param email
   * @return Responseentity.
   */
  @DeleteMapping("/delete")
  public ResponseEntity<?> deleteEmployee(
      @RequestParam final String email) {
    employeeService.deleteEmployeeById(email);
    return new ResponseEntity<>("Employee deleted successfully",
        HttpStatus.NO_CONTENT);
  }
}
