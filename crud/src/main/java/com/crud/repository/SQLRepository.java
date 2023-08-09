package com.crud.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.crud.entity.Department;
import com.crud.entity.Employee;

@Repository
public interface SQLRepository extends CrudRepository<Employee, Integer> {

	public List<Employee> findByFirstName(String firstName);

	public List<Employee> findByLastName(String lastName);

	public List<Employee> findByDateOfJoining(Date dateOfJoining);

	public List<Employee> findByDepartment(Department department);

	public List<Employee> findBySalary(Long salary);

	public Employee findByEmail(String email);
}
