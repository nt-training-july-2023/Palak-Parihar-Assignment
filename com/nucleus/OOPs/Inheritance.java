/**
 * 
 */
package com.nucleus.OOPs;

/**
 * 
 */

class Company {
	String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
	


class EmployeeI extends Salary{
	String Name;
	String ContactNo;
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getContactNo() {
		return ContactNo;
	}
	public void setContactNo(String contactNo) {
		ContactNo = contactNo;
	}
	@Override
	public String toString() {
		return "EmployeeI [Name=" + Name + ", ContactNo=" + ContactNo + ", address=" + address + "]";
	}
	
	
	
	
}

class Salary extends Company{
	double salary;

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
	
}

public class Inheritance {

	/**
	 * 
	 */
	public Inheritance() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EmployeeI employeeI = new EmployeeI();
		employeeI.setName("EMployee1");
		employeeI.setContactNo("2345678");
		employeeI.setAddress("ASDF");
		employeeI.setSalary(2345.56);
		
		System.out.println(employeeI);
	}
	

}
