/**
 *
 */
package com.grievance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 *
 */

@Entity
public class Employee {
  @Id
  @Column(unique = true)
  @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
  @NotBlank
  private String email;

  @NotBlank
  private String password;

  /**
   * @return the name
   */
  public String getEmail() {
    return email;
  }

  /**
   * @param name the name to set
   */
  public void setEmail(String name) {
    this.email = name;
  }

  /**
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * @param password the password to set
   */
  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "Employee [email=" + email + ", password=" + password + "]";
  }
}
