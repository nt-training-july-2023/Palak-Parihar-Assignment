/**
 * 
 */
package com.nucleus.July21.ListArrayInputPassword;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 */
public class ListMain {

	/**
	 * 
	 */
	public ListMain() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> list = null;
		Scanner sc = new Scanner(System.in);
		
		list = new ArrayList<String>();
		for(int i=0; i<5; i++) {
			list.add("AB "+i);
		}
		
		
		System.out.println("Enter the index of element to find");
		int a = sc.nextInt();
		
		try {
			if(list == null) {
				throw new NullPointerException("The List of Strings is Null");
			}
			if(a > list.size()-1) {
				throw new IndexOutOfBoundsException("The entered index is greater than the list size");
			}
			
			System.out.println("String : "+ list.get(a));
		}catch (NullPointerException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		catch (IndexOutOfBoundsException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}

}
