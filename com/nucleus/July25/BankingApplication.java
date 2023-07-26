/**
 * 
 */
package com.nucleus.July25;

/**
 * 
 */
public class BankingApplication extends Thread {

	/**
	 * 
	 */

	public BankingApplication() {
		// TODO Auto-generated constructor stub
	}

	public static int totalValue = 100;

	static synchronized void withdrawn(int withdrawal) {
		System.out.println(" Money to be withdrawn : " + withdrawal);
		if (withdrawal <= totalValue) {
			totalValue -= withdrawal;
		} else {
			System.out.println(" Money to be withdrawn is greater than the present amount ");
		}
		System.out.println(" Current Balance : " + totalValue);
	}
	
	static synchronized void deposit(int deposit) {
		totalValue+= deposit;
		System.out.println(" Money to be deposited : " + deposit);
		System.out.println(" Current Balance : " + totalValue);
	}

}
