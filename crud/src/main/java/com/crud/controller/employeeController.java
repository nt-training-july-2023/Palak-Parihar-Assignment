/**
 * 
 */
package com.crud.controller;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.entity.Department;
import com.crud.entity.Employee;
import com.crud.service.EmployeeService;

/**
 * 
 */

@RestController
@RequestMapping("/")
public class employeeController {

	@Autowired
	EmployeeService service;

	@PostMapping("/saveEmployee")
	public ResponseEntity saveEmployee(@RequestBody Employee employee) {
		Optional<Employee> opt = service.findByEmail(employee.getEmail());
		if (!opt.isEmpty()) {
			return new ResponseEntity<>("Employee with this email already exists", HttpStatus.CONFLICT);
		}
		Optional<Employee> savedEmployee = service.saveEmployee(employee);
		if (savedEmployee.isEmpty()) {
			return new ResponseEntity<>("Something unexpected happend", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity(savedEmployee, HttpStatus.CREATED);
	}

	@GetMapping("/listAllEmployees")
	public ResponseEntity listAllEmployees() {
		Optional<List<Employee>> listOfEmployees = service.listAllEmployees();
		return ResponseEntity.ok(listOfEmployees);
	}

	@GetMapping("/findEmployeeByEmail/{email}")
	public ResponseEntity findEmployeeByEmail(@PathVariable String email) {
		Optional<Employee> employee = service.findByEmail(email);
		if (employee.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(employee);
	}

	@GetMapping("/findEmployeeById/{id}")
	public ResponseEntity findEmployeeById(@PathVariable Integer id) {
		Optional<Employee> employee = service.findEmployeeById(id);
		if (employee.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(employee);
	}

	@DeleteMapping("/deleteEmployee")
	public ResponseEntity deleteEmployee(@RequestBody Employee employee) {
		Optional<String> message = service.deleteEmployee(employee);
		if (message.isEmpty()) {
			return new ResponseEntity<>("Employee doesn't exist", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(message);
	}

	@PutMapping("/updateEmployee")
	public ResponseEntity updateEmployee(@RequestBody Employee employee) {
		Optional<Employee> updateEmployee = service.updateEmployeeDetails(employee);
		if (updateEmployee.isEmpty()) {
			return new ResponseEntity<>("Employee with this Email Id doesn't exist", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(updateEmployee);
	}

	@GetMapping("/listEmployeesByDateOfJoining/{date}")
	public ResponseEntity listEmployeesByDateOfJoining(@PathVariable String date) {
		Date date2 = Date.valueOf(date);
		Optional<List<Employee>> list = service.listAllEmployeesByDateOfJoining(date2);
		return ResponseEntity.ok(list);
	}

	@GetMapping("/listEmployeesByDepartment/{department}")
	public ResponseEntity listEmployeesByDepartment(@PathVariable String department) {
		Department dept = Department.valueOf(department);
		Optional<List<Employee>> list = service.listAllEmployeesByDepartment(dept);
		return ResponseEntity.ok(list);
	}
}
