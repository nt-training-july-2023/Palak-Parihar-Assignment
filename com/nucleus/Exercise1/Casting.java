/**
 * 
 */
package com.nucleus.Exercise1;

/**
 * 
 */
public class Casting {

	/**
	 * 
	 */
	public Casting() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		byte b= 12;
		char c = 'a';
		long l = 11111112345l;
		int i = 234;
		double d = 35234567898765.98;
		float f = 234f;
		
		System.out.println("byte : "+b);
		System.out.println("char : "+c);
		System.out.println("long : "+l);
		System.out.println("int : "+i);
		System.out.println("double : "+d);
		System.out.println("float : "+f);
		
		int cs;
		System.out.println("Casting char to int : " + (cs = c));
		System.out.println("Casting long to int : " + (int)l);
		System.out.println("Casting double to float : " + (float)d);
	}

}
