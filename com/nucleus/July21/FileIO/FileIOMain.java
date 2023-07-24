/**
 * 
 */
package com.nucleus.July21.FileIO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


/**
 * 
 */
public class FileIOMain {

	/**
	 * 
	 */
	public FileIOMain() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File file = null;
		FileReader fileReader = null;
		try {
			file = new File("C:\\Users\\palak\\OneDrive\\Desktop\\Test.txt\\");
			fileReader = new FileReader(file);
		}
		catch (FileNotFoundException e) {
			// TODO: handle exception
			System.out.println("Error Message : " + e.getMessage());
		}		
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error Message : "+e.getMessage());
		}
		
		int i;
		while((i = fileReader.read()) !=-1) {
			System.out.print((char)i);
		}
		fileReader.close();
		
		;
	}

}
