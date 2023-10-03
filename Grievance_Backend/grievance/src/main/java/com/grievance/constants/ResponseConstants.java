package com.grievance.constants;

/**
 * This class contains response message constants for various scenarios.
 */
public final class ResponseConstants {

  /**
   * Private constructor to prevent instantiation.
   */
  private ResponseConstants() {
    throw new AssertionError("This class should not be instantiated.");
  }

  /**
   * Response message for successful employee login.
   */
  public static final String EMPLOYEE_LOGIN = "Employee login was successfull.";

  /**
   * Response message for successful retrieval of employees.
   */
  public static final String EMPLOYEE_RETRIEVED =
      "Employees retrieved successfully.";

  /**
   * Response message for successful creation of an employee.
   */
  public static final String EMPLOYEE_CREATED =
      "Employee created successfully.";

  /**
   * Response message for successful password change.
   */
  public static final String PASSWORD_CHANGED =
      "Password changed successfully.";

  /**
   * Response message for successful deletion of an employee.
   */
  public static final String EMPLOYEE_DELETED =
      "Employee deleted successfully.";

  /**
   * Response message for successful deletion of a department.
   */
  public static final String DEPARTMENT_DELETED =
      "Department deleted successfully.";

  /**
   * Response message for successful creation of a ticket.
   */
  public static final String TICKET_CREATED = "Ticket created successfully.";

  /**
   * Response message for successful creation of a department.
   */
  public static final String DEPARTMENT_CREATED =
      "Department created successfully.";

  /**
   * Response message for an existing department.
   */
  public static final String TICKET_RETRIEVED_BY_ID =
      "Department already exists.";

  /**
   * Response message for successful retrieval of tickets.
   */
  public static final String TICKET_RETRIEVED =
      "Tickets retrieved successfully.";

  /**
   * Response message for successful ticket update.
   */
  public static final String TICKET_UPDATE = "Ticket updated successfully.";
}
