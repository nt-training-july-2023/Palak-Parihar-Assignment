/**
 * 
 */
package com.nucleus.July21.Area;

import java.util.Scanner;

/**
 * 
 */
public class AreaOfRectangle {

	/**
	 * 
	 */
	public AreaOfRectangle() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int length = 0;
		int width = 0;
		System.out.println("Please Enter the value of length and breadth");
		try {
			length = sc.nextInt();
			width = sc.nextInt();
			if(length<=0 || width <=0) {
				throw new InvalidDimensionException("The Entered value of Length or Width are either zero or negative");
			}
		}catch (InvalidDimensionException e) {
			// TODO: handle exception
			System.out.println("Message : " +e.getMessage());
		}

	}

}
