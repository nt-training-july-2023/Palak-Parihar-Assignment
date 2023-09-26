package com.grievance.configuration;

import static org.mockito.Mockito.*;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.grievance.Configuration.SecurityFilter;
import com.grievance.authentication.AuthenticatingUser;
import com.grievance.authentication.AuthenticatingUserImpl;
import com.grievance.entity.Employee;
import com.grievance.entity.UserType;
import com.grievance.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class SecurityFilterTest {
  private SecurityFilter securityFilter;
  private HttpServletRequest request;
  private HttpServletResponse response;
  private FilterChain filterChain;
  private AuthenticatingUserImpl authenticatingUser;
  private EmployeeRepository employeeRepository;

  @BeforeEach
  public void setup() {
    request = mock(HttpServletRequest.class);
    response = mock(HttpServletResponse.class);
    filterChain = mock(FilterChain.class);
    employeeRepository = mock(EmployeeRepository.class);
    securityFilter = new SecurityFilter(authenticatingUser);
    authenticatingUser = mock(AuthenticatingUserImpl.class);
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
        verify(response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid User");
    }

  @Test
    public void testDoFilter_AdminAccess() throws Exception {
        when(request.getMethod()).thenReturn("POST");
        when(request.getHeader("email")).thenReturn("admin@nucleusteq.com");
        when(request.getHeader("password")).thenReturn("Password");
        when(request.getRequestURI()).thenReturn("/employee/saveEmployee");
//        when(employeeRepository.findByEmailAndPasswordAndUserType("admin@nucleusteq.com", "Password", UserType.ADMIN)).thenReturn(new Employee());
        when(authenticatingUser.checkIfUserIsAdmin(Mockito.eq("admin@nucleusteq.com"),Mockito.eq("Password"))).thenReturn(true);
        System.out.println(authenticatingUser.checkIfUserIsAdmin("", ""));
        securityFilter.doFilter(request, response, filterChain);
        verify(filterChain).doFilter(request, response);
    }

  @Test
    public void testDoFilter_MemberAccess() throws Exception {
        when(request.getHeader("email")).thenReturn("member@nucleusteq.com");
        when(request.getHeader("password")).thenReturn("Password");
        when(request.getRequestURI()).thenReturn("/api/ticket/create");
        securityFilter.doFilter(request, response, filterChain);
        verify(filterChain).doFilter(request, response);
    }

  @Test
    public void testDoFilter_UnauthorizedRole() throws Exception {
        when(request.getMethod()).thenReturn("GET");
        when(request.getHeader("email")).thenReturn("member@nucleusteq.com");
        when(request.getHeader("password")).thenReturn("Password");
        
        System.out.println(request+" , "+authenticatingUser.checkIfUserIsAdmin("", ""));
        when(authenticatingUser.checkIfUserIsAdmin(Mockito.anyString(), Mockito.anyString())).thenReturn(true);
        
        when(request.getRequestURI()).thenReturn("/employee/saveEmployee");
        securityFilter.doFilter(request, response, filterChain);
        verify(response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid User");
    }

  @Test
  public void testDoFilter_InvalidUrl() throws Exception {
      when(request.getMethod()).thenReturn("GET");
      when(request.getHeader("email")).thenReturn("admin@nucleusteq.com");
      when(request.getHeader("password")).thenReturn("Password");
      when(request.getRequestURI()).thenReturn("/api/invalidurl");

      // Mock the behavior of the employeeRepository
      Employee mockEmployee = new Employee(); // Create a mock Employee object
      when(employeeRepository.findByEmailAndPasswordAndUserType("admin@nucleusteq.com", "Password", UserType.ADMIN)).thenReturn(mockEmployee);

      when(authenticatingUser.checkIfUserIsAdmin(Mockito.anyString(), Mockito.anyString())).thenReturn(false);

      securityFilter.doFilter(request, response, filterChain);
      verify(response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized Role");
  }

}