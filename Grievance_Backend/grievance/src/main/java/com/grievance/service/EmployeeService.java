package com.grievance.service;

import com.grievance.dto.ChangePasswordInDto;
import com.grievance.dto.EmployeeInDto;
import com.grievance.dto.EmployeeLoginDto;
import com.grievance.dto.EmployeeOutDto;
import java.util.List;
import java.util.Optional;

/**
 *The Employee Service functionality provides
 *functionality for managing employee of application.
 */
public interface EmployeeService {
  /**
   * Saves a new employee record.
   *
   * @param employeeInDto The input DTO containing
   *     employee information to be saved.
   * @return An optional containing the saved employee
   *     details in the form of an EmployeeOutDto.
   */
  Optional<EmployeeOutDto> saveEmployee(EmployeeInDto employeeInDto);

  /**
   * Performs employee login based on email and password.
   *
   * @param employeeLoginDto The input DTO containing
   *     email and password for employee login.
   * @return An optional containing the employee
   *     details in the form of an
   *     EmployeeOutDto if login is successful;
   *         otherwise, an empty optional.
   */
  Optional<EmployeeOutDto> loginEmployee(EmployeeLoginDto employeeLoginDto);

  /**
   * Retrieves a list of all employees.
   * @param page
   * @return An optional containing a list of
   *     EmployeeOutDto objects representing all employees.
   */
  Optional<List<EmployeeOutDto>> listAllEmployees(Integer page);

  /**
   * changePassword for existing user.
   *@param changePasswordInDto
   *@param email
   *
   */
  void changePassword(ChangePasswordInDto changePasswordInDto, String email);

  /**
   * delete employee by Id.
   * @param email
   * @param deleteEmployee
   */
  void deleteEmployeeById(String email, Integer deleteEmployee);

}
