/**
 * 
 */
package com.nucleus.Static;

/**
 * 
 */


class Company{
	static String CompanyName = "NucleusTeq";
	
	
	public void nonstaticmethod() {
		System.out.println(CompanyName);
	}
}

public class StaticVariables {

	/**
	 * 
	 */
	static {
		System.out.println("Static block executing");
	}
	
	public StaticVariables() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	
	public void method1() {
		
		System.out.println(Company.CompanyName);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Company.CompanyName);
		
		new StaticVariables().method1();
	}

}
