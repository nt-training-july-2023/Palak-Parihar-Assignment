/**
 *
 */
package com.grievance.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.grievance.constants.ValidationConstants;
import com.grievance.entity.Status;
import com.grievance.entity.TicketType;

import java.util.Objects;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 */
public class TicketInDto {
  /**
   * minimumLength final integer.
   */
  private static final int MINIMUM_LENGTH = 1;
  /**
   * maximumLengthOfTitle final integer.
   */
  private static final int MAXIMUM_LENGTH_OF_TITLE = 50;
  /**
   * maximumLengthOfDescription final integer.
   */
  private static final int MAXIMUMN_LENGTH_OF_DESCRIPTION = 225;

  /**
   * The title of the ticket.
   */
  @NotEmpty(message = ValidationConstants.EMPTY_FIELD)
  @Size(min = MINIMUM_LENGTH, max = MAXIMUM_LENGTH_OF_TITLE,
  message = "Title too long (max 50 characters)")
  private String title;

  /**
   * The type of the ticket.
   */
  @NotNull(message =  ValidationConstants.EMPTY_FIELD)
  private TicketType ticketType;

  /**
   * The department associated with the ticket.
   */
  @JsonProperty("department")
  @NotNull(message = ValidationConstants.EMPTY_FIELD)
  private DepartmentInDto department;

  /**
   * The description of the ticket.
   */
  @NotEmpty(message = ValidationConstants.EMPTY_FIELD)
  @Size(min = MINIMUM_LENGTH, max = MAXIMUMN_LENGTH_OF_DESCRIPTION,
  message = "Description too long (max 225 characters)")
  private String description;

  /**
   * Status of ticket.
   */
  private Status status = Status.OPEN;

  /**
   * @return the status
   */
  public Status getStatus() {
    return status;
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
    return department;
  }

  /**
   * @param departmentField the department to set
   */
  public void setDepartment(final DepartmentInDto departmentField) {
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
   * hashCode of this object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(department, description,
        ticketType, title);
  }

  /**
   * equals method to compare this ticketInDto with object.
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
    TicketInDto other = (TicketInDto) obj;
    return Objects.equals(department, other.department)
        && Objects.equals(description, other.description)
        && ticketType == other.ticketType
        && Objects.equals(title, other.title);
  }
}
