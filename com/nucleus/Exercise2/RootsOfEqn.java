/**
 * 
 */
package com.nucleus.Exercise2;

import java.io.*;
import java.util.*;
/**
 * 
 */
public class RootsOfEqn {

	/**
	 * 
	 */
	public RootsOfEqn() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		double a = sc.nextDouble();
		double b = sc.nextDouble();
		double c = sc.nextDouble();
		
		double d = (b*b) - (4 * a * c);
		System.out.println(d);
		double root1 = ((-1 * b) + Math.sqrt(d))/2 * a;
		double root2 = ((-1 * b) - Math.sqrt(d))/2 * a;
		
		System.out.println("Roots of the equation : "+ root1 +" "+ root2);
	}

}
