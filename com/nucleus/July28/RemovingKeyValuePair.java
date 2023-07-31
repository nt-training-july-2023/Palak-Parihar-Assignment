/**
 * 
 */
package com.nucleus.July28;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 */
public class RemovingKeyValuePair {

	/**
	 * 
	 */
	
	EmployeeHashMap employeeHashMap;
	
	public RemovingKeyValuePair() {
		// TODO Auto-generated constructor stub
	}
	
	

	public RemovingKeyValuePair(EmployeeHashMap employeeHashMap) {
		super();
		this.employeeHashMap = employeeHashMap;
	}
	
	public void removePair(Integer employeeId, Employee employee) {
		Employee employee2 = this.employeeHashMap.EmployeeHashMap.get(employeeId);
		if(employee2.getEmployeeName().equals(employee.getEmployeeName()) && employee2.getEmployeeEmail().equals(employee.getEmployeeEmail())) {
			this.employeeHashMap.removeEntry(employeeId);
		}
	}



	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RemovingKeyValuePair keyValuePair = new RemovingKeyValuePair(new EmployeeHashMap(new HashMap<Integer, Employee>()));
	    
		keyValuePair.employeeHashMap.putEntry(1, new Employee("Jack", "jack@gmail.com"));
	
		keyValuePair.employeeHashMap.putEntry(2, new Employee("Katie", "katie@gmail.com"));
		
		keyValuePair.employeeHashMap.putEntry(3, new Employee("Jerry", "jerry@gmail.com"));
		
		keyValuePair.employeeHashMap.putEntry(4, new Employee("Tom", "tom@gmail.com"));
		
		keyValuePair.employeeHashMap.putEntry(5, new Employee("Death", "death@gmail.com"));
		
		System.out.println(keyValuePair.employeeHashMap.EmployeeHashMap);
		
		keyValuePair.removePair(5, new Employee("Death", "death@gmail.com"));
		
		System.out.println(keyValuePair.employeeHashMap.EmployeeHashMap);
		
	}

}
