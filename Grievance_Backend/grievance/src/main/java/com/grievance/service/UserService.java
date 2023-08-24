package com.grievance.service;

import com.grievance.entity.Employee;
import java.util.List;
import java.util.Optional;

public interface UserService {
  public Optional<Boolean> login(Employee employee);

  public Optional<Employee> saveUser(Employee employee);

  public Optional<List<Employee>> listUser();
}
