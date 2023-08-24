package com.grievance.repository;

import com.grievance.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<Employee, Integer> {
  Employee findByEmail(String name);
}
