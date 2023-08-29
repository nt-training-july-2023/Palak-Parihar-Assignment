/**
 * Employee entity class.
 *
 */

package com.grievance.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * Employee entity class.
 */
public class Employee {

  /**
   * The id of employee.
   */
  @Id
  @Column(unique = true)
  @Email
  @NotBlank
  private String email;
  /**
   * Full Name of employee.
   */
  private String fullName;
  /**
   * Password of employee .
   */
  private String password;
  /**
   * Usertype of employee.
   */
  private UserType userType;
  /**
   * getter method for instance fullName.
   *
   * @return the fullName
   *
   */

  public String getFullName() {
    return fullName;
  }

  /**
   * setter method for instance fullName.
   *
   * @param fullNameField the fullName to set
   */
  public void setFullName(final String fullNameField) {
    this.fullName = fullNameField;
  }

  /**
   * getter method to get instance email.
   *
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * setter method for instance email.
   *
   * @param emailField the email to set
   */
  public void setEmail(final String emailField) {
    this.email = emailField;
  }

  /**
   * getter method for instance password.
   *
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * setter method for instance password.
   *
   * @param passwordField the password to set
   */
  public void setPassword(final String passwordField) {
    this.password = passwordField;
  }

  /**
   * getter method for instance userType.
   *
   * @return the userType
   */
  public UserType getUserType() {
    return userType;
  }

  /**
   * setter method for instance userType.
   *
   * @param userTypeField the userType to set
   */
  public void setUserType(final UserType userTypeField) {
    this.userType = userTypeField;
  }
}
