package com.grievance.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * Employee entity class. This class represents the Employee entity used in the
 * application. It contains information about employees, such as email, full
 * name, password, user type, department, and associated tickets.
 */
@Entity
public class Employee {
  /**
   * The employeeId, unique identifier of employee.
   */
  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "emp_seq")
  private Integer employeeId;
  /**
   * The email of the employee (unique identifier).
   */
  @Column(unique = true)
  @Email(regexp = "^[A-Za-z0-9._%+-]+@nucleusteq\\.com$")
  private String email;

  /**
   * The full name of the employee.
   */
  @NotEmpty
  private String fullName;

  /**
   * The password of the employee.
   */
  @NotEmpty
  private String password;

  /**
   * The user type of the employee.
   */
  private UserType userType;

  /**
   * Indicates if the employee is a first-time user.
   */
  private Boolean firstTimeUser = true;

  /**
   * The department to which the employee belongs.
   */
  @ManyToOne
  @JoinColumn(name = "departmentId")
  @JsonBackReference
  private Department department;

  /**
   * The list of tickets associated with the employee.
   */
  @JsonManagedReference
  @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
  private List<Ticket> tickets;


  /**
   * get the employeeId of employee.
   *
   * @return the employeeId
   */
  public Integer getEmployeeId() {
    return employeeId;
  }

  /**
   * set the employeeId of employee.
   *
   * @param employeeIdField the employeeId to set
   */
  public void setEmployeeId(final Integer employeeIdField) {
    this.employeeId = employeeIdField;
  }

  /**
   * Get the full name of the employee.
   *
   * @return the fullName
   */
  public String getFullName() {
    return fullName;
  }

  /**
   * Set the full name of the employee.
   *
   * @param fullNameField the fullName to set
   */
  public void setFullName(final String fullNameField) {
    this.fullName = fullNameField;
  }

  /**
   * Get the email of the employee.
   *
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * Set the email of the employee.
   *
   * @param emailField the email to set
   */
  public void setEmail(final String emailField) {
    this.email = emailField;
  }

  /**
   * Get the password of the employee.
   *
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * Set the password of the employee.
   *
   * @param passwordField the password to set
   */
  public void setPassword(final String passwordField) {
    this.password = passwordField;
  }

  /**
   * Get the user type of the employee.
   *
   * @return the userType
   */
  public UserType getUserType() {
    return userType;
  }

  /**
   * Set the user type of the employee.
   *
   * @param userTypeField the userType to set
   */
  public void setUserType(final UserType userTypeField) {
    this.userType = userTypeField;
  }

  /**
   * Get the department of the employee.
   *
   * @return the department
   */
  public Department getDepartment() {
    return department;
  }

  /**
   * Set the department of the employee.
   *
   * @param departmentField the department to set
   */
  public void setDepartment(final Department departmentField) {
    this.department = departmentField;
  }

  /**
   * Get the status of whether the employee is a first-time user.
   *
   * @return the firstTimeUser
   */
  public Boolean getFirstTimeUser() {
    return firstTimeUser;
  }

  /**
   * Set the status of whether the employee is a first-time user.
   *
   * @param firstTimeUserField the firstTimeUser to set
   */
  public void setFirstTimeUser(final Boolean firstTimeUserField) {
    this.firstTimeUser = firstTimeUserField;
  }

  /**
   * Get the list of tickets associated with the employee.
   *
   * @return the tickets
   */
  public List<Ticket> getTickets() {
    return tickets;
  }

  /**
   * Set the list of tickets associated with the employee.
   *
   * @param ticketsField the tickets to set
   */
  public void setTickets(final List<Ticket> ticketsField) {
    this.tickets = ticketsField;
  }

  /**
   * Default constructor for Employee.
   */
  public Employee() {
  }

  /**
   * toString.
   */
  @Override
  public String toString() {
    return email;
  }

  /**
   * hashcode of this object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(employeeId, department, email, firstTimeUser,
        fullName, password, tickets, userType);
  }

  /**
   * method to compare two department objects.
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
    Employee other = (Employee) obj;
    return Objects.equals(employeeId, other.employeeId)
        && Objects.equals(department, other.department)
        && Objects.equals(email, other.email)
        && Objects.equals(firstTimeUser, other.firstTimeUser)
        && Objects.equals(fullName, other.fullName)
        && Objects.equals(password, other.password)
        && userType == other.userType;
  }

  /**
   * parameterised contructor.
   * @param employeeIdField
   * @param emailField
   * @param fullNameField
   * @param passwordField
   * @param userTypeField
   * @param firstTimeUserField
   * @param departmentField
   */
  public Employee(
      final Integer employeeIdField,
      @Email(regexp = "^[A-Za-z0-9._%+-]+@nucleusteq\\.com$")
      @NotBlank final String emailField,
      @NotEmpty final String fullNameField,
      @NotEmpty final String passwordField,
      final UserType userTypeField,
      final Boolean firstTimeUserField,
      final Department departmentField) {
    super();
    this.employeeId = employeeIdField;
    this.email = emailField;
    this.fullName = fullNameField;
    this.password = passwordField;
    this.userType = userTypeField;
    this.firstTimeUser = firstTimeUserField;
    this.department = departmentField;
  }
}
