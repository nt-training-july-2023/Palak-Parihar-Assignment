/**
 *Employee Entity.
 */

package com.grievance.dto;

import com.grievance.entity.Employee;
import com.grievance.entity.Ticket;
import com.grievance.entity.UserType;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 *EmployeeDto .
 */

public class EmployeeDto {
  /**
   * The id of employee.
   */
  @Id
  @Column(unique = true)
  @Email(regexp = "^[A-Za-z0-9+_.-]+@nucleusteq.com(.+)$")
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
  private UserType userType;

  private Boolean firstTimeUser = true;

  /**
   * Department of employee.
   */
  @NotEmpty
  private DepartmentDto departmentDto;

  @NotEmpty
  private List<Ticket> tickets;

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
   * @return the fullName
   */
  public String getFullName() {
    return fullName;
  }

  /**
   * @param fullName the fullName to set
   */
  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  /**
   * @return the userType
   */
  public UserType getUserType() {
    return userType;
  }

  /**
   * @param userType the userType to set
   */
  public void setUserType(UserType userType) {
    this.userType = userType;
  }

  /**
   * @return the firstTimeUser
   */
  public Boolean getFirstTimeUser() {
    return firstTimeUser;
  }

  /**
   * @param firstTimeUser the firstTimeUser to set
   */
  public void setFirstTimeUser(Boolean firstTimeUser) {
    this.firstTimeUser = firstTimeUser;
  }

  /**
   * @return the department
   */
  public DepartmentDto getDepartment() {
    return departmentDto;
  }

  /**
   * @param department the department to set
   */
  public void setDepartment(DepartmentDto departmentDto) {
    this.departmentDto = departmentDto;
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

  /**
   * toString method.
   */

//  public EmployeeDto toDto(Employee employee) {
//	  EmployeeDto employeeDto = new EmployeeDto();
//    employeeDto.departmentDto = new DepartmentDto(employee.getDepartment());
//    employeeDto.email = employee.getEmail();
////    employeeDto.firstTimeUser = employee.getFirstTimeUser();
//    employeeDto.fullName = employee.getFullName();
//    employeeDto.password = employee.getPassword();
//    employeeDto.userType = employee.getUserType();
//    return employeeDto;
//  }

  @Override
  public String toString() {
    return (
      "EmployeeDto [email=" +
      email +
      ", fullName=" +
      fullName +
      ", password=" +
      password +
      ", userType=" +
      userType +
      ", firstTimeUser=" +
      firstTimeUser +
      ", departmentDto=" +
      departmentDto +
      ", tickets=" +
      tickets +
      "]"
    );
  }

  public EmployeeDto() {
    super();
    // TODO Auto-generated constructor stub}

  }
  public Employee toEntity() {
	  Employee employee = new Employee();
	  employee.setDepartment(departmentDto.toEntity());
	  employee.setEmail(email);
	  employee.setFullName(fullName);
	  employee.setPassword(password);
	  employee.setUserType(userType);
	  return employee;
  }
}
