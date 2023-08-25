/**
 * User Controller.
 */

package com.grievance.controller;

import com.grievance.entity.Employee;
import com.grievance.service.UserService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

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
 * Rest COntroller.
 */
@RestController
@CrossOrigin
@Validated
public class UserController {

  @Autowired
  UserService service;
  
  @PostMapping("/save")
  public ResponseEntity<?> saveUser(@Valid @RequestBody Employee user) {
    return ResponseEntity.ok(service.saveUser(user));
  }

  
  @GetMapping("/list")
  public ResponseEntity<?> listUser() {
    return ResponseEntity.ok(service.listUser());
  }

  /**
   * Method for login a user.
   * *@param employee
   * *@return 
   */
  @PostMapping("/login")
  public ResponseEntity<?> loginUser(@Valid @RequestBody Employee employee) {
    Optional<Boolean> answer = service.login(employee);
    
    if (answer.get()) {
      return new ResponseEntity<>("Login Successfully", HttpStatus.ACCEPTED);
    }
    return new ResponseEntity<>("Invalid Credentials", HttpStatus.UNAUTHORIZED);
  }	
}
