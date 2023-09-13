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
import com.grievance.exception.PasswordMismatchException;
import com.grievance.exception.ResourceNotFoundException;
import com.grievance.exception.UnauthorisedUserException;
import com.grievance.service.EmployeeService;

//import java.util.Base64;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

//    Base64.Decoder decoder = Base64.getDecoder();
//    byte[] pass = decoder.decode(employeeLoginDto.getPassword());
//    employeeLoginDto.setPassword(new String(pass));

    Optional<EmployeeOutDto>
    employeeDtoOptional = employeeService.loginEmployee(employeeLoginDto);
    if (employeeDtoOptional.isPresent()) {
      return new ResponseEntity<>(employeeDtoOptional, HttpStatus.ACCEPTED);
    }
    return new ResponseEntity<>("Invalid Credentials", HttpStatus.UNAUTHORIZED);
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
      @RequestHeader final String email, @RequestHeader final String password) {
         try {
             authenticatingUser.checkIfUserIsAdmin(email, password);
         } catch  (UnauthorisedUserException e) {
           return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
         }
          Optional<List<EmployeeOutDto>> listOfAllEmployees =
               employeeService.listAllEmployees();
          return new ResponseEntity<>(listOfAllEmployees, HttpStatus.ACCEPTED);
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
   * @param password
   * @param changePasswordInDto
   * @return ResponseEntity
   */
  @PutMapping("/changePassword")
  public ResponseEntity<?> changePassword(
          @RequestHeader final String email,
          @RequestHeader final String password,
          @RequestBody final ChangePasswordInDto changePasswordInDto
         ) {
        try {
          authenticatingUser.checkIfUserExists(email, password);
          try {
              Boolean passwordChanged = employeeService.changePassword(
                        changePasswordInDto.getOldPassword(),
                        changePasswordInDto.getNewPassword(), email);
              if (passwordChanged) {
                 return new ResponseEntity<>("Password changed successfully",
                        HttpStatus.NO_CONTENT);
              }
           } catch (PasswordMismatchException e) {
                 return new ResponseEntity<>(e.getMessage(),
                        HttpStatus.NOT_FOUND);
          }
       } catch (UnauthorisedUserException e) {
           return new ResponseEntity<>(e.getMessage(),
                   HttpStatus.UNAUTHORIZED);
       }
       return new ResponseEntity<>("Something unexpected happened",
             HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
