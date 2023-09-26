/**
 * EmployeeLoginDto represents a data
 * transfer object for employee login information.
 * This class is used for transferring
 * employee login data between layers of the application.
 */

package com.grievance.dto;

import java.util.Objects;

/**
 * Data transfer object for employee login information.
 */
public class EmployeeLoginDto {
  /**
   * email of EmployeeLogin Dto.
   */
  private String email;
  /**
   * String password of EmaployeeLogin DTO.
   */
  private String password;

  /**
   * Get the email.
   *
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * Set the email.
   *
   * @param emailField the email to set
   */
  public void setEmail(final String emailField) {
    this.email = emailField;
  }

  /**
   * Get the password.
   *
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * Set the password.
   *
   * @param passwordField the password to set
   */
  public void setPassword(final String passwordField) {
    this.password = passwordField;
  }

  /**
   * hashCode of this employeeLoginDto.
   */
  @Override
  public int hashCode() {
    return Objects.hash(email, password);
  }

  /**
   * method to compare this employeeLoginDto with object.
   */
  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    EmployeeLoginDto other = (EmployeeLoginDto) obj;
    return Objects.equals(email, other.email)
        && Objects.equals(password, other.password);
  }

  /**
   * parameterized constructor.
   * @param emailField
   * @param passwordField
   */
  public EmployeeLoginDto(
      final String emailField,
      final String passwordField) {
    super();
    this.email = emailField;
    this.password = passwordField;
  }

  /**
   * default constructor.
   */
  public EmployeeLoginDto() {
    super();
  }

}
