package com.grievance.repository;

import org.springframework.data.repository.CrudRepository;

import com.grievance.dto.EmployeeDto;
import com.grievance.entity.Employee;



/**
 * Crud repository for Employee.
 */
public interface UserRepository extends CrudRepository<Employee, String> {
  /**
   * findByEmail method.
   *
   * @param email of type String.
   * @return Employee.
   */
}
