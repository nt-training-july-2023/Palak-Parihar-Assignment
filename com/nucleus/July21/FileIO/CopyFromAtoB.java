/**
 * 
 */
package com.nucleus.July21.FileIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 
 */
public class CopyFromAtoB {

	/**
	 * 
	 */
	public CopyFromAtoB() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File fileA = null;
		File fileB = null;
		FileInputStream fileInputStream = null;
		FileOutputStream fileOutputStream = null;

		try {
			fileA = new File("C:\\Users\\palak\\OneDrive\\Desktop\\Test.txt\\");
			fileB = new File("C:\\Users\\palak\\OneDrive\\Desktop\\TestB.txt\\");
			fileInputStream = new FileInputStream(fileA);
			fileOutputStream = new FileOutputStream(fileB);
			try {
				int i;
				while ((i = fileInputStream.read()) != -1) {
					fileOutputStream.write(i);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
		catch (FileNotFoundException e) {
			// TODO: handle exception
			System.out.println("Error : " + e.getMessage());
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		} finally {
			if(fileInputStream!=null) {
				fileInputStream.close();
			}
			if(fileOutputStream!=null) {
				fileOutputStream.close();
			}
		}

	}
}
