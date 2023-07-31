package com.nucleus.July28;

import java.util.Map;

public class EmployeeHashMap {
	public Map<Integer, Employee> EmployeeHashMap;

	public EmployeeHashMap(Map<Integer, Employee> employeeHashMap) {
		super();
		EmployeeHashMap = employeeHashMap;
	}

	public int findKey(String employeeName) {
		for (Map.Entry<Integer, Employee> entry : EmployeeHashMap.entrySet()) {
			if (employeeName.toLowerCase().equals(entry.getValue().getEmployeeName().toLowerCase())) {
				return entry.getKey();
			}
		}
		return -1;
	}

	public String removeEntry(Integer EmployeeId) {
		try {
			EmployeeHashMap.remove(EmployeeId);
			return "Entry Successfully Deleted ";
		} catch (NullPointerException e) {
			// TODO: handle exception
			return "Map is Null";
		}
	}

	public void putEntry(Integer employeeId, Employee employee) {
		EmployeeHashMap.put(employeeId, employee);
	}

}
