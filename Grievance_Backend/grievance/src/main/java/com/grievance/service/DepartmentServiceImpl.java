package com.grievance.service;

import com.grievance.constants.ErrorConstants;
import com.grievance.dto.DepartmentInDto;
import com.grievance.dto.DepartmentOutDto;
import com.grievance.entity.Department;
import com.grievance.exception.RecordAlreadyExistException;
import com.grievance.exception.ResourceNotFoundException;
import com.grievance.exception.CustomException;
import com.grievance.repository.DepartmentRepository;
import com.grievance.repository.EmployeeRepository;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * The DepartmentServiceImpl class is an implementation of the DepartmentService
 * interface that provides functionality for managing department data in the
 * application.
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

  /**
   * Intance to create loggers.
   */
  private static final Logger LOGGER = LoggerFactory
      .getLogger(DepartmentServiceImpl.class);

  /**
   * pageSize for list of departments.
   */
  private final Integer pageSize = 10;

  /**
   * The DepartmentRepository instance provides data access methods for
   * interacting with the database to perform operations related to departments.
   */
  @Autowired
  private DepartmentRepository departmentRepository;

  /**
   * The EmployeeRepository instance provides data access methods for
   * interacting with the database to perform operations related to employees.
   */
  @Autowired
  private EmployeeRepository employeeRepository;

  /**
   * The ModelMapper instance used for mapping between
   * different data structures,
   * such as converting between Entity objects and DTOs (Data Transfer Objects).
   */
  @Autowired
  private ModelMapper modelMapper;

  /**
   * Saves a department in the database and converts the
   * returned department value
   * to a Department DTO.
   *
   * @param departmentInDto of Department DTO.
   * @return An Optional containing a department DTO.
   */
  @Override
  public Optional<DepartmentOutDto> saveDepartment(
      final DepartmentInDto departmentInDto) {
    LOGGER.info("Saving department: {}",
        departmentInDto.getDepartmentName());
    Department department2 = convertToEntity(departmentInDto);
    Department savedDepartment = departmentRepository
        .findByDepartmentName(
            departmentInDto.getDepartmentName().toUpperCase());

    if (!Objects.isNull(savedDepartment)) {
      LOGGER.error("Department with name {} already exists.",
          departmentInDto.getDepartmentName());
      throw new RecordAlreadyExistException(
          ErrorConstants.DEPARTMENT_ALREADY_EXIST);
    }

    department2
        .setDepartmentName(department2.getDepartmentName().toUpperCase());
    Department saved = departmentRepository.save(department2);
    return Optional.ofNullable(convertToDto(saved));
  }

  /**
   * Retrieves a list of all departments from the database
   * and converts them into
   * a list of DepartmentDto objects.
   *
   * @param page
   * @return An optional containing a list of DepartmentDto objects representing
   *         all departments.
   */
  @Override
  public Optional<List<DepartmentOutDto>> listAllDepartment(
      final Integer page) {
    LOGGER.info("Listing all departments");

    List<DepartmentOutDto> list = new ArrayList<>();
    if (Objects.isNull(page)) {
      departmentRepository.findAll()
          .forEach(e -> {
            list.add(convertToDto(e));
          });
      return Optional.ofNullable(list);
    }

    departmentRepository
        .findAll(PageRequest.of(page, pageSize))
        .forEach(
            e -> {
              list.add(convertToDto(e));
            });
    return Optional.of(list);
  }

  /**
   * method to delete department by Id.
   *
   * @param departmentId
   * @param email
   */
  public void deleteDepartment(final Integer departmentId, final String email) {
    Optional<Department> dept = departmentRepository
        .findById(departmentId);
    if (!dept.isPresent()) {
      LOGGER.error("Department with ID {} not found.", departmentId);
      throw new ResourceNotFoundException(
          ErrorConstants.DEPARTMENT_NOT_FOUND);
    }
    Department userDepartment =
        employeeRepository.findByEmail(email).getDepartment();
    if (userDepartment.equals(dept.get())) {
      LOGGER.info("User can not delete their own department");
      throw new CustomException(ErrorConstants.DEPARTMENT_SELF_DELETE);
    }
    departmentRepository.deleteById(departmentId);
    LOGGER.info("Department with ID {} deleted successfully.",
        departmentId);
  }

  /**
   * Converts an DepartmentDto sto object into an Department entity.
   *
   * @param departmentInDto The Department entity to be converted.
   * @return An Department representing the department's data.
   */
  public Department convertToEntity(
      final DepartmentInDto departmentInDto) {
    Department department = modelMapper.map(departmentInDto,
        Department.class);
    return department;
  }

  /**
   * Converts an Department entity object into an DepartmentDto data transfer
   * object (DTO).
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
