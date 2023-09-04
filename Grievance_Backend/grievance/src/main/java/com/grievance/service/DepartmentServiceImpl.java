package com.grievance.service;

import com.grievance.dto.DepartmentDto;
import com.grievance.entity.Department;
import com.grievance.repository.DepartmentRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The DepartmentServiceImpl class is an
 * implementation of the DepartmentService interface
 * that provides functionality for managing department data in the application.
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
  /**
   * The DepartmentRepository instance provides data access methods
   * for interacting with the database to perform operations
   * related to departments, such as retrieval and modification.
   */
  @Autowired
  private DepartmentRepository departmentRepository;

  /**
   * The ModelMapper instance used for mapping between
   * different data structures,
   * such as converting between Entity objects
   *     and DTOs (Data Transfer Objects).
   */
  @Autowired
  private ModelMapper modelMapper;

  /**
   * Saves a department in the database and converts the
   * returned department value to a Department DTO.
   *
   * @param departmentDto of Department DTO.
   * @return An Optional containing a department DTO.
   */
  @Override
  public Optional<DepartmentDto>
      saveDepartment(final DepartmentDto departmentDto) {
    Department department2 = convertToEntity(departmentDto);
    Department savedDepartment = departmentRepository.save(department2);
    return Optional.ofNullable(convertToDto(savedDepartment));
  }

  /**
   * Retrieves a list of all departments from the database and
   * converts them into a list of DepartmentDto objects.
   *
   * @return An optional containing a list of DepartmentDto
   *     objects representing all departments.
   */
  @Override
  public Optional<List<DepartmentDto>> listAllDepartment() {
    List<DepartmentDto> list = new ArrayList<DepartmentDto>();
    departmentRepository
        .findAll()
        .forEach(
          e -> {
            list.add(convertToDto(e));
          });
    return Optional.of(list);
  }

  /**
   * Converts an DepartmentDto sto object into an
   * Department entity.
   *
   * @param departmentDto The Department entity to be converted.
   * @return An Department representing the department's data.
   */
  public Department convertToEntity(final DepartmentDto departmentDto) {
    Department department = modelMapper.map(departmentDto,
        Department.class);
    return department;
  }

  /**
   * Converts an Department entity object into an
   * DepartmentDto data transfer object (DTO).
   *
   * @param department The department entity to be converted.
   * @return An DepartmentDto representing the department's data.
   */
  public DepartmentDto convertToDto(final Department department) {
    DepartmentDto departmentDto = modelMapper.map(department,
        DepartmentDto.class);
    return departmentDto;
  }
}
