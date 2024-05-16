package com.crud.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.entity.Department;
import com.crud.entity.Employee;
import com.crud.repository.SQLRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	SQLRepository repository;

	@Override
	public Optional<Employee> saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return Optional.of(repository.save(employee));
	}

	@Override
	public Optional<List<Employee>> listAllEmployees() {
		// TODO Auto-generated method stub

		List<Employee> employees = new ArrayList<Employee>();
		repository.findAll().forEach(employees::add);
		return Optional.ofNullable(employees);
	}

	@Override
	public Optional<Employee> findEmployeeById(Integer employeeId) {
		// TODO Auto-generated method stub
		return repository.findById(employeeId);
	}

	@Override
	public Optional<Employee> findByEmail(String email) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(repository.findByEmail(email));
	}

	@Override
	public Optional<List<Employee>> listAllEmployeesByDateOfJoining(Date date) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(repository.findByDateOfJoining(date));
	}

	@Override
	public Optional<List<Employee>> listAllEmployeesByDepartment(Department department) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(repository.findByDepartment(department));
	}

	@Override
	public Optional<List<Employee>> listAllEmployeesBySalary(Long salary) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(repository.findBySalary(salary));
//		return null;
	}

	@Override
	public Optional<Employee> updateEmployeeDetails(Employee employee) {
		Employee employee2 = repository.findByEmail(employee.getEmail());
		if (employee2 == null) {
			return Optional.empty();
		}
		employee2.setAddress(employee.getAddress());
		employee2.setDateOfJoining(employee.getDateOfJoining());
		employee2.setDepartment(employee.getDepartment());
		employee2.setEmail(employee.getEmail());
		employee2.setFirstName(employee.getFirstName());
		employee2.setLastName(employee.getLastName());
		employee2.setSalary(employee.getSalary());

		return Optional.ofNullable(repository.save(employee2));
	}

	@Override
	public Optional<String> deleteEmployee(Employee employee) {
		// TODO Auto-generated method stub
	    Employee employee1 = repository.findByEmail(employee.getEmail());
		if (employee1 == null) {
			return Optional.empty();
		}
		repository.delete(employee);
		return Optional.of("Employee Deleted Successfully");
	}

}
