package com.grievance.service;

import com.grievance.entity.Employee;
import java.util.List;
import java.util.Optional;

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
  Optional<Boolean> login(Employee employee);

  /**
   * save user in database.
   *
   * @param employee of type Employee.
   *
   * @return Employee
   */
  Optional<Employee> saveUser(Employee employee);

  /**
   * listing user.
   *
   * @return List of Employee.
   */
  Optional<List<Employee>> listUser();
}
