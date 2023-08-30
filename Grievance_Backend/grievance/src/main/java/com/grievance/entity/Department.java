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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

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
  @Column(unique = true, nullable = false)
  private String departmentName;
  
  @OneToMany
  private List<Employee> employees;
  
  @OneToMany
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
return employees;}

/**
 * @param employees the employees to set
 */
public void setEmployees(List<Employee> employees) {
this.employees = employees;}

/**
 * @return the tickets
 */
public List<Ticket> getTickets() {
return tickets;}

/**
 * @param tickets the tickets to set
 */
public void setTickets(List<Ticket> tickets) {
this.tickets = tickets;}
  
  
  
}
