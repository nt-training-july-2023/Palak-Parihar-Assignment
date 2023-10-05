package com.crud;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.crud.controller.employeeController;
import com.crud.entity.Department;
import com.crud.entity.Employee;
import com.crud.repository.SQLRepository;
import com.crud.service.EmployeeService;

@SpringBootTest
class CrudApplicationTests {

	@Autowired
	employeeController controller;

	@Autowired
	EmployeeService service;

	@Test
	void contextLoads() {
	}

	@Test
	void saveEmployeeTest() {
		Employee employee = new Employee();
		employee.setEmail("nucleusteq@gmail.com");
		employee.setAddress("92 ABC");
		employee.setFirstName("Nucleusteq");
		employee.setLastName("Teq");
		String str = "2023-03-31";
		Date date = Date.valueOf(str);
		employee.setDateOfJoining(date);
		employee.setSalary(123455l);
		employee.setDepartment(Department.LEGAL);

		controller.saveEmployee(employee);
	}

	@Autowired
	SQLRepository repository;

	@Test
	void testRepo() {
//		System.out.println(repository.findByEmail("cor@nucleusteq.com"));
		System.out.println();
	}

}
