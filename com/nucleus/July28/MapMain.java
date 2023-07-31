/**
 * 
 */
package com.nucleus.July28;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 
 */
public class MapMain {

	/**
	 * 
	 */
	public Map<Integer, String> EmployeeMap = null;
	public MapMain() {
		// TODO Auto-generated constructor stub
		EmployeeMap = new HashMap<Integer, String>();
	}
	
	
	public void enterValues(Integer EmployeeId, String EmployeeName) {
		EmployeeMap.put(EmployeeId, EmployeeName);
	}
	
	public Integer findEmployeeId(String EmployeeName) {
		for(Map.Entry<Integer, String> map : EmployeeMap.entrySet()) {
			if(map.getValue().toLowerCase().equals(EmployeeName.toLowerCase())) {
				return map.getKey();
			}
		}
		return -1;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MapMain mapMain =  new MapMain();
		mapMain.enterValues(1, "Joey");
		mapMain.enterValues(2, "Niklaus");
		mapMain.enterValues(3, "Hayley");
		mapMain.enterValues(4, "Caroline");
		mapMain.enterValues(5, "Atlas");
		mapMain.enterValues(6, "Lily");
		
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Enter the Employee Name to find its id");
			
			String EmployeeName = scanner.nextLine();
			
			Integer EmployeeId = mapMain.findEmployeeId(EmployeeName);
			
			if(EmployeeId==-1) {
				System.out.println("Employee doesn't exist");
			}else {
				System.out.println("Employee Id : " + EmployeeId);
			}
		}

	}

}
