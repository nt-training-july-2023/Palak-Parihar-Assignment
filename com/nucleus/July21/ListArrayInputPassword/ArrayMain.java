/**
 * 
 */
package com.nucleus.July21.ListArrayInputPassword;

import java.util.Scanner;

/**
 * 
 */
public class ArrayMain {

	/**
	 * 
	 */
	public ArrayMain() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Integer array[] = new Integer[23];
		for (int i = 0; i < array.length; i++) {
			array[i] = i + 2;
		}

		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Please entere the index of element to be found");
			int index = sc.nextInt();
			
			if(array == null) {
				throw new NullPointerException("Array is Null");
			}
			
			if(index > array.length-1) {
				throw new ArrayIndexOutOfBoundsException("Entered index is greater than the Array's length");
			}
			
			System.out.println(array[index]);
		}catch (NullPointerException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}catch (ArrayIndexOutOfBoundsException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

}
