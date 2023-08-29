package com.grievance.service;

import java.util.List;
import java.util.Optional;

import com.grievance.dto.EmployeeDto;

/**
 * User Service Interface.
 */
public interface UserService {
  /**
   * login method.
   *
   * @param employee of type Employee.
   *
   * @return Boolean
   */
  Optional<Boolean> login(EmployeeDto employee);

  /**
   * save user in database.
   *
   * @param employee of type Employee.
   *
   * @return Employee
   */
  Optional<EmployeeDto> saveUser(EmployeeDto employee);

  /**
   * listing user.
   *
   * @return List of Employee.
   */
  Optional<List<EmployeeDto>> listUser();
}
