package com.grievance.entity;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
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
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * Entity class representing a ticket.
 */
@Entity
public class Ticket {

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
    @ManyToOne
    @JoinColumn(name = "departmentId")
    @JsonBackReference
    private Department department;


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
    private Status status;

    /**
     * The date when the ticket was opened.
     */
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOpened;

    /**
     * The date when the ticket was last updated.
     */
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated;

    /**
     * The list of comments associated with the ticket.
     */
    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "ticket",
            cascade = CascadeType.ALL)
    private List<Comment> comments;

    /**
     * The employee who created the ticket.
     */
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "employeeId")
    private Employee employee;

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
    public Department getDepartment() {
        return department;
    }

    /**
     * @param departmentField the department to set
     */
    public void setDepartment(final Department departmentField) {
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
     * @return the dateOpened
     */
    public Date getDateOpened() {
        return dateOpened;
    }

    /**
     * @param dateOpenedField the dateOpened to set
     */
    public void setDateOpened(final Date dateOpenedField) {
        this.dateOpened = dateOpenedField;
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
    public List<Comment> getComments() {
        return comments;
    }

    /**
     * @param commentsField the comments to set
     */
    public void setComments(final List<Comment> commentsField) {
        this.comments = commentsField;
    }

    /**
     * @return the employee
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * @param employeeField the employee to set
     */
    public void setEmployee(final Employee employeeField) {
        this.employee = employeeField;
    }

    /**
     * hashCOde of this object.
     */
    @Override
    public int hashCode() {
      return Objects.hash(comments, department, description, employee,
          status, ticketId, ticketType, title);
    }

    /**
     * method to compare two ticket objects.
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
      Ticket other = (Ticket) obj;
      return Objects.equals(comments, other.comments)
          && Objects.equals(department, other.department)
          && Objects.equals(description, other.description)
          && Objects.equals(employee, other.employee)
          && status == other.status && Objects.equals(ticketId, other.ticketId)
          && ticketType == other.ticketType
          && Objects.equals(title, other.title);
    }

    /**
     * parameterized constructor.
     * @param titleField
     * @param ticketTypeField
     * @param departmentField
     * @param descriptionField
     * @param statusField
     * @param lastUpdatedField
     * @param employeeField
     */
    public Ticket(
        @NotEmpty
        @Size(min = minimumLength,
        max = maximumLengthOfTitle,
        message = "Title too long (max 50 characters)")
        final String titleField,
        final TicketType ticketTypeField,
        final Department departmentField,
        @NotEmpty @Size(min = minimumLength,
        max = maximumLengthOfDescription,
        message = "Description too long (max 500 characters)")
        final String descriptionField,
        final Status statusField,
        final Date lastUpdatedField,
        final Employee employeeField) {
      super();
      this.title = titleField;
      this.ticketType = ticketTypeField;
      this.department = departmentField;
      this.description = descriptionField;
      this.status = statusField;
      this.lastUpdated = lastUpdatedField;
      this.employee = employeeField;
    }

    /**
     * default constructor.
     */
    public Ticket() {
      super();
    }
}
