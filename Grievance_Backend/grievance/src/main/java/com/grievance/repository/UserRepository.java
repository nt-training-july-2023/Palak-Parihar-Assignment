package com.grievance.repository;

import com.grievance.entity.Employee;
import org.springframework.data.repository.CrudRepository;

/**
 * Crud repository for Employee.
 */
public interface UserRepository extends CrudRepository<Employee, Integer> {
  /**
   * findByEmail method.
   *
   * @param name of type String.
   * @return Employee.
   */
  Employee findByEmail(String name);
}
