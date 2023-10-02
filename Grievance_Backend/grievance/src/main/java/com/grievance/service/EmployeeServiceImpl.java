package com.grievance.service;

import com.grievance.constants.ErrorConstants;
import com.grievance.dto.ChangePasswordInDto;
import com.grievance.dto.EmployeeInDto;
import com.grievance.dto.EmployeeLoginDto;
import com.grievance.dto.EmployeeOutDto;
import com.grievance.entity.Employee;
import com.grievance.exception.RecordAlreadyExistException;
import com.grievance.exception.ResourceNotFoundException;
import com.grievance.exception.CustomException;
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
import java.util.regex.Pattern;

/**
 * The EmployeeServiceImpl class is an implementation of the EmployeeService
 * interface that provides functionality for managing employee data in the
 * application.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
  /**
   * Instance for creating loggers.
   */
  private static final Logger LOGGER = LoggerFactory
      .getLogger(EmployeeServiceImpl.class);

  /**
   * The employeeRepository instance provide data access method for interacting
   * with database.
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
   * page size during pagibation.
   */
  private final Integer pageSize = 10;

  /**
   * Saves a new employee record.
   *
   * @param employeeInDto The input DTO containing employee information to be
   *                      saved.
   * @return An optional containing the saved employee details in the form of an
   *         EmployeeOutDto.
   */
  @Override
  public Optional<EmployeeOutDto> saveEmployee(
      final EmployeeInDto employeeInDto) {
    LOGGER.info("Saving employee: {}", employeeInDto.getEmail());
    Employee employee = employeeRepository
        .findByEmail(employeeInDto.getEmail());
    if (Objects.isNull(employee)) {
      String password = Base64DecodeService
          .decodeBase64ToString(employeeInDto.getPassword());
      Boolean valid = Pattern.matches("^(?=.*[0-9])(?=.*[!@#$%^&*])"
          + "[a-zA-Z0-9!@#$%^&*]{6,20}$", password);
      if (!valid) {
        LOGGER.info("Password doesn't match requirements");
        throw new CustomException("Password must have a "
            + "Uppercase a Lowercase and A Special Character");
      }
      employee = convertToEntity(employeeInDto);
      employee = employeeRepository.save(employee);
      EmployeeOutDto employeeOutDto = convertToDto(employee);
      LOGGER.info("Employee saved successfully: {}",
          employeeInDto.getEmail());
      return Optional.of(employeeOutDto);
    }
    LOGGER.error("Employee with email {} already exists.",
        employeeInDto.getEmail());
    throw new RecordAlreadyExistException(
        ErrorConstants.EMPLOYEE_ALREADY_EXIST);
  }

  /**
   * Retrieves a list of all employees.
   *
   * @param page
   * @return An optional containing a list of EmployeeOutDto
   * objects representing
   *         all employees.
   */
  @Override
  public Optional<List<EmployeeOutDto>> listAllEmployees(
      final Integer page) {
    LOGGER.info("Listing all employees");
    List<EmployeeOutDto> employeeOutDtoList = new ArrayList<>();
    if (!Objects.isNull(page)) {
      employeeRepository
          .findAll(PageRequest.of(page, pageSize))
          .forEach(
              e -> {
                employeeOutDtoList.add(convertToDto(e));
              });
      return Optional.of(employeeOutDtoList);
    }
    employeeRepository
        .findAll()
        .forEach(
            e -> {
              employeeOutDtoList.add(convertToDto(e));
            });
    return Optional.of(employeeOutDtoList);
  }

  /**
   * Performs employee login based on email and password.
   *
   * @param employeeLoginDto The input DTO containing email and password for
   *                         employee login.
   * @return An optional containing the employee details in the form of an
   *         EmployeeOutDto if login is successful; otherwise,
   *         an empty optional.
   */
  @Override
  public Optional<EmployeeOutDto> loginEmployee(
      final EmployeeLoginDto employeeLoginDto) {
    LOGGER.info("Performing employee login for email: {}",
        employeeLoginDto.getEmail());
    Employee employee = employeeRepository.findByEmailAndPassword(
        employeeLoginDto.getEmail(), employeeLoginDto.getPassword());

    if (!Objects.isNull(employee)) {
      LOGGER.info("Employee login successful for email: {}",
          employeeLoginDto.getEmail());
      return Optional.ofNullable(convertToDto(employee));
    }
    LOGGER.error("Employee login failed for email: {}",
        employeeLoginDto.getEmail());
    throw new ResourceNotFoundException(ErrorConstants.EMPLOYEE_NOT_FOUND);
  }

  /**
   * changePassword for existing user.
   *
   * @param changePasswordInDto
   * @param email
   */
  @Override
  public void changePassword(final ChangePasswordInDto changePasswordInDto,
      final String email) {
    LOGGER.info("Changing password for email: {}", email);
    Employee employee = employeeRepository.findByEmailAndPassword(email,
        changePasswordInDto.getOldPassword());
    if (Objects.isNull(employee)) {
      LOGGER.error("Employee with email {} not found for password change.",
          email);
      throw new ResourceNotFoundException(ErrorConstants.EMPLOYEE_NOT_FOUND);
    }

    if (!employee.getPassword()
        .equals(changePasswordInDto.getNewPassword())) {
      employee.setPassword(changePasswordInDto.getNewPassword());
      employee.setFirstTimeUser(false);
      employeeRepository.save(employee);
      LOGGER.info("Password changed successfully for email: {}", email);
      return;
    }
    LOGGER.error("Password change failed for email: {}", email);
    throw new CustomException("New Password can not be equal to Old Password");
  }

  /**
   * delete employee by Id.
   * @param email
   * @param deleteEmployee
   */
  @Override
  public void deleteEmployeeById(
      final String email, final String deleteEmployee) {
    LOGGER.info("Deleting employee with email: {}", email);
    if (deleteEmployee.equals(email)) {
      LOGGER.info("user can not delete itself {}", email);
      throw new CustomException(ErrorConstants.EMPLOYEE_SELF_DELETE);
    }
    Employee employee = employeeRepository.findByEmail(deleteEmployee);
    if (Objects.isNull(employee)) {
      LOGGER.error("Employee with email {} not found for deletion.",
          deleteEmployee);
      throw new ResourceNotFoundException(deleteEmployee);
    }
    employeeRepository.delete(employee);
    LOGGER.info("Employee with email {} deleted successfully.", deleteEmployee);
  }

  /**
   * Converts an Employee entity object into an EmployeeOutDto data transfer
   * object (DTO).
   *
   * @param employee The Employee entity to be converted.
   * @return An EmployeeOutDto representing the employee's data.
   */
  private EmployeeOutDto convertToDto(final Employee employee) {
    EmployeeOutDto employeeOutDto = modelMapper.map(employee,
        EmployeeOutDto.class);
    return employeeOutDto;
  }

  /**
   * Converts an EmployeeInDto dto object into an Employee entity.
   *
   * @param employeeInDto The Employee entity to be converted.
   * @return An Employee representing the employee's data.
   */
  private Employee convertToEntity(final EmployeeInDto employeeInDto) {
    Employee employee = modelMapper.map(employeeInDto, Employee.class);
    return employee;
  }
}
