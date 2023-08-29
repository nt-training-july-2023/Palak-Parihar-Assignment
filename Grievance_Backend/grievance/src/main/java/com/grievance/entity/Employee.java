/**
 *Employee Entity.
 */

package com.grievance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 *Employee Entity.
 */

@Entity
public class Employee {
  /**
   * email instance.
   */
  @Id
  @Column(unique = true)
  @Email
  @NotBlank
  private String email;

  /**
   * password instance.
   */
  @NotBlank
  private String password;

  /**
   * This is getter method for email.
   *
   * @return the name.
   */
  public String getEmail() {
    return email;
  }

  /**
   * This is getter method for Password.
   *
   * @return the password.
   */
  public String getPassword() {
    return password;
  }

  /**
   * This is setter method for password.
   *
   * @param pass of type String.
   */
  public void setPassword(final String pass) {
    this.password = pass;
  }

  /**
   * This is a setter method for email.
   *
   * @param emailField the email to set.
   */
  public void setEmail(final String emailField) {
    this.email = emailField;
  }

  /**
   * toString method.
   */
  @Override
  public String toString() {
    return "Employee [email=" + email + ", password=" + password + "]";
  }
}
