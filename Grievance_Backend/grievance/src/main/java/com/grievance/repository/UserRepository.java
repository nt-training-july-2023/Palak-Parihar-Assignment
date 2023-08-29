package com.grievance.repository;

import org.springframework.data.repository.CrudRepository;

import com.grievance.dto.EmployeeDto;



/**
 * Crud repository for Employee.
 */
public interface UserRepository extends CrudRepository<EmployeeDto, String> {
  /**
   * findByEmail method.
   *
   * @param email of type String.
   * @return Employee.
   */
  EmployeeDto findByEmail(String email);
}
