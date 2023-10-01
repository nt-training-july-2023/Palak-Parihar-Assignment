package com.grievance.Configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.grievance.authentication.AuthenticatingUser;


/**
 * A custom Servlet Filter implementation to
 * perform pre and post-processing of HTTP requests and responses.
 * This filter can be used to add custom
 * logic before and after a request reaches the controller.
 */
@CrossOrigin("*")
public class SecurityFilter implements Filter {
/**
 * logger.
 */
  private static final Logger LOGGER =
          LoggerFactory.getLogger(SecurityFilter.class);
  /**
   * loginUrl to compare with request url.
   */
  private String loginUrl = "/employee/login";
  /**
   *
   * @param authenticatingUserField
   */
  public SecurityFilter(final AuthenticatingUser authenticatingUserField) {
      this.authenticatingUser = authenticatingUserField;
  }

  /**
   *
   */
  @Autowired
  private AuthenticatingUser authenticatingUser;

  /**
   *
   */
  private static List<String> adminUrls = new ArrayList<String>();

  static {
      adminUrls.add("/employee/listAllEmployees");
      adminUrls.add("/department/save");
      adminUrls.add("/department/deleteDepartment");
      adminUrls.add("/employee/save");
   }

  /**
   * Initializes the custom filter.
   *
   * @param filterConfig The FilterConfig
   * object containing filter configuration details.
   * @throws ServletException If an error occurs during filter initialization.
   */

  @Override
  public void init(final FilterConfig filterConfig) throws ServletException {
      LOGGER.info("Initiating Custom Filter");
  }

  /**
   * Performs filtering of HTTP requests and responses.
   *
   * @param request  The ServletRequest object representing
   * the incoming HTTP request.
   * @param response The ServletResponse object representing
   * the HTTP response to be sent.
   * @param chain    The FilterChain for executing the next filter in the chain.
   * @throws IOException      If an I/O error occurs
   * while processing the request or response.
   * @throws ServletException If an error occurs during filter processing.
   */
  @Override
  public void doFilter(
          final ServletRequest request,
          final ServletResponse response,
          final FilterChain chain)
          throws IOException, ServletException {
    LOGGER.info("Request received at: {}",
        new Date(System.currentTimeMillis()));


      HttpServletRequest httpServletRequest = (HttpServletRequest) request;
      HttpServletResponse httpServletResponse = (HttpServletResponse) response;

      String requestUrl = httpServletRequest.getRequestURI();
      String email = httpServletRequest.getHeader("email");
      String password = httpServletRequest.getHeader("password");

      if (httpServletRequest.getMethod().equals("OPTIONS")) {
             httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
             httpServletResponse.setHeader("Access-Control-Allow-Methods",
                        "GET, POST, PUT, DELETE");
          httpServletResponse.setHeader("Access-Control-Allow-Headers",
                     "Authorization, Content-Type, email, password");
          httpServletResponse.setContentType("application/json");
          httpServletResponse.setStatus(HttpServletResponse.SC_OK);
      } else {
          if (Objects.isNull(email) || Objects.isNull(password)) {
              if (loginUrl.equals(requestUrl)) {
                 chain.doFilter(request, response);
              } else {
                 ((HttpServletResponse) response).sendError(
                        HttpServletResponse.SC_UNAUTHORIZED, "Invalid User");
              }
          } else {
              if (adminUrls.contains(requestUrl)) {
                  if (authenticatingUser.checkIfUserIsAdmin(email, password)) {
                     chain.doFilter(request, response);
                 } else {
                     ((HttpServletResponse) response).sendError(
                             HttpServletResponse.SC_UNAUTHORIZED,
                             "Invalid User");
                 }
            } else {
                  if (authenticatingUser.checkIfUserExists(email, password)) {
                      chain.doFilter(request, response);
                  } else {
                       ((HttpServletResponse) response).sendError(
                          HttpServletResponse.SC_UNAUTHORIZED, "Invalid User");
                  }
              }
          }
      }
      LOGGER.info("Response sent at: {}",
          new Date(System.currentTimeMillis()));
  }

  /**
   * Destroys the filter and releases any resources held by it.
   * This method is called by the servlet
   * container when the filter is taken out of service.
   */
  @Override
  public void destroy() {
  }
}

