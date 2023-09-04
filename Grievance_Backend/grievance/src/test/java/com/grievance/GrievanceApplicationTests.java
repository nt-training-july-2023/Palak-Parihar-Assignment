package com.grievance;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.grievance.entity.Department;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.grievance.entity.Employee;
import com.grievance.entity.UserType;
import com.grievance.repository.DepartmentRepository;
import com.grievance.repository.EmployeeRepository;

@SpringBootTest
class GrievanceApplicationTests {

  @Test
  void test() {
    assertEquals(true, true);
  }
  
  @Autowired
  EmployeeRepository employeeRepository;
  
  @Autowired
  DepartmentRepository departmentRepository;
  
  @Test
  void saveEmployee() {
	  
//	  Department department = departmentRepository.findByDepartmentName("HR");
//	  Employee employee = new Employee();
//	  employee.setDepartment(department);
//	  employee.setEmail("example@nucleusteq.com");
//	  employee.setFirstTimeUser(true);
//	  employee.setFullName("Example");
//	  employee.setPassword("Example#123");
//	  System.out.println(employeeRepository.save(employee));
//	  
  }
}
