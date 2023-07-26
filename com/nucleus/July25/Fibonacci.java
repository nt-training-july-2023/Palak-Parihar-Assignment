/**
 * 
 */
package com.nucleus.July25;

/**
 * 
 */
public class Fibonacci {

	/**
	 * 
	 */
	public void print(int num) {
		
		System.out.println("Printing fibonnaci");
		int dp[] = new int[num];
		dp[0] = 0;
		System.out.print(0+" ");
		dp[1] = 1;
		System.out.print(1+" ");
		for(int i = 2; i<=num; i++) {
			System.out.print(i+" ");
		}
		System.out.println();
	}

}
