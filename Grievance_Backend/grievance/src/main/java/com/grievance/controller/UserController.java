/**
 * User Controller.
 */

package com.grievance.controller;

import com.grievance.dto.EmployeeDto;
import com.grievance.entity.Employee;
import com.grievance.service.UserService;
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
public class UserController {
  /**
   *Autowiring Service.
   */
  @Autowired
  private UserService service;

  /**
   * Controller method for login employee.
   *
   * @param employee
   *
   * @return Employee
   *
   */
  @PostMapping("/login")
  public
      ResponseEntity<?>
        loginUser(@Valid @RequestBody final Employee employee) {
    Optional<Boolean> answer = service.login(employee);

    if (answer.get()) {
      return new ResponseEntity<>("Login Successfully", HttpStatus.ACCEPTED);
    }
    return new ResponseEntity<>("Invalid Credentials", HttpStatus.UNAUTHORIZED);
  }

  /**
   * Controller method to save employee.
   *
   * @param employee
   *
   * @return Employee
   *
   */
  @PostMapping("/save")
  public
      ResponseEntity<?>
        saveUser(@Valid @RequestBody final Employee employee) {
    return ResponseEntity.ok(service.saveUser(employee));
  }

  /**
   * Controller method to list employee.
   *
   * @return List Employee.
   *
   */
  @GetMapping("/list")
  public ResponseEntity<?> listUser() {
    return ResponseEntity.ok(service.listUser());
  }
}
