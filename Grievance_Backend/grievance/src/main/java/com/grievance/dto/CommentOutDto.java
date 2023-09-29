package com.grievance.dto;

import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CommentOutDto {

  /**
   * description of comment.
   */
  private String description;

  /**
   * comment belong to this userName.
   */
  private String userName;

  /**
   * comment created on date.
   */
  @JsonFormat(pattern = "dd-MM-yyyy hh:mm aa", timezone = "Asia/Kolkata")
  private Date createdOn;

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
   * @return the userName
   */
  public String getUserName() {
    return userName;
  }

  /**
   * @param userNameField the userName to set
   */
  public void setUserName(final String userNameField) {
    this.userName = userNameField;
  }

  /**
   * @return the createdOn
   */
  public Date getCreatedOn() {
    return createdOn;
  }

  /**
   * @param createdOnField the createdOn to set
   */
  public void setCreatedOn(final Date createdOnField) {
    this.createdOn = createdOnField;
  }

  /**
   * hashcode of this object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(createdOn, description, userName);
  }

  /**
   * equals method to compare this commentoutdto with objects.
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
    CommentOutDto other = (CommentOutDto) obj;
    return Objects.equals(createdOn, other.createdOn)
        && Objects.equals(description, other.description)
        && Objects.equals(userName, other.userName);
  }


  /**
   * parametrised constructor.
   * @param descriptionField
   * @param userNameField
   * @param createdOnField
   */
  public CommentOutDto(final String descriptionField,
      final String userNameField,
      final Date createdOnField) {
    super();
    this.description = descriptionField;
    this.userName = userNameField;
    this.createdOn = createdOnField;
  }

  /**
   * default constructor.
   */
  public CommentOutDto() {
    super();
  }
}
