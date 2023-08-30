/**
 *
 */
package com.grievance.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

/**
 *
 */
@Entity
public class Ticket {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer ticketId;

  @NotEmpty
  @Size(min = 1, max = 50, message = "Title too long(max 50 characters)")
  private String title;

  @NotEmpty
  private TicketType ticketType;

  @ManyToOne
  @JoinColumn(name = "departmentId")
  private Department department;

  @NotEmpty
  @Size(min = 1, max = 500, message = "Description too long (max 500 characters)")
  private String description;

  @NotEmpty
  private Status status;

  @CreatedDate
  @Temporal(TemporalType.TIMESTAMP)
  private Date dateOpened;

  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date lastUpdated;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "ticket")
  private List<Comment> comments;

  @ManyToOne
  @JoinColumn(name = "employeeId")
  private Employee employee;

  /**
   * @return the title
   */
  public String getTitle() {
    return title;
  }

  /**
   * @param title the title to set
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * @return the ticketType
   */
  public TicketType getTicketType() {
    return ticketType;
  }

  /**
   * @param ticketType the ticketType to set
   */
  public void setTicketType(TicketType ticketType) {
    this.ticketType = ticketType;
  }

  /**
   * @return the department
   */
  public Department getDepartment() {
    return department;
  }

  /**
   * @param department the department to set
   */
  public void setDepartment(Department department) {
    this.department = department;
  }

  /**
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * @param description the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * @return the status
   */
  public Status getStatus() {
    return status;
  }

  /**
   * @param status the status to set
   */
  public void setStatus(Status status) {
    this.status = status;
  }

  /**
   * @return the dateOpened
   */
  public Date getDateOpened() {
    return dateOpened;
  }

  /**
   * @param dateOpened the dateOpened to set
   */
  public void setDateOpened(Date dateOpened) {
    this.dateOpened = dateOpened;
  }

  /**
   * @return the lastUpdated
   */
  public Date getLastUpdated() {
    return lastUpdated;
  }

  /**
   * @param lastUpdated the lastUpdated to set
   */
  public void setLastUpdated(Date lastUpdated) {
    this.lastUpdated = lastUpdated;
  }

  /**
   * @return the comments
   */
  public List<Comment> getComments() {
    return comments;
  }

  /**
   * @param comments the comments to set
   */
  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }

  /**
   * @return the employee
   */
  public Employee getEmployee() {
    return employee;
  }

  /**
   * @param employee the employee to set
   */
  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  /**
   * @return the ticketId
   */
  public Integer getTicketId() {
    return ticketId;
  }
  
  
}
