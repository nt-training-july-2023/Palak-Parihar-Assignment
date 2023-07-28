/**
 * 
 */
package com.nucleus.July27;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

/**
 * 
 */
public class CopyFileAtoFileBReverse {

	/**
	 * 
	 */
	public CopyFileAtoFileBReverse() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		File file = null;
		BufferedWriter bufferedWriter = null;
		try {
			Stack<String> st = new Stack<String>();

			
			file = new File("C:\\Users\\palak\\OneDrive\\Desktop\\Test.txt\\");
			try {
				fileReader = new FileReader(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			bufferedReader = new BufferedReader(fileReader);

			try {
//				System.out.println(bufferedReader.readLine());
				while (bufferedReader.readLine() != null) {
					st.push(bufferedReader.readLine());
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
//			System.out.println(st.size());
			FileWriter fileWriter = null;
			try {
				fileWriter = new FileWriter("C:\\Users\\palak\\OneDrive\\Desktop\\TestB.txt\\");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 bufferedWriter = new BufferedWriter(fileWriter);
			while(!st.isEmpty()) {
				if(st.size() == 0) {
					break;
				}
				String a = st.pop();
				System.out.println(a);
				bufferedWriter.write(a);
				bufferedWriter.write(new char[] {'\n'});
			}
			
		} finally {
			// TODO: handle finally clause
			bufferedReader.close();
			bufferedWriter.close();
		}
		
		System.out.println("Success");
		
	}
	

}
