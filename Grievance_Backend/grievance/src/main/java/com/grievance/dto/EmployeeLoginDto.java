/**
 * EmployeeLoginDto represents a data
 * transfer object for employee login information.
 * This class is used for transferring
 * employee login data between layers of the application.
 */

package com.grievance.dto;

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
   *toString of EmployeeLogin Dto.
   */
  @Override
  public String toString() {
    return "EmployeeLoginDto [email=" + email + ", password=" + password + "]";
  }
}
