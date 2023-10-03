package com.grievance.dto;

import com.grievance.entity.UserType;
import java.util.Objects;
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
  private String email;

  /**
   * The full name of the employee.
   */
  private String fullName;

  /**
   * The user type of the employee.
   */
  private UserType userType;

  /**
   * Indicates if the employee is a first-time user.
   */
  private Boolean firstTimeUser;

  /**
   * The department of the employee.
   */
  private String departmentOutDto;

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

  public String getDepartment() {
    return departmentOutDto;
  }

  /**
   * Set the department of the employee.
   *
   * @param departmentField the departmentDto to set
   */
  public void setDepartment(final String departmentField) {
    this.departmentOutDto = departmentField;
  }
  /**
   * Default constructor for EmployeeOutDto.
   */
  public EmployeeOutDto() {
    super();
  }

  /**
   * hashcode of this employeeOutDto objects.
   */
  @Override
  public int hashCode() {
    return Objects.hash(departmentOutDto, email,
        firstTimeUser, fullName, userType);
  }

  /**
   * equals method to compare object with this employeeOutDto.
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
    EmployeeOutDto other = (EmployeeOutDto) obj;
    return Objects.equals(departmentOutDto, other.departmentOutDto)
        && Objects.equals(email, other.email)
        && Objects.equals(firstTimeUser, other.firstTimeUser)
        && Objects.equals(fullName, other.fullName)
        && userType == other.userType;
  }

  /**
   * parameterised constructor.
   * @param emailField
   * @param fullNameField
   * @param userTypeField
   * @param firstTimeUserField
   * @param departmentOutDtoField
   */
  public EmployeeOutDto(
      final String emailField,
      @NotEmpty final String fullNameField,
      final UserType userTypeField,
      final Boolean firstTimeUserField,
      @NotEmpty final String departmentOutDtoField) {
    super();
    this.email = emailField;
    this.fullName = fullNameField;
    this.userType = userTypeField;
    this.firstTimeUser = firstTimeUserField;
    this.departmentOutDto = departmentOutDtoField;
  }

}
