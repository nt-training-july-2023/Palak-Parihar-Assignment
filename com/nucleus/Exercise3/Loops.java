/**
 * 
 */
package com.nucleus.Exercise3;

import java.util.Scanner;

/**
 * 
 */
public class Loops {

	/**
	 * 
	 */
	public Loops() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		//Multiplication Table
		int i = 7;
		for(int j = 1; j<=10; j++) {
			System.out.println(i * j);
		}
		
		System.out.println("Enter n : ");
		//Sum of first n numbers
		int n = sc.nextInt();
		System.out.println(n*(n-1) / 2);
		System.out.println();
		int sum = 0;
		long fact = 1; 
		while(n--!=0) {
			sum+=n;
			if(n== 0) break;
			fact*=n;
		}
		System.out.println("Sum of first n natural numbers : " + sum);
		
		System.out.println("Factorial of n : " + fact);
		
		
	}

}
