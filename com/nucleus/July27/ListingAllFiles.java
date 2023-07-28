/**
 * 
 */
package com.nucleus.July27;

import java.io.File;

/**
 * 
 */
public class ListingAllFiles {

	/**
	 * 
	 */
	public ListingAllFiles() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File("C:\\Users\\palak\\OneDrive\\Documents");
		
		for(String s: file.list()) {
			System.out.println(s);
		}
	}

}
