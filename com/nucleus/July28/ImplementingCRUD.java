/**
 * 
 */
package com.nucleus.July28;

import java.util.HashMap;
import java.util.Scanner;

/**
 * 
 */
public class ImplementingCRUD {

	/**
	 * 
	 */

	EmployeeHashMap employeeHashMap;
	PrintingAllKeysAndValues allKeysAndValues;

	

	public ImplementingCRUD(EmployeeHashMap employeeHashMap, PrintingAllKeysAndValues allKeysAndValues) {
		super();
		this.employeeHashMap = employeeHashMap;
		this.allKeysAndValues = allKeysAndValues;
	}

	public String AddEntry(Integer EmployeeId, Employee employee) {
		allKeysAndValues.GeneratingMap(EmployeeId, employee);
		return "Employee Added";
	}

	public void DeleteEntry(Integer EmployeeId) {
		employeeHashMap.EmployeeHashMap.remove(EmployeeId);
	}

	public void RetrieveEntry(Integer EmployeeId) {
		employeeHashMap.EmployeeHashMap.get(EmployeeId);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ImplementingCRUD crud = new ImplementingCRUD(new EmployeeHashMap(new HashMap<Integer, Employee>()), new PrintingAllKeysAndValues());
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println(crud.AddEntry(1, new Employee("Atlas", "atlas@gmail.com")));
			System.out.println(crud.AddEntry(2, new Employee("Lily", "lily@gmail.com")));
			System.out.println(crud.AddEntry(3, new Employee("Joe", "Joe@gmail.com")));
			System.out.println(crud.AddEntry(4, new Employee("Hope", "jhope@gmail.com")));
			
			crud.allKeysAndValues.print(crud.employeeHashMap);
			
			System.out.println("Enter Employee Name to Delete : ");
			
			String employeeName = scanner.nextLine();
			
			Integer EmployeeId = crud.employeeHashMap.findKey(employeeName);
			
			System.out.println(crud.employeeHashMap.removeEntry(EmployeeId));
		}
		
		
	}

}
