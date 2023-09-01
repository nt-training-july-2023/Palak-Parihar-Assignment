/**
 * Department entity class for Employee.
 *
 *
 *@author palak.
 *
 *
 */

package com.grievance.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.grievance.dto.DepartmentDto;

/**
 * Department entity class.
 *
 */
@Entity
public class Department {
  /**
   *departmentId instance for department.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dept_seq")
  private Integer departmentId;

  /**
   * departmentName for department.
   */
  @NotEmpty
  @Column(nullable = false, unique = true)
  private String departmentName;

  @JsonIgnore
  @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
  private List<Employee> employees;

  @JsonIgnore
  @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
  private List<Ticket> tickets;

  /**
   * getter method to get departmentId for department.
   *
   * @return the departmentId
   */
  public Integer getDepartmentId() {
    return departmentId;
  }

  /**
   * setter method to get departmentId for department.
   *
   * @param departmentIdField the departmentId to set
   */
  public void setDepartmentId(final Integer departmentIdField) {
    this.departmentId = departmentIdField;
  }

  /**
   * getter method to get departmentName for department.
   *
   * @return the departmentName
   */
  public String getDepartmentName() {
    return departmentName;
  }

  /**
   * setter method to get departmentName for department.
   *
   * @param departmentNameField the departmentName to set
   */
  public void setDepartmentName(final String departmentNameField) {
    this.departmentName = departmentNameField;
  }

  /**
   * @return the employees
   */
  public List<Employee> getEmployees() {
    return employees;
  }

  /**
   * @param employees the employees to set
   */
  public void setEmployees(List<Employee> employees) {
    this.employees = employees;
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

//  @Override
//  public String toString() {
//    return (
//      "Department [departmentId=" +
//      departmentId +
//      ", departmentName=" +
//      departmentName +
//      ", employees=" +
//      employees +
//      ", tickets=" +
//      tickets +
//      "]"
//    );
//  }
  
  public DepartmentDto toDto() {
	  DepartmentDto departmentDto = new DepartmentDto();
	  departmentDto.setDepartmentId(departmentId);
	  departmentDto.setDepartmentName(departmentName);
	  return departmentDto;
  }
}
