/**
 * 
 */
package com.nucleus.July25;

import java.util.Scanner;

/**
 * 
 */
public class ThreadMain extends Thread {

	public void run() {
		System.out.println("Current Thread : " + Thread.currentThread().getId());

		try (Scanner sc = new Scanner(System.in)) {
			System.out.print("Enter Number : ");
			int num = sc.nextInt();
			// Printing Numbers
			PrintingNumbers numbers1 = new PrintingNumbers();
			numbers1.print(num);

			Fibonacci fibonacci = new Fibonacci();
			fibonacci.print(num);

			SumOfAllNumbers allNumbers = new SumOfAllNumbers();
			allNumbers.print(num);
			
			ReverseTheList list = new ReverseTheList();
			list.print(num);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//
//		for (int i = 0; i < 8; i++) {
//			ThreadMain threadMain = new ThreadMain();
//			threadMain.start();
//		}

		ThreadMain threadMain = new ThreadMain();
		threadMain.start();

	}

}
