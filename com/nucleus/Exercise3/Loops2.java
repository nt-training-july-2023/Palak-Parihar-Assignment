/**
 * 
 */
package com.nucleus.Exercise3;

import java.util.Scanner;

/**
 * 
 */
public class Loops2 {

	/**
	 * 
	 */
	public Loops2() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		//Reverse A number
		int number = sc.nextInt();
		
		int op = number;
		
		int reverse = 0;
		while(op!=0) {
			reverse= reverse * 10 + (op%10);
			op/=10;
		}
		System.out.println(reverse);
		
		System.out.println("The number is " + ((reverse == number) ? "": "not")+ "palindrome");
		
		int num = sc.nextInt();
		System.out.println("Printing pyramid ");
		
		for(int i=1; i<=num; i++) {
			for(int j=0; j<=i; j++) {
				System.out.print(i+" ");
			}
			System.out.println();
		}
	}

}
