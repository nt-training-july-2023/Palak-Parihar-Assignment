/**
 * Department entity class for Employee.
 * This class represents the Department entity used in the application.
 * It contains information about
 * departments and their associated employees and tickets.
 *
 * @author palak
 */

package com.grievance.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * Department entity class.
 * This class represents the Department entity used in the application.
 * It contains information about
 * departments and their associated employees and tickets.
 */
@Entity
public class Department {
  /**
   * The unique identifier for the department.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dept_seq")
  private Integer departmentId;

  /**
   * The name of the department.
   */
  @NotEmpty
  @Column(nullable = false, unique = true)
  private String departmentName;

  /**
   * The list of employees associated with this department.
   */
  @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
  @JsonManagedReference
  private List<Employee> employees;

  /**
   * The list of tickets associated with this department.
   */
  @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
  @JsonManagedReference
  private List<Ticket> tickets;

  /**
   * Get the departmentId for this department.
   *
   * @return the departmentId
   */
  public Integer getDepartmentId() {
    return departmentId;
  }

  /**
   * Set the departmentId for this department.
   *
   * @param departmentIdField the departmentId to set
   */
  public void setDepartmentId(final Integer departmentIdField) {
    this.departmentId = departmentIdField;
  }

  /**
   * Get the departmentName for this department.
   *
   * @return the departmentName
   */
  public String getDepartmentName() {
    return departmentName;
  }

  /**
   * Set the departmentName for this department.
   *
   * @param departmentNameField the departmentName to set
   */
  public void setDepartmentName(final String departmentNameField) {
    this.departmentName = departmentNameField;
  }

  /**
   * Get the list of employees associated with this department.
   *
   * @return the employees
   */
  public List<Employee> getEmployees() {
    return employees;
  }

  /**
   * Set the list of employees associated with this department.
   *
   * @param employeesField the employees to set
   */
  public void setEmployees(final List<Employee> employeesField) {
    this.employees = employeesField;
  }

  /**
   * Get the list of tickets associated with this department.
   *
   * @return the tickets
   */
  public List<Ticket> getTickets() {
    return tickets;
  }

  /**
   * Set the list of tickets associated with this department.
   *
   * @param ticketsField the tickets to set
   */
  public void setTickets(final List<Ticket> ticketsField) {
    this.tickets = ticketsField;
  }

}
