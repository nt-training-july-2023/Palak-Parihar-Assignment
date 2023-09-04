package com.grievance.service;

import com.grievance.dto.DepartmentDto;
import java.util.List;
import java.util.Optional;

/**
 * The DepartmentService is and interface
 * that provides functionality for managing department data in the application.
 */
public interface DepartmentService {
  /**
   * Saves a department in the database and converts the
   * returned department value to a Department DTO.
   *
   * @param departmentDto of Department DTO.
   * @return An Optional containing a department DTO.
   */
  Optional<DepartmentDto> saveDepartment(DepartmentDto departmentDto);

  /**
   * Retrieves a list of all departments from the database and
   * converts them into a list of DepartmentDto objects.
   *
   * @return An optional containing a list of DepartmentDto
   *     objects representing all departments.
   */
  Optional<List<DepartmentDto>> listAllDepartment();
}
