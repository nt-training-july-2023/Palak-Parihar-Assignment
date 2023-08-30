/**
 *
 */
package com.grievance.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import org.hibernate.annotations.CreationTimestamp;

/**
 *entity class of comment.
 */
@Entity
public class Comment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer commentId;

  @NotEmpty
  private String description;

  @NotEmpty
  private String userName;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdOn;
  
  @ManyToOne
  private Ticket ticket;
  

/**
 * @return the commentId
 */
public Integer getCommentId() {
return commentId;}

/**
 * @param commentId the commentId to set
 */
public void setCommentId(Integer commentId) {
this.commentId = commentId;}

/**
 * @return the description
 */
public String getDescription() {
return description;}

/**
 * @param description the description to set
 */
public void setDescription(String description) {
this.description = description;}

/**
 * @return the userName
 */
public String getUserName() {
return userName;}

/**
 * @param userName the userName to set
 */
public void setUserName(String userName) {
this.userName = userName;}

/**
 * @return the createdOn
 */
public Date getCreatedOn() {
return createdOn;}

/**
 * @param createdOn the createdOn to set
 */
public void setCreatedOn(Date createdOn) {
this.createdOn = createdOn;}

/**
 * @return the ticket
 */
public Ticket getTicket() {
return ticket;}

/**
 * @param ticket the ticket to set
 */
public void setTicket(Ticket ticket) {
this.ticket = ticket;}


  
  
}
