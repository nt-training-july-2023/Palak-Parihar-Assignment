/**
 * User Controller.
 */

package com.grievance.controller;

import com.grievance.dto.EmployeeDto;
import com.grievance.dto.EmployeeLoginDto;
import com.grievance.entity.Employee;
import com.grievance.exception.ResourceNotFoundException;
import com.grievance.service.EmployeeService;
import java.util.Optional;
import javax.validation.Valid;
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
   * Controller method for login employee.
   *
   * @param employee
   *
   * @return Employee
 * @throws ResourceNotFoundException 
   *
   */
//  @PostMapping("/login")
//  public ResponseEntity<?> loginUser(
//    @Valid @RequestBody final EmployeeLoginDto employeeLoginDto
//  ) throws ResourceNotFoundException {
//    Optional<EmployeeDto> answer = employeeService
//      .loginEmployee(employeeLoginDto.getEmail(), employeeLoginDto.getPassword());
//
//    if (answer.isPresent()) {
//      return new ResponseEntity<>(answer.get(), HttpStatus.ACCEPTED);
//    }
//    throw new ResourceNotFoundException(employeeLoginDto.getEmail());
//  }

  /**
   * Controller method to save employee.
   *
   * @param employee
   *
   * @return Employee
   *
   */
//  @PostMapping("/saveEmployee")
//  public ResponseEntity<?> saveUser( @RequestBody final EmployeeDto employee) {
//	  System.out.println(employee);
////    try {
//      Optional<EmployeeDto> savedEmployee = employeeService.saveEmployee(employee);
//      return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
////    } catch (Exception e) {
////      return new ResponseEntity<>(null, HttpStatus.CONFLICT);
////    }
//  }

  /**
   * Controller method to list employee.
   *
   * @return List Employee.
   *
   */
  @GetMapping("/list")
  public ResponseEntity<?> listUser() {
    return ResponseEntity.ok(employeeService.listAllEmployees());
  }
  
  @PostMapping("/save")
  public ResponseEntity<?> save(@RequestBody final EmployeeDto employeeDto){
	  System.out.println(employeeDto);
	  return ResponseEntity.ok(employeeService.saveEmployee(employeeDto));
  }
}













