/**
 * Entity class representing a comment.
 */

package com.grievance.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Entity class representing a comment.
 */
@Entity
public class Comment {
  /**
   * Integer commentId of Comment.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer commentId;

  /**
   * String description of comment.
   */
  @NotEmpty
  private String description;

  /**
   * String userName that comment belongs to.
   */
  @NotEmpty
  private String userName;

  /**
   * Creation time of comment.
   */
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Kolkata")
  private Date createdOn;

  /**
   * ticket that belongs to this comment.
   */
  @ManyToOne
  @JoinColumn(name = "ticketId")
  @JsonIgnore
  private Ticket ticket;

  /**
   * Get the commentId.
   *
   * @return the commentId
   */
  public Integer getCommentId() {
    return commentId;
  }

  /**
   * Set the commentId.
   *
   * @param commentIdField the commentId to set
   */
  public void setCommentId(final Integer commentIdField) {
    this.commentId = commentIdField;
  }

  /**
   * Get the description.
   *
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Set the description.
   *
   * @param descriptionField the description to set
   */
  public void setDescription(final String descriptionField) {
    this.description = descriptionField;
  }

  /**
   * Get the userName.
   *
   * @return the userName
   */
  public String getUserName() {
    return userName;
  }

  /**
   * Set the userName.
   *
   * @param userNameField the userName to set
   */
  public void setUserName(final String userNameField) {
    this.userName = userNameField;
  }

  /**
   * Get the createdOn date.
   *
   * @return the createdOn date
   */
  public Date getCreatedOn() {
    return createdOn;
  }

  /**
   * Set the createdOn date.
   *
   * @param createdOnField the createdOn date to set
   */
  public void setCreatedOn(final Date createdOnField) {
    this.createdOn = createdOnField;
  }

  /**
   * Get the associated ticket.
   *
   * @return the ticket
   */
  public Ticket getTicket() {
    return ticket;
  }

  /**
   * Set the associated ticket.
   *
   * @param ticketField the ticket to set
   */
  public void setTicket(final Ticket ticketField) {
    this.ticket = ticketField;
  }

  /**
   * constructor.
   * @param desc
   */
  public Comment(final String desc) {
      this.description = desc;
  }
  /**
   *
   */
  public Comment() {
  }
}
