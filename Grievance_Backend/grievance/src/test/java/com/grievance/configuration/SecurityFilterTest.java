package com.grievance.configuration;

import static org.mockito.Mockito.*;


import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.grievance.Configuration.SecurityFilter;
import com.grievance.authentication.AuthenticateUser;
import com.grievance.constants.ErrorConstants;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class SecurityFilterTest {
  private SecurityFilter securityFilter;
  private HttpServletRequest request;
  private HttpServletResponse response;
  private FilterChain filterChain;

  private AuthenticateUser authenticateUser;

  @BeforeEach
  public void setup() {
    request = mock(HttpServletRequest.class);
    response = mock(HttpServletResponse.class);
    filterChain = mock(FilterChain.class);
    authenticateUser = mock(AuthenticateUser.class);
    securityFilter = new SecurityFilter(authenticateUser);
  }

  @Test
    public void testDoFilter_WithMethodOptions() throws Exception {
        when(request.getMethod()).thenReturn("OPTIONS");
        securityFilter.doFilter(request, response, filterChain);
        verify(response).setHeader("Access-Control-Allow-Origin", "*");
        verify(filterChain, never()).doFilter(request, response);
    }

  @Test
    public void testDoFilter_WithLoginUrl() throws Exception {
        when(request.getMethod()).thenReturn("POST");
        when(request.getRequestURI()).thenReturn("/employee/login");
        securityFilter.doFilter(request, response, filterChain);
        verify(filterChain).doFilter(request, response);
    }

  @Test
    public void testDoFilter_MissingEmailHeader() throws Exception {
    when(request.getMethod()).thenReturn("GET");
        when(request.getHeader("email")).thenReturn(null);
        when(request.getHeader("password")).thenReturn("Password");
        securityFilter.doFilter(request, response, filterChain);
        verify(response).sendError(HttpServletResponse.SC_UNAUTHORIZED,  ErrorConstants.INVALID_USER);
    }

  @Test
    public void testDoFilter_AdminAccess() throws Exception {
        when(request.getMethod()).thenReturn("POST");
        when(request.getHeader("email")).thenReturn("admin@nucleusteq.com");
        when(request.getHeader("password")).thenReturn("Password");
        when(request.getRequestURI()).thenReturn("/employee/save");
        when(authenticateUser.checkIfUserIsAdmin(Mockito.eq("admin@nucleusteq.com"),Mockito.eq("Password"))).thenReturn(true);
        securityFilter.doFilter(request, response, filterChain);
        verify(filterChain).doFilter(request, response);
    }

  @Test
    public void testDoFilter_MemberAccess() throws Exception {
        when(request.getMethod()).thenReturn("POST");
        when(request.getHeader("email")).thenReturn("member@nucleusteq.com");
        when(request.getHeader("password")).thenReturn("Password");
        when(request.getRequestURI()).thenReturn("/ticket/add");
        when(authenticateUser.checkIfUserExists(Mockito.anyString(), Mockito.anyString())).thenReturn(true);
        securityFilter.doFilter(request, response, filterChain);
        verify(filterChain).doFilter(request, response);
    }

  @Test
    public void testDoFilter_UnauthorizedRole() throws Exception {
        when(request.getMethod()).thenReturn("GET");
        when(request.getHeader("email")).thenReturn("member@nucleusteq.com");
        when(request.getHeader("password")).thenReturn("Password");
        
        when(authenticateUser.checkIfUserIsAdmin(Mockito.anyString(), Mockito.anyString())).thenReturn(false);
        
        when(request.getRequestURI()).thenReturn("/employee/save");
        securityFilter.doFilter(request, response, filterChain);
        verify(response).sendError(HttpServletResponse.SC_UNAUTHORIZED,  ErrorConstants.INVALID_USER);
    }

  @Test
  public void testDoFilter_InvalidUrl() throws Exception {
      when(request.getMethod()).thenReturn("GET");
      when(request.getHeader("email")).thenReturn("admin@nucleusteq.com");
      when(request.getHeader("password")).thenReturn("Password");
      when(request.getRequestURI()).thenReturn("/invalidurl");

      when(authenticateUser.checkIfUserIsAdmin(Mockito.anyString(), Mockito.anyString())).thenReturn(false);

      securityFilter.doFilter(request, response, filterChain);
      verify(response).sendError(HttpServletResponse.SC_UNAUTHORIZED, ErrorConstants.INVALID_USER);
  }
  
  @Test
  public void testDoFilter_invalid_firstTimeLogin() throws Exception {
      when(request.getMethod()).thenReturn("POST");
      when(request.getHeader("email")).thenReturn("admin@nucleusteq.com");
      when(request.getHeader("password")).thenReturn("Password");
      when(request.getRequestURI()).thenReturn("/tickets/list");

      when(authenticateUser.checkIfUserisFirstTimeLogin(Mockito.anyString(), Mockito.anyString())).thenReturn(true);

      securityFilter.doFilter(request, response, filterChain);
      verify(response).sendError(HttpServletResponse.SC_UNAUTHORIZED, ErrorConstants.UNAUTHORISED_USER_FIRST_LOGIN);
  }
  
  @Test
  public void testDoFilter_firstTimeLogin() throws Exception {
      when(request.getMethod()).thenReturn("POST");
      when(request.getHeader("email")).thenReturn("admin@nucleusteq.com");
      when(request.getHeader("password")).thenReturn("Password");
      when(request.getRequestURI()).thenReturn("/employee/changePassword");

      when(authenticateUser.checkIfUserisFirstTimeLogin(Mockito.anyString(), Mockito.anyString())).thenReturn(true);

      securityFilter.doFilter(request, response, filterChain);
      verify(filterChain).doFilter(request, response);
  }

}
