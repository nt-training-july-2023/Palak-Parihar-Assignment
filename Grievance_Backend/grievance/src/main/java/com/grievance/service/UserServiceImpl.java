package com.grievance.service;

import com.grievance.entity.Employee;
import com.grievance.repository.UserRepository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  @Autowired
  UserRepository repository;

  @Override
  public Optional<Boolean> login(Employee user) {
    // TODO Auto-generated method stub
    Employee user2 = repository.findByEmail(user.getEmail());
    if (user2 != null) {
      if (user2.getPassword().equals(user.getPassword())) {
        return Optional.of(true);
      }
    }
    return Optional.of(false);
  }

  @Override
  public Optional<Employee> saveUser(Employee user) {
    // TODO Auto-generated method stub
    return Optional.ofNullable(repository.save(user));
  }

  @Override
  public Optional<List<Employee>> listUser() {
    // TODO Auto-generated method stub
    List<Employee> employees = new ArrayList<Employee>();
    repository.findAll().forEach(employees::add);
    return Optional.ofNullable(employees);
  }
}