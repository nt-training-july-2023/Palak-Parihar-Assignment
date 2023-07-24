/**
 * 
 */
package com.nucleus.Property;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 */
public class PropertyClass {

	/**
	 * 
	 */
	public PropertyClass() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileReader fileReader = null;
		try {
			fileReader = new FileReader("C:\\Users\\palak\\OneDrive\\Desktop\\Java\\Test.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(fileReader.toString());
		
		Properties properties = new Properties();
		properties.load(fileReader);
		System.out.println(properties.toString());
		
		System.out.println(properties.get("password"));
	}

}
