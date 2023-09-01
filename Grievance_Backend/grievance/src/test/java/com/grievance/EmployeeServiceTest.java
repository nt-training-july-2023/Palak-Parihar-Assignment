package com.grievance;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.grievance.entity.Employee;
import com.grievance.repository.EmployeeRepository;
import com.grievance.service.EmployeeService;

import edu.emory.mathcs.backport.java.util.Collections;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class EmployeeServiceTest {
	
	@Mock
	EmployeeRepository employeeRepository;
	
	@Autowired
	EmployeeService employeeService;
	
	@Test
	public void when_no_employee_return_empty_list() {
		Mockito.when(employeeRepository.findAll()).thenReturn(new ArrayList<Employee>());
		List<Employee> list = employeeService.listAllEmployees();
		System.out.println("list : "+list);
		assertTrue(list.size()==0);
	}
}

