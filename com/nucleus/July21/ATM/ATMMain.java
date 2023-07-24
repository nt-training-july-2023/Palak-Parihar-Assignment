/**
 * 
 */
package com.nucleus.July21.ATM;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 
 */
public class ATMMain {

	/**
	 * 
	 */
	public ATMMain() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		long account_no = 0;
		double amount_to_withdraw = 0;
		try {
			 account_no = sc.nextLong();
			 amount_to_withdraw = sc.nextDouble();
			 if(account_no <0 || amount_to_withdraw <0) {
				 throw new InvalidInputException("Message : Negative Value Entered");
			 }
		}catch (InvalidInputException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		
	}

}
