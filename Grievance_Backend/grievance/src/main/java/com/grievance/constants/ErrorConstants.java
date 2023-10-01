package com.grievance.constants;

/**
 * This class contains error message constants for various scenarios.
 */
public final class ErrorConstants {

  /**
   * Private constructor to prevent instantiation.
   */
  private ErrorConstants() {
    throw new AssertionError("This class should not be instantiated.");
  }

  /**
   * Error message for a user trying to access resources on first time login.
   */
  public static final String UNAUTHORISED_USER_FIRST_LOGIN =
      "You are unauthorised to access this"
      + " resource please change password to continue";

  /**
   * Error message for a user trying to access resources on first time login.
   */
  public static final String INVALID_USER = "Unauthorised User";

  /**
   * Error message for a generic resource not found error.
   */
  public static final String RESOURCE_NOT_FOUND = "Resource not found.";

  /**
   * Error message for when an employee with specific credentials doesn't exist.
   */
  public static final String EMPLOYEE_NOT_FOUND =
      "Employee with these credentials doesn't exist.";

  /**
   * Error message for when a department doesn't exist.
   */
  public static final String DEPARTMENT_NOT_FOUND = "Department doesn't exist.";

  /**
   * Error message for when a ticket with a specific ID doesn't exist.
   */
  public static final String TICKET_NOT_FOUND =
      "Ticket with this ID doesn't exist.";

  /**
   * Error message for when a comment is not found.
   */
  public static final String COMMENT_NOT_FOUND = "Comment not found.";

  /**
   * Error message when method arguments are not valid.
   */
  public static final String METHOD_ARGUMENT_NOT_VALID =
      "Method arguments aren't valid";

  /**
   * Error message for a generic record already exists error.
   */
  public static final String RECORD_ALREADY_EXIST = "Record already exists.";

  /**
   * Error message for a department already exists error.
   */
  public static final String DEPARTMENT_ALREADY_EXIST =
      "Department already exists.";

  /**
   * Error message for a ticket already exists error.
   */
  public static final String TICKET_ALREADY_EXIST = "Ticket already exists.";

  /**
   * Error message for a employee already exists error.
   */
  public static final String EMPLOYEE_ALREADY_EXIST =
      "Employee already exists.";
  /**
   * Error message for a user deleting themself error.
   */
  public static final String EMPLOYEE_SELF_DELETE =
      "User can not delete itself.";
  /**
   * Error message for a user delete their own department error.
   */
  public static final String DEPARTMENT_SELF_DELETE =
      "User can not delete their own department.";
}
