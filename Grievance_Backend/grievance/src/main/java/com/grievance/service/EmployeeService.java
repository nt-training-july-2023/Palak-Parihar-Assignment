/**
 *
 */
package com.grievance.service;

import java.util.List;
import java.util.Optional;
import com.grievance.dto.EmployeeDto;
import com.grievance.entity.Employee;

/**
 *
 */
public interface EmployeeService {
//  Employee findEmployeeByEmail(EmployeeDto employee);
  
  Optional<EmployeeDto> saveEmployee(EmployeeDto employeeDto);
  
//  Optional<EmployeeDto> loginEmployee(String email, String password);
//  
  Optional<List<EmployeeDto>> listAllEmployees();
  
//  Employee save(Employee employee);
//  
//  List<Employee> listAllEmployees();
  
//  EmployeeDto saveEmployee(EmployeeDto employeeDto);
  
}
