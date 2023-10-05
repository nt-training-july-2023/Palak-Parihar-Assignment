/**
 * 
 */
package com.crud.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.crud.entity.Department;
import com.crud.entity.Employee;

/**
 * 
 */
public interface EmployeeService {

	public Optional<Employee> saveEmployee(Employee employee);

	public Optional<List<Employee>> listAllEmployees();

	public Optional<Employee> findEmployeeById(Integer employeeId);

	public Optional<Employee> findByEmail(String email);

	public Optional<List<Employee>> listAllEmployeesByDateOfJoining(Date date);

	public Optional<List<Employee>> listAllEmployeesByDepartment(Department department);

	public Optional<List<Employee>> listAllEmployeesBySalary(Long salary);

	public Optional<Employee> updateEmployeeDetails(Employee employee);

	public Optional<String> deleteEmployee(Employee employee);
}
