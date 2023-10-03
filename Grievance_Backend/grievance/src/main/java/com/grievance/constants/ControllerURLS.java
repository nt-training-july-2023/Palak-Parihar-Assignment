package com.grievance.constants;

/**
 * This class contains URL constants related to various controllers in the
 * application.
 */
public final class ControllerURLS {

  /**
   * private constructors.
   */
  private ControllerURLS() {
    throw new AssertionError("This class should not be instantiated.");
  }

  /**
   * The base URL for Department-related endpoints.
   */
  public static final String DEPARTMENT_BASE_URL = "/department";

  /**
   * The base URL for Employee-related endpoints.
   */
  public static final String EMPLOYEE_BASE_URL = "/employee";

  /**
   * URL for employee login.
   */
  public static final String EMPLOYEE_LOGIN = "/login";

  /**
   * URL for changing employee password.
   */
  public static final String EMPLOYEE_CHANGE_PASSWORD = "/changePassword";

  /**
   * The base URL for Ticket-related endpoints.
   */
  public static final String TICKET_BASE_URL = "/ticket";

  /**
   * URL for retrieving a list of all data (departments, employees, etc.).
   */
  public static final String GET_ALL_DATA = "/list";

  /**
   * URL for saving data (departments, employees, etc.).
   */
  public static final String SAVE_DATA = "/save";

  /**
   * URL for deleting data by its ID (departments, employees, etc.).
   */
  public static final String DELETE_DATA_BY_ID = "/delete";

  /**
   * URL for updating data by its ID (departments, employees, etc.).
   */
  public static final String UPDATE_DATA_BY_ID = "/update";

  /**
   * URL for retrieving data by its ID (departments, employees, etc.).
   */
  public static final String GET_DATA_BY_ID = "/get";
}
