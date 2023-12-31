package com.grievance.response;

import java.util.Map;

public class ErrorResponse {
  /**
   * message for response.
   */
  private String message;
  /**
   * statuscode of error response.
   */
  private int statusCode;

  /**
   * validations.
   */
  private Map<String, String> validationErrors;

  /**
   * @return the message
   */
  public String getMessage() {
    return message;
  }

  /**
   * @param messageField the messageField to set
   */
  public void setMessage(final String messageField) {
    this.message = messageField;
  }

  /**
   * @return the statusCode
   */
  public int getStatusCode() {
    return statusCode;
  }

  /**
   * @param statusCodeField the statusCode to set
   */
  public void setStatusCode(final int statusCodeField) {
    this.statusCode = statusCodeField;
  }

  /**
   * @return the validationErrors
   */
  public Map<String, String> getValidationErrors() {
    return validationErrors;
  }

  /**
   * @param validationErrorsField the validationErrors to set
   */
  public void setValidationErrors(final
      Map<String, String> validationErrorsField) {
    this.validationErrors = validationErrorsField;
  }

  /**
   * parameterized constructor.
   * @param messageField
   * @param statusCodeField
   * @param validationErrorsField
   */
  public ErrorResponse(final String messageField,
      final int statusCodeField,
      final Map<String, String> validationErrorsField) {
    super();
    this.message = messageField;
    this.statusCode = statusCodeField;
    this.validationErrors = validationErrorsField;
  }

}
