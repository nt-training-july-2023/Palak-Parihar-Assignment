/**
 * 
 */
package com.nucleus.July25;

/**
 * 
 */
public class SumOfAllNumbers {

	/**
	 * 
	 */
	public SumOfAllNumbers() {
		// TODO Auto-generated constructor stub
	}
	
	public void print(int num) {
		long sum = 0;
		for(int i=0; i<=num; i++) {
			sum+=i;
		}
		System.out.println("Sum of All Numbers : "+sum);
	}

}
