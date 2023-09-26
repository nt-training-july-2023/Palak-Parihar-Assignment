package com.grievance.service;

import com.grievance.dto.DepartmentInDto;
import com.grievance.dto.DepartmentOutDto;

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
   * @param departmentInDto of Department DTO.
   * @return An Optional containing a department DTO.
   */
  Optional<DepartmentOutDto> saveDepartment(DepartmentInDto departmentInDto);

  /**
   * Retrieves a list of all departments from the database and
   * converts them into a list of DepartmentDto objects.
   * @param page
   * @return An optional containing a list of DepartmentDto
   *     objects representing all departments.
   */
  Optional<List<DepartmentOutDto>> listAllDepartment(Integer page);

  /**
   * method to delete department by Id.
   * @param departmentId
   */
  void deleteDepartment(Integer departmentId);

}
