/**
 *
 */
package com.grievance.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
 import com.grievance.entity.Status;
import com.grievance.entity.TicketType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
/**
 *
 */
public class TicketInDto {
  /**
   *minimumLength final integer.
   */
  private final int minimumLength = 1;
  /**
   *maximumLengthOfTitle final integer.
   */
  private final int maximumLengthOfTitle = 50;
  /**
   *maximumLengthOfDescription final integer.
   */
  private final int maximumLengthOfDescription = 500;


  /**
   * Integer ticketId of Ticket.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer ticketId;

  /**
   * The title of the ticket.
   */
  @NotEmpty
  @Size(
    min = minimumLength,
    max = maximumLengthOfTitle,
    message = "Title too long (max 50 characters)"
  )
  private String title;

  /**
   * The type of the ticket.
   */
  private TicketType ticketType;

  /**
   * The department associated with the ticket.
   */
  @JsonProperty("department")
  private DepartmentInDto departmentInDto;

  /**
   * The description of the ticket.
   */
  @NotEmpty
  @Size(
    min = minimumLength,
    max = maximumLengthOfDescription,
    message = "Description too long (max 500 characters)"
  )
  private String description;

  /**
   * The status of the ticket.
   */
  @NotEmpty
  private Status status;

  /**
   * employee that owns this ticket.
   */
  @JsonProperty("employee")
  private EmployeeInDto employeeInDto;

  /**
   * @return the ticketId
   */
  public Integer getTicketId() {
    return ticketId;
  }

  /**
   * @param ticketIdField the ticketId to set
   */
  public void setTicketId(final Integer ticketIdField) {
    this.ticketId = ticketIdField;
  }

  /**
   * @return the title
   */
  public String getTitle() {
    return title;
  }

  /**
   * @param titleField the title to set
   */
  public void setTitle(final String titleField) {
    this.title = titleField;
  }

  /**
   * @return the ticketType
   */
  public TicketType getTicketType() {
    return ticketType;
  }

  /**
   * @param ticketTypeField the ticketType to set
   */
  public void setTicketType(final TicketType ticketTypeField) {
    this.ticketType = ticketTypeField;
  }

  /**
   * @return the department
   */
  public DepartmentInDto getDepartment() {
    return departmentInDto;
  }

  /**
   * @param departmentField the department to set
   */
  public void setDepartment(final DepartmentInDto departmentField) {
    this.departmentInDto = departmentField;
  }

  /**
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * @param descriptionField the description to set
   */
  public void setDescription(final String descriptionField) {
    this.description = descriptionField;
  }

  /**
   * @return the status
   */
  public Status getStatus() {
    return status;
  }

  /**
   * @param statusField the status to set
   */
  public void setStatus(final Status statusField) {
    this.status = statusField;
  }

/**
 * @return the employeeInDto
 */
public EmployeeInDto getEmployeeInDto() {
return employeeInDto;
}

/**
 * @param employeeInDtoField the employeeInDto to set
 */
public void setEmployeeInDto(final EmployeeInDto employeeInDtoField) {
this.employeeInDto = employeeInDtoField;
}

}
