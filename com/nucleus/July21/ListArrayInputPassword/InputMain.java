/**
 * 
 */
package com.nucleus.July21.ListArrayInputPassword;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 
 */
public class InputMain {

	/**
	 * 
	 */
	public InputMain() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws NotEvenNumberException 
	 */
	public static void main(String[] args) throws NotEvenNumberException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a Number");
		double a = 0;
		
		try {
			a = sc.nextDouble();
			if(a%2 != 0) {
				throw new NotEvenNumberException("The entered value isn't even");
			}else {
				System.out.println("The entered value is even");
			}
		}
		catch (NotEvenNumberException e) {
			// TODO: handle exception
			System.out.println("Error : "+e.getMessage());
		}
		catch (InputMismatchException e) {
			// TODO: handle exception
			throw  new NotEvenNumberException("The Entered Value isn't Numeric");
		}
	}

}
