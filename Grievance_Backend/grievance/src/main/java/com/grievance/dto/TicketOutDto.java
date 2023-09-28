package com.grievance.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.grievance.entity.Status;
import com.grievance.entity.TicketType;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotEmpty;

public class TicketOutDto {
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
  @NotEmpty
  private TicketType ticketType;

  /**
   * The department associated with the ticket.
   */
  @JsonIgnore
  @JsonProperty("department")
  private String department;

  /**
   * The description of the ticket.
   */
  private String description;

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
   * comments that belongs to this ticket.
   */
  private List<CommentOutDto> comments;

  /**
   * The employee who created the ticket.
   */
  @JsonProperty("employee")
  @JsonIgnore
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
   * @return the lastUpdated
   */
  public Date getLastUpdated() {
    return lastUpdated;
  }

  /**
   * @param lastUpdatedField the lastUpdated to set
   */
  public void setLastUpdated(final Date lastUpdatedField) {
    this.lastUpdated = lastUpdatedField;
  }

  /**
   * @return the comments
   */
  public List<CommentOutDto> getComments() {
    return comments;
  }

  /**
   * @param commentsField the comments to set
   */
  public void setComments(final List<CommentOutDto> commentsField) {
    this.comments = commentsField;
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
   * hashcode of ticketOutDto.
   */
  @Override
  public int hashCode() {
    return Objects.hash(comments, department, description,
        employeeOutDto, status, ticketId, ticketType,
        title);
  }

  /**
   * method to compare ticketOutDto to object.
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
    TicketOutDto other = (TicketOutDto) obj;
    return Objects.equals(comments, other.comments)
        && Objects.equals(department, other.department)
        && Objects.equals(description, other.description)
        && Objects.equals(employeeOutDto, other.employeeOutDto)
        && status == other.status
        && Objects.equals(ticketId, other.ticketId)
        && ticketType == other.ticketType
        && Objects.equals(title, other.title);
  }
}
