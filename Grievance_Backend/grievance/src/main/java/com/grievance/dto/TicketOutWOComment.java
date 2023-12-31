package com.grievance.dto;

import java.util.Date;
import java.util.Objects;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.grievance.entity.Status;
import com.grievance.entity.TicketType;

public class TicketOutWOComment {

  /**
   * Integer ticketId of Ticket.
   */
  private Integer ticketId;

  /**
   * The title of the ticket.
   */
  private String title;

  /**
   * The type of the ticket.
   */
  private TicketType ticketType;

  /**
   * The department associated with the ticket.
   */
  @JsonProperty("department")
  private String department;

  /**
   * The status of the ticket.
   */
  @NotEmpty
  private Status status;

  /**
   * The date when the ticket was last updated.
   */
  @JsonFormat(pattern = "dd-MM-yyyy hh:mm aa", timezone = "Asia/Kolkata")
  private Date lastUpdated;

  /**
   * The employee who created the ticket.
   */
  @JsonProperty("employee")
  private String employeeOutDto;

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
  public String getDepartment() {
    return department;
  }

  /**
   * @param departmentField the department to set
   */
  public void setDepartment(final String departmentField) {
    this.department = departmentField;
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
   * @return the lastUpdated
   */
  public Date getLastUpdated() {
    return new Date(lastUpdated.getTime());
  }

  /**
   * @param lastUpdatedField the lastUpdated to set
   */
  public void setLastUpdated(final Date lastUpdatedField) {
    this.lastUpdated = new Date(lastUpdatedField.getTime());
  }

  /**
   * @return the employee
   */
  public String getEmployee() {
    return employeeOutDto;
  }

  /**
   * @param employeeField the employee to set
   */
  public void setEmployee(final String employeeField) {
    this.employeeOutDto = employeeField;
  }

  /**
   * hashcode of this TicketOutWOComment.
   */
  @Override
  public int hashCode() {
    return Objects.hash(department, employeeOutDto,
        lastUpdated, status, ticketId, ticketType, title);
  }

  /**
   * method to compare this object with other object.
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
    TicketOutWOComment other = (TicketOutWOComment) obj;
    return Objects.equals(department, other.department)
        && Objects.equals(employeeOutDto, other.employeeOutDto)
        && Objects.equals(lastUpdated, other.lastUpdated)
        && status == other.status
        && Objects.equals(ticketId, other.ticketId)
        && ticketType == other.ticketType
        && Objects.equals(title, other.title);
  }
}
