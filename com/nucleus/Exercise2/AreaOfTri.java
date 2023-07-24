/**
 * 
 */
package com.nucleus.Exercise2;


import java.io.*;
import java.util.Scanner;
/**
 * 
 */
public class AreaOfTri {

	/**
	 * 
	 */
	public AreaOfTri() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		double base = sc.nextDouble();
		double height = sc.nextDouble();
		
		System.out.println("Area of triangler is : " + (base * height)/2);
	}

}
