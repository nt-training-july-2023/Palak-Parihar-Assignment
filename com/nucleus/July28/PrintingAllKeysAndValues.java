/**
 * 
 */
package com.nucleus.July28;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 */

public class PrintingAllKeysAndValues {

	public EmployeeHashMap employeeHashMap;

	/**
	 * 
	 */
	public PrintingAllKeysAndValues() {
		// TODO Auto-generated constructor stub
		employeeHashMap = new EmployeeHashMap(new HashMap<Integer, Employee>());
	}

	public void GeneratingMap(Integer EmployeeId, Employee employee) {
		employeeHashMap.EmployeeHashMap.put(EmployeeId, employee);
	}

	public void print(EmployeeHashMap employeeHashMap) {
		for (Map.Entry<Integer, Employee> map : employeeHashMap.EmployeeHashMap.entrySet()) {
			System.out.print("Employee Id : " + map.getKey() + ", ");
			System.out.print("Employee Name : " + map.getValue().getEmployeeName() + ", ");
			System.out.println("Employee Email : " + map.getValue().getEmployeeName());
		}
	}

	public void printAllKeys(EmployeeHashMap employeeHashMap) {
		System.out.println("Printing All Keys");
		for (Integer integer : employeeHashMap.EmployeeHashMap.keySet()) {
			System.out.println(integer);
		}
	}

	public void printAllValues(EmployeeHashMap employeeHashMap) {
		System.out.println("Printing All values");
		for (Employee employee : employeeHashMap.EmployeeHashMap.values()) {
			System.out.print("Employee Name : " + employee.getEmployeeName()+" , ");
			System.out.println("Employee Gmail : " + employee.getEmployeeName());
			;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PrintingAllKeysAndValues allKeysAndValues = new PrintingAllKeysAndValues();

		Employee employee1 = new Employee("Atlas", "atlas@gmail.com");
		Employee employee2 = new Employee("Lily", "lily@gmail.com");
		Employee employee3 = new Employee("Phoebe", "phoebe@gmail.com");
		Employee employee4 = new Employee("Monica", "monica@gmail.com");

		allKeysAndValues.GeneratingMap(1, employee1);
		allKeysAndValues.GeneratingMap(2, employee2);
		allKeysAndValues.GeneratingMap(3, employee3);
		allKeysAndValues.GeneratingMap(4, employee4);

		allKeysAndValues.print(allKeysAndValues.employeeHashMap);

		allKeysAndValues.printAllKeys(allKeysAndValues.employeeHashMap);

		allKeysAndValues.printAllValues(allKeysAndValues.employeeHashMap);
	}

}
