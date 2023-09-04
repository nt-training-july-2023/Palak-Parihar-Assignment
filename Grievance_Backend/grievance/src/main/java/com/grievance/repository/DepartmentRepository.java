package com.grievance.repository;

import com.grievance.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The DepartmentRepository interface provides data
 * access methods for managing department entities in the database.
 */
public interface DepartmentRepository extends
    JpaRepository<Department, Integer> {
  /**
   * Find a department by its name.
   *
   * @param departmentName The name of the department to search for.
   * @return The Department entity associated
   *     with the given name, or null if not found.
   */
  Department findByDepartmentName(String departmentName);
}
