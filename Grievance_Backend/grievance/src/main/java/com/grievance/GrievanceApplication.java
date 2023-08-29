package com.grievance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point of Grievance Management Application.
 */
@SpringBootApplication
public class GrievanceApplication {

  /**
   * Default Constructor.
   */
  protected GrievanceApplication() {
  }

  /**
   * main method of grievance application.
   *
   * @param args type of String.
   */
  public static void main(final String[] args) {
    SpringApplication.run(GrievanceApplication.class, args);
  }
}
