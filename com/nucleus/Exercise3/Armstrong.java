/**
 * 
 */
package com.nucleus.Exercise3;

import java.util.Scanner;

/**
 * 
 */
public class Armstrong {

	/**
	 * 
	 */
	public Armstrong() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		int number = sc.nextInt();
		int op = number;
		int n = 0;
		for(; op!=0 ; ++n) {
			op/=10;
		}
		op = number;
		
		int result = 0;
		for(; op!=0; ) {
			int c = op%10;
			result+= Math.pow(c, n);
			op/=10;
		}
		System.out.println("The given number is "+ (number == result ? "" : "not") + " Armstrong");
	}

}
