/**
 * 
 */
package com.nucleus.Exercise5;

/**
 * 
 */
public class StringClass {

	/**
	 * 
	 */
	public StringClass() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String st = new String("NucleusTeq");
		
		System.out.println(st.length());
		System.out.println(st.concat(" String"));
		System.out.println(st.charAt(5));
		System.out.println(st.startsWith("N"));
		System.out.println(st.indexOf("T"));
		System.out.println(st.replace('T', 't'));
		
		//equals vs == operator
		
		String st2 = "NucleusTeq";
		System.out.println(st.equals(st2));
		System.out.println(st == st2);
		

	}

}
