package com.grievance.constants;

public class ValidationConstants {

  protected ValidationConstants() {
    throw new AssertionError("This class should not be instantiated.");
  }

  /**
   * Email validation constant.
   */
  public static final String EMAIL_VALIDATION =
      "Email must follow example@nucleusteq.com format";

  /**
   * Password validation constant.
   */
  public static final String PASSWORD_VALIDATION =
      "Password must contain an uppercase a lowercase "
      + "and a special character and in between (6, 20) length";
  /**
   * Empty field validation.
   */
  public static final String EMPTY_FIELD = "Field must not be empty.";
}
