package com.grievance.dto;

import com.grievance.entity.Status;
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
}
