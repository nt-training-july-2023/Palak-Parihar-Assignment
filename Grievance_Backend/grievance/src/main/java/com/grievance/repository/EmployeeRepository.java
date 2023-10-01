package com.grievance.repository;

import com.grievance.entity.Department;
import com.grievance.entity.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.grievance.entity.UserType;


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

  /**
   * find Employee by email and password and userType.
   * @param email
   * @param password
   * @param userType
   * @return existed Employee by email and password and userType.
   */
  Employee findByEmailAndPasswordAndUserType(
          String email,
          String password,
          UserType userType);

  /**
   * find Employee by email and password.
   * @param email
   * @param password
   * @return existed Employee by email and password.
   */
  Employee findByEmailAndPassword(String email, String password);

  /**
   * @param email
   * @param userType
   * @return boolean if user exist with given mail and userType
   */
  Boolean existsByEmailAndUserType(String email, UserType userType);

}

