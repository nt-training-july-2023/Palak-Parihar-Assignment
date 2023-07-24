/**
 * 
 */
package com.nucleus.OOPs;

/**
 * 
 */
public class Encapsulation {

	/**
	 * 
	 */
	public Encapsulation() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Employee employee = new Employee();
		employee.setfName("Employee");
		employee.setlName("Last Name");
		employee.setPhoneNo("3456789");
		
		System.out.println(employee);
	}

}

class Employee{
	String fName;
	String lName;
	String phoneNo;
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	@Override
	public String toString() {
		return "Employee [fName=" + fName + ", lName=" + lName + ", phoneNo=" + phoneNo + "]";
	}
	
	
	
}
