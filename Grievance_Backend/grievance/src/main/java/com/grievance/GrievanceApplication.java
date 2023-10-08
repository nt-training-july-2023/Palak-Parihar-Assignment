package com.grievance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.grievance.Configuration.SecurityFilter;
import com.grievance.authentication.AuthenticateUser;

/**
 * Entry point of Grievance Management Application.
 */
@SpringBootApplication
public class GrievanceApplication {

  /**
   *employeeRepository.
   */
  @Autowired
  private AuthenticateUser authenticateUser;


  /**
   * Default Constructor.
   */
  protected GrievanceApplication() { }

  /**
   * main method of grievance application.
   *
   * @param args type of String.
   */

  public static void main(final String[] args) {
    SpringApplication.run(GrievanceApplication.class, args);
  }

  /**
   * Creates and configures a
   * FilterRegistrationBean for a custom
   * UserFilter to intercept requests for all URL patterns.
   *
   * @return A FilterRegistrationBean<UserFilter>
   * instance configured for the UserFilter.
   */
  @Bean
  FilterRegistrationBean<SecurityFilter> securityFilter() {
      FilterRegistrationBean<SecurityFilter> registrationBean =
             new FilterRegistrationBean<>();

      registrationBean.setFilter(new SecurityFilter(authenticateUser));

      registrationBean.addUrlPatterns("/*");

      return registrationBean;

   }
}
