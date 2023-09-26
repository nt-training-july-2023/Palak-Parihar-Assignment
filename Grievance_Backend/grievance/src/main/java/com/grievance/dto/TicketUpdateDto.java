package com.grievance.dto;

import com.grievance.entity.Status;

import java.util.Objects;

import javax.validation.constraints.NotEmpty;

public class TicketUpdateDto {
  /**
   * The status of the ticket.
   */
  private Status status;

  /**
   * description of comment.
   */
  @NotEmpty
  private String description;

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
   * hashCode of this ticketUpdateDto.
   */
  @Override
  public int hashCode() {
    return Objects.hash(description, status);
  }

  /**
   * method compares objects and return boolean.
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
    TicketUpdateDto other = (TicketUpdateDto) obj;
    return Objects.equals(description, other.description)
        && status == other.status;
  }
}
