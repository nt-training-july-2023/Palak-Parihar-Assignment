/**
 * Employee entity class.
 *
 */

package com.grievance.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * Employee entity class.
 */
@Entity
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
  @NotEmpty
  private String fullName;

  /**
   * Password of employee .
   */
  @NotEmpty
  private String password;

  /**
   * Usertype of employee.
   */
  @NotEmpty
  private UserType userType;

  /**
   * Department of employee.
   */
  @NotEmpty
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "departmentId")
  private Department department;

  @NotEmpty
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL)
  private List<Ticket> tickets;

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

  /**
   * getter method for instance department.
   *
   * @return the department
   */
  public Department getDepartment() {
    return department;
  }

  /**
   * setter method for instance department.
   *
   * @param departmentField the department to set
   */
  public void setDepartment(final Department departmentField) {
    this.department = departmentField;
  }

  /**
   * @return the tickets
   */
  public List<Ticket> getTickets() {
    return tickets;
  }

  /**
   * @param tickets the tickets to set
   */
  public void setTickets(List<Ticket> tickets) {
    this.tickets = tickets;
  }
  
  
}
