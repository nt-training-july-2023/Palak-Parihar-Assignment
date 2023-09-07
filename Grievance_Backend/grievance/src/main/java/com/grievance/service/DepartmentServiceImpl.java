package com.grievance.service;

import com.grievance.dto.DepartmentInDto;
import com.grievance.dto.DepartmentOutDto;
import com.grievance.entity.Department;
import com.grievance.exception.DepartmentAlreadyExists;
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
   * @param departmentInDto of Department DTO.
   * @return An Optional containing a department DTO.
   */
  @Override
  public Optional<DepartmentOutDto>
      saveDepartment(final DepartmentInDto departmentInDto) {
    Department department2 = convertToEntity(departmentInDto);
    Department savedDepartment = departmentRepository.findByDepartmentName(
    departmentInDto.getDepartmentName().toUpperCase());

    if (savedDepartment != null) {
       throw new DepartmentAlreadyExists(departmentInDto.getDepartmentName());
    }
    department2.setDepartmentName(
        department2.getDepartmentName().toUpperCase());
    Department saved = departmentRepository.save(department2);
    return Optional.ofNullable(convertToDto(saved));
  }

  /**
   * Retrieves a list of all departments from the database and
   * converts them into a list of DepartmentDto objects.
   *
   * @return An optional containing a list of DepartmentDto
   *     objects representing all departments.
   */
  @Override
  public Optional<List<DepartmentOutDto>> listAllDepartment() {
    List<DepartmentOutDto> list = new ArrayList<DepartmentOutDto>();
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
   * @param departmentInDto The Department entity to be converted.
   * @return An Department representing the department's data.
   */
  public Department convertToEntity(final DepartmentInDto departmentInDto) {
    Department department = modelMapper.map(departmentInDto,
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
  public DepartmentOutDto convertToDto(final Department department) {
    DepartmentOutDto departmentOutDto = modelMapper.map(department,
        DepartmentOutDto.class);
    return departmentOutDto;
  }
}
