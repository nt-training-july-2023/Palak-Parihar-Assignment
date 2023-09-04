/**
 * User Controller.
 */

package com.grievance.controller;

import com.grievance.dto.EmployeeInDto;
import com.grievance.dto.EmployeeLoginDto;
import com.grievance.dto.EmployeeOutDto;
import com.grievance.service.EmployeeService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest Controller.
 */
@RestController
@CrossOrigin("*")
@Validated
public class EmployeeController {
  /**
   *Autowiring Service.
   */
  @Autowired
  private EmployeeService employeeService;

  /**
   * Controller method for login in a Employee.
   *
   * @param employeeLoginDto for EmployeeLoginDto.
   * @return ResponseEntity.
   */
  @PostMapping("/login")
  public ResponseEntity<?> loginUser(
      @RequestBody final EmployeeLoginDto employeeLoginDto
  ) {
    Optional<EmployeeOutDto>
    employeeDtoOptional = employeeService.loginEmployee(employeeLoginDto);
    if (employeeDtoOptional.isPresent()) {
      return new ResponseEntity<>(employeeDtoOptional, HttpStatus.ACCEPTED);
    }
    return new ResponseEntity<>("Invalid Credentials", HttpStatus.CONFLICT);
  }

  /**
   * Controller method to return list of All Employees.
   *
   * @return ResponseEntity with list of All Employees.
   */
  @GetMapping("/list")
  public ResponseEntity<?> listOfAllEmployees() {
    return ResponseEntity.ok(employeeService.listAllEmployees());
  }

  /**
   * Controller method for saving Employee.
   *
   * @param employeeInDto of EmployeeInSto.
   *
   * @return ResponseEntity with employee
   *
   */
  @PostMapping("/save")
  public ResponseEntity<?> save(
  @RequestBody final EmployeeInDto employeeInDto) {
    //    System.out.println(employeeInDto);
    return ResponseEntity.ok(employeeService.saveEmployee(employeeInDto));
  }
}
