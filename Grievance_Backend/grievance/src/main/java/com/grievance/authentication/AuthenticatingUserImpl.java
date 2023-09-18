/**
 *
 */
package com.grievance.authentication;

import com.grievance.entity.Employee;
import com.grievance.entity.UserType;
import com.grievance.repository.EmployeeRepository;
import com.grievance.service.Base64DecodeService;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 *
 */
@Configuration
public class AuthenticatingUserImpl implements AuthenticatingUser {
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
      String decodePassword = Base64DecodeService.decodeBase64ToString(
             password);
      Employee employee = employeeRepository.findByEmailAndPassword(
              email, decodePassword);
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
       String decodePassword = Base64DecodeService.decodeBase64ToString(
              password);
       Employee employee = employeeRepository
                .findByEmailAndPasswordAndUserType(
                     email, decodePassword, UserType.ADMIN);
        return !Objects.isNull(employee);
  }
}
