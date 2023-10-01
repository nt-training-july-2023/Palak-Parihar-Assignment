package com.grievance.response;

/**
 * Response Class.
 * @param <T>
 */
public class Response<T> {
  /**
   * message for response.
   */
  private String message;
  /**
   * statusCode for response.
   */
  private int statusCode;
  /**
   * data for response.
   */
  private T data;

  /**
   * @return the message
   */
  public String getMessage() {
    return message;
  }

  /**
   * @param messageField the message to set
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
   * @return the data
   */
  public T getData() {
    return data;
  }

  /**
   * @param dataField the data to set
   */
  public void setData(final T dataField) {
    this.data = dataField;
  }

  /**
   * parameterized constructor.
   *
   * @param messageField
   * @param statusCodeField
   * @param dataField
   */
  public Response(
      final String messageField,
      final int statusCodeField,
      final T dataField) {
    super();
    this.message = messageField;
    this.statusCode = statusCodeField;
    this.data = dataField;
  }

}
