/**
 * 
 */
package com.nucleus.July25;

/**
 * 
 */

class CashWithdrawal extends Thread {
	int cash;

	public CashWithdrawal(int cash) {
		// TODO Auto-generated constructor stub
		this.cash = cash;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		BankingApplication.withdrawn(cash);

	}
}

class CashDeposit extends Thread {

	int cash;

	public CashDeposit(int cash) {
		super();
		this.cash = cash;
	}

	@Override
	public void run() {
		// TODO Auto-gesuper.run();
		BankingApplication.deposit(77);
	}
}

public class BankingApplicationMain extends Thread {

	/**
	 * 
	 */
	public BankingApplicationMain() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new CashWithdrawal(123).start();
		new CashDeposit(66).start();
		
		new CashWithdrawal(123).start();
		new CashDeposit(64).start();
		new CashWithdrawal(23).start();
		new CashDeposit(12).start();
		new CashDeposit(66).start();

	}

}
