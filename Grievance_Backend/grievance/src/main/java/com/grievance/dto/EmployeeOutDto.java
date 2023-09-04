package com.grievance.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.grievance.entity.Ticket;
import com.grievance.entity.UserType;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * EmployeeOutDto represents an output data
 * transfer object for employee-related information.
 * This class is used for transferring employee
 * data between layers of the application.
 */
public class EmployeeOutDto {
  /**
   * email String of EmployeeOut DTO.
   */
  @Id
  @Column(unique = true)
  @Email(regexp = "^[A-Za-z0-9+_.-]+@nucleusteq.com(.+)$")
  @NotBlank
  private String email;

  /**
   * The full name of the employee.
   */
  @NotEmpty
  private String fullName;

  /**
   * The user type of the employee.
   */
  private UserType userType;

  /**
   * Indicates if the employee is a first-time user.
   */
  private Boolean firstTimeUser = true;

  /**
   * The department of the employee.
   */
  @NotEmpty
  private DepartmentDto departmentDto;

  /**
   * The list of tickets associated with the employee.
   */
  @NotEmpty
  @JsonIgnore
  private List<Ticket> tickets;

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
   * Get the department of the employee.
   *
   * @return the departmentDto
   */
  public DepartmentDto getDepartment() {
    return departmentDto;
  }

  /**
   * Set the department of the employee.
   *
   * @param departmentField the departmentDto to set
   */
  public void setDepartment(final DepartmentDto departmentField) {
    this.departmentDto = departmentField;
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
   * Default constructor for EmployeeOutDto.
   */
  public EmployeeOutDto() {
    super();
    // TODO Auto-generated constructor stub
  }
}
