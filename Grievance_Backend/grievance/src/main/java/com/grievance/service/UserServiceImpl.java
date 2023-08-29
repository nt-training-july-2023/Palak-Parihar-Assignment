package com.grievance.service;

import com.grievance.dto.EmployeeDto;
import com.grievance.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for employee.
 */
@Service
public class UserServiceImpl implements UserService {
  /**
   * Autowiring user repository.
   */
  @Autowired
  private UserRepository repository;

  /**
   * overriding login method from userservice.
   */
  @Override
  public Optional<Boolean> login(final EmployeeDto user) {
    EmployeeDto user2 = repository.findByEmail(user.getEmail());
    if (user2 != null) {
      if (user2.getPassword().equals(user.getPassword())) {
        return Optional.of(true);
      }
    }
    return Optional.of(false);
  }

  /**
   * overriding saveUser method from userservice.
   */
  @Override
  public Optional<EmployeeDto> saveUser(final EmployeeDto user) {
    // TODO Auto-generated method stub
    return Optional.ofNullable(repository.save(user));
  }

  /**
   * overriding listUser method from userservice.
   */
  @Override
  public Optional<List<EmployeeDto>> listUser() {
    // TODO Auto-generated method stub
    List<EmployeeDto> employees = new ArrayList<EmployeeDto>();
    repository.findAll().forEach(employees::add);
    return Optional.ofNullable(employees);
  }
}
