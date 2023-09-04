package com.grievance.repository;

import com.grievance.entity.Department;
import com.grievance.entity.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The EmployeeRepository interface provides data access
 * methods for managing Employee entities in the database.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
  /**
   * Find an employee by their email address.
   *
   * @param email The email address to search for.
   * @return The Employee entity associated with
   *     the given email, or null if not found.
   */
  Employee findByEmail(String email);

  /**
   * Find a list of employees belonging to a specific department.
   *
   * @param department The department for which to retrieve employees.
   * @return A list of Employee entities belonging
   *     to the specified department.
   */
  List<Employee> findByDepartment(Department department);
}
