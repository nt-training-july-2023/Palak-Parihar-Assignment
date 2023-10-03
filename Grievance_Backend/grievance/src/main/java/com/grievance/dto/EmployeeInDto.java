package com.grievance.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.grievance.constants.ValidationConstants;
import com.grievance.entity.UserType;


import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

/**
 * EmployeeInDto represents an input data transfer object
 * for employee-related information.
 * This class is used for transferring employee
 * data between layers of the application.
 */
@Validated
public class EmployeeInDto {
  /**
   * The email of the employee (unique identifier).
   */
  @Id
  @Column(unique = true)
  @Email(regexp = "^[a-z0-9+_.-]+@+nucleusteq.com",
  message = ValidationConstants.EMAIL_VALIDATION)
  @NotEmpty(message = ValidationConstants.EMPTY_FIELD)
  private String email;

  /**
   * The full name of the employee.
   */
  @NotEmpty(message = ValidationConstants.EMPTY_FIELD)
  private String fullName;

  /**
   * The password of the employee.
   */
  @NotEmpty(message = ValidationConstants.EMPTY_FIELD)
  private String password;

  /**
   * The user type of the employee.
   */
  @NotNull(message = ValidationConstants.EMPTY_FIELD)
  private UserType userType;

  /**
   * The department of the employee.
   */
  @NotNull(message = ValidationConstants.EMPTY_FIELD)
  @JsonProperty("department")
  private DepartmentInDto department;

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
   * Get the department of the employee.
   *
   * @return the department
   */
  public DepartmentInDto getDepartmentDto() {
    return department;
  }

  /**
   * Set the department of the employee.
   *
   * @param departmentField the department to set
   */
  public void setDepartmentDto(final DepartmentInDto departmentField) {
    this.department = departmentField;
  }
  /**
   * Default constructor for EmployeeInDto.
   */
  public EmployeeInDto() {
    super();
  }

  /**
   * hashcode of employeeInDto.
   */
  @Override
  public int hashCode() {
    return Objects.hash(department, email,
        fullName, password, userType);
  }

  /**
   * equals method.
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
    EmployeeInDto other = (EmployeeInDto) obj;
    return Objects.equals(department, other.department)
        && Objects.equals(email, other.email)
        && Objects.equals(fullName, other.fullName)
        && Objects.equals(password, other.password)
        && userType == other.userType;
  }

  /**
   * parameterized constructor.
   * @param emailField
   * @param fullNameField
   * @param passwordField
   * @param userTypeField
   * @param departmentInDtoField
   */
  public EmployeeInDto(
      @Email(regexp = "^[A-Za-z0-9+_.-]+@nucleusteq.com(.+)$")
      @NotBlank final String emailField,
      @NotEmpty final String fullNameField,
      @NotEmpty final String passwordField,
      final UserType userTypeField,
      @NotEmpty final DepartmentInDto departmentInDtoField) {
    super();
    this.email = emailField;
    this.fullName = fullNameField;
    this.password = passwordField;
    this.userType = userTypeField;
    this.department = departmentInDtoField;
  }

}
