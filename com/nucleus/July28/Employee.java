package com.nucleus.July28;

public class Employee {
	String EmployeeName;
	String EmployeeEmail;

	public Employee() {
		
	}
	public Employee(String employeeName, String employeeEmail) {
		super();
		EmployeeName = employeeName;
		EmployeeEmail = employeeEmail;
	}

	public String getEmployeeName() {
		return EmployeeName;
	}

	public String getEmployeeEmail() {
		return EmployeeEmail;
	}

}
