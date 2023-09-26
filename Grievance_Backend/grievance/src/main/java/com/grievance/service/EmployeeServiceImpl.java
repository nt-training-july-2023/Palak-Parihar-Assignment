package com.grievance.service;

import com.grievance.dto.ChangePasswordInDto;
import com.grievance.dto.EmployeeInDto;
import com.grievance.dto.EmployeeLoginDto;
import com.grievance.dto.EmployeeOutDto;
import com.grievance.entity.Employee;
import com.grievance.exception.EmployeeAlreadyExistException;
import com.grievance.exception.EmployeeNotFoundException;
import com.grievance.exception.PasswordMatchException;
import com.grievance.repository.EmployeeRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The EmployeeServiceImpl class is an
 * implementation of the EmployeeService interface
 * that provides functionality for managing employee data in the application.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
  /**
   * The employeeRepository instance provide data access method
   * for interacting with database.
   */
  @Autowired
  private EmployeeRepository employeeRepository;

  /**
   * The ModelMapper instance used for mapping between
   * different data structures,
   * such as converting between Entity objects
   *     and DTOs (Data Transfer Objects).
   */
  @Autowired
  private ModelMapper modelMapper;

  /**
   * Saves a new employee record.
   *
   * @param employeeInDto The input DTO containing
   *     employee information to be saved.
   * @return An optional containing the saved employee
   *     details in the form of an EmployeeOutDto.
   */
  @Override
  public Optional<EmployeeOutDto> saveEmployee(
    final EmployeeInDto employeeInDto) {

     Employee employee = employeeRepository
           .findByEmail(employeeInDto.getEmail());
     if (Objects.isNull(employee)) {
        employee = convertToEntity(employeeInDto);
        employee = employeeRepository.save(employee);
        EmployeeOutDto employeeOutDto = convertToDto(employee);
        return Optional.of(employeeOutDto);
    }
    throw new EmployeeAlreadyExistException();
  }

  /**
   * Retrieves a list of all employees.
   *
   * @return An optional containing a list of
   *     EmployeeOutDto objects representing all employees.
   */
  @Override
  public Optional<List<EmployeeOutDto>> listAllEmployees() {
    List<EmployeeOutDto> employeeOutDtoList = new ArrayList<>();
    employeeRepository
      .findAll()
      .forEach(
        e -> {
          employeeOutDtoList.add(convertToDto(e));
        }
      );
    return Optional.of(employeeOutDtoList);
  }

  /**
   * Performs employee login based on email and password.
   *
   * @param employeeLoginDto The input DTO containing
   *     email and password for employee login.
   * @return An optional containing the employee
   *     details in the form of an
   *     EmployeeOutDto if login is successful;
   *         otherwise, an empty optional.
   */
  @Override
  public Optional<EmployeeOutDto> loginEmployee(
       final EmployeeLoginDto employeeLoginDto) {

    Employee employee = employeeRepository
        .findByEmailAndPassword(employeeLoginDto.getEmail(),
                employeeLoginDto.getPassword());

    if (!Objects.isNull(employee)) {
      return Optional.ofNullable(convertToDto(employee));
    }
    throw new EmployeeNotFoundException(employeeLoginDto.getEmail());
  }

  /**
   * changePassword for existing user.
   *@param changePasswordInDto
   *
   */
  @Override public void changePassword(
          final ChangePasswordInDto changePasswordInDto,
          final String email) {
       Employee employee = employeeRepository.findByEmailAndPassword(
                                   email,
                                   changePasswordInDto.getOldPassword());
       if (Objects.isNull(employee)) {
           throw new EmployeeNotFoundException(email);
       }

       if (!employee.getPassword().equals(
           changePasswordInDto.getNewPassword())) {
         employee.setPassword(changePasswordInDto.getNewPassword());
         employee.setFirstTimeUser(false);
         employeeRepository.save(employee);
         return;
       }
     throw new PasswordMatchException();
   }

  /**
   * delete employee by Id.
   * @param email
   */
  @Override
  public void deleteEmployeeById(final String email) {
    Employee employee = employeeRepository.findByEmail(email);
    if (Objects.isNull(employee)) {
      throw new EmployeeNotFoundException(email);
    }
    employeeRepository.delete(employee);
  }

  /**
   * Converts an Employee entity object into an
   * EmployeeOutDto data transfer object (DTO).
   *
   * @param employee The Employee entity to be converted.
   * @return An EmployeeOutDto representing the employee's data.
   */
  private EmployeeOutDto convertToDto(final Employee employee) {
    EmployeeOutDto employeeOutDto =
    modelMapper.map(employee, EmployeeOutDto.class);
    return employeeOutDto;
  }

  /**
   * Converts an EmployeeInDto dto object into an
   * Employee entity.
   *
   * @param employeeInDto The Employee entity to be converted.
   * @return An Employee representing the employee's data.
   */
  private Employee convertToEntity(final EmployeeInDto employeeInDto) {
    Employee employee = modelMapper.map(employeeInDto, Employee.class);
    return employee;
  }
}
