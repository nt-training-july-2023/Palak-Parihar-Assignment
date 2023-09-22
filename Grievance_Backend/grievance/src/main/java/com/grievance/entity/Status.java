package com.grievance.entity;

/**
 * Enumeration representing the status of a ticket.
 * Possible values are OPEN, INPROGRESS, and CLOSED.
 */
public enum Status {
  /**
   * open type of status.
   */
  OPEN,
  /**
   * In Progress type of status.
   */
  BEING_ADDRESSED,
  /**
   * close type of status.
   */
  RESOLVED
}
