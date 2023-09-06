/**
 * User Controller.
 */

package com.grievance.controller;

import com.grievance.authentication.AuthenticatingUser;
import com.grievance.dto.EmployeeInDto;
import com.grievance.dto.EmployeeLoginDto;
import com.grievance.dto.EmployeeOutDto;
import com.grievance.exception.EmployeeAlreadyExistException;
import com.grievance.exception.ResourceNotFoundException;
import com.grievance.service.EmployeeService;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
   * The authenicatingUser instance
   * provides information about user being authorised.
   */
//  private AuthenticatingUserImpl authenticatingUserImpl
//       = new AuthenticatingUserImpl();
  @Autowired
  private AuthenticatingUser authenticatingUser;

  /**
   * Controller method for login in a Employee.
   *
   * @param employeeLoginDto for EmployeeLoginDto.
   * @return ResponseEntity.
 * @throws ResourceNotFoundException
   */
  @PostMapping("/login")
  public ResponseEntity<?> loginUser(
      @RequestBody final EmployeeLoginDto employeeLoginDto
  ) throws ResourceNotFoundException {
    Optional<EmployeeOutDto>
    employeeDtoOptional = employeeService.loginEmployee(employeeLoginDto);
    if (employeeDtoOptional.isPresent()) {
      return new ResponseEntity<>(employeeDtoOptional, HttpStatus.ACCEPTED);
    }
    throw new ResourceNotFoundException(employeeLoginDto.getEmail());
  }

  /**
   * Controller method to return list of All Employees.
   *
   * @param email
   * @param password
   * @return ResponseEntity with list of All Employees.
   */
  @GetMapping("/listAllEmployees")
  public ResponseEntity<?> listAllEmployees(
      @RequestParam final String email, @RequestParam final String password) {
         Boolean boolean1 =
            authenticatingUser.checkIfUserIsAdmin(email, password);
         if (boolean1) {
          Optional<List<EmployeeOutDto>> listOfAllEmployees =
               employeeService.listAllEmployees();
          return new ResponseEntity<>(listOfAllEmployees, HttpStatus.ACCEPTED);
          }
         return new ResponseEntity<>("Invalid Authorization",
             HttpStatus.UNAUTHORIZED);
  }

//  /**
//   * Controller method for saving Employee.
//   *
//   * @param employeeInDto of EmployeeInSto.
//   *
//   * @return ResponseEntity with employee
//   *
//   */
//  @PostMapping("/save")
//  public ResponseEntity<?> save(
//  @RequestBody final EmployeeInDto employeeInDto) {
//      Optional<EmployeeOutDto> optional =
//         employeeService.saveEmployee(employeeInDto);
//      if  (!optional.isPresent()) {
//         throw new EmployeeAlreadyExistException(employeeInDto.getEmail());
//      }
//    return new ResponseEntity<>(optional, HttpStatus.ACCEPTED);
//  }

  /**
   *
   * @param email
   * @param password
   * @param employeeInDto
   * @return Response
   */
  @PostMapping("/saveEmployee")
  public ResponseEntity<?> saveEmployee(
          @RequestParam final String email,
          @RequestParam final String password,
          @RequestBody final EmployeeInDto employeeInDto) {

     Boolean boolean1 =
     authenticatingUser.checkIfUserIsAdmin(email, password);
     if (boolean1) {
       Optional<EmployeeOutDto> optional =
             employeeService.saveEmployee(employeeInDto);
               if  (!optional.isPresent()) {
                     throw new EmployeeAlreadyExistException(
                       employeeInDto.getEmail());
               }
               return new ResponseEntity<>(optional, HttpStatus.ACCEPTED);
            }
       return new ResponseEntity<>("Invalid Authorization",
           HttpStatus.UNAUTHORIZED);
  }
}
