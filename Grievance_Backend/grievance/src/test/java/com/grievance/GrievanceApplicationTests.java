package com.grievance;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.grievance.entity.Department;
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
	  
	  Department department = new Department();
	  department.setDepartmentName("HR");
////	  department.se
//	  
//	  departmentRepository.save(department);
	  
	  Employee employee = new Employee();
	  employee.setDepartment(department);
	  employee.setEmail("example@nucleusteq.com");
	  employee.setFullName("Example Nucleusteq");
	  employee.setPassword("1234");
	  employee.setUserType(UserType.MEMBER);
//	  employee.s
	  
	  employeeRepository.save(employee);
	  
  }
}
