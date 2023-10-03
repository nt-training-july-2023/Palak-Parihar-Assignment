/**
 *
 */
package com.grievance.authentication;

import com.grievance.entity.Employee;
import com.grievance.entity.UserType;
import com.grievance.repository.EmployeeRepository;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 *
 */
@Configuration
public class AuthenticatingUserImpl implements AuthenticateUser {
  /**
   * employeeRepository instance for
   * access to database.
   */
  @Autowired
  private EmployeeRepository employeeRepository;

  /**
   * method to check if user exists or not.
   * @param email
   * @param password
   * @return boolean
   */
  @Override
  public Boolean checkIfUserExists(final String email, final String password) {
      Employee employee = employeeRepository.findByEmailAndPassword(
              email, password);
    return !Objects.isNull(employee);
  }

  /**
   * method to check if user is authorised or not.
   * @param email
   * @param password
   * @return boolean
   */
  @Override
  public Boolean checkIfUserIsAdmin(
        final String email, final String password) {
       Employee employee = employeeRepository
                .findByEmailAndPasswordAndUserType(
                     email, password, UserType.ADMIN);
        return !Objects.isNull(employee);
  }

  /**
   * method to check if user is login in first time.
   * @param email
   * @param password
   * @return boolean.
   */
  @Override
  public Boolean checkIfUserisFirstTimeLogin(final String email,
      final String password) {
    return employeeRepository
        .existsByEmailAndPasswordAndFirstTimeUser(email, password, true);
  }
}
