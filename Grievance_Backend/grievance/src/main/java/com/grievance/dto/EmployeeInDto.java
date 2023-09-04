package com.grievance.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.grievance.entity.UserType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * EmployeeInDto represents an input data transfer object
 * for employee-related information.
 * This class is used for transferring employee
 * data between layers of the application.
 */
public class EmployeeInDto {
  /**
   * The email of the employee (unique identifier).
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
   * The department of the employee.
   */
  @NotEmpty
  @JsonProperty("department")
  private DepartmentDto departmentDto;

  /**
   * Get the email of the employee.
   *
   * @return the email
   */
  public String getEmail() {
    return email;
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
   * @param pass the password to set
   */
  public void setPassword(final String pass) {
    this.password = pass;
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
   * @return the department
   */
  public DepartmentDto getDepartmentDto() {
    return departmentDto;
  }

  /**
   * Set the department of the employee.
   *
   * @param departmentField the department to set
   */
  public void setDepartmentDto(final DepartmentDto departmentField) {
    this.departmentDto = departmentField;
  }
  /**
   * Default constructor for EmployeeInDto.
   */
  public EmployeeInDto() {
    super();
  }

@Override public String toString(){return "EmployeeInDto [email=" + email + ", fullName=" + fullName + ", password=" + password + ", userType=" + userType + ", firstTimeUser=" + firstTimeUser + ", department=" + departmentDto + "]";}


  
}
