/**
 * 
 */
package com.nucleus.July26.Lamda;

import java.util.Scanner;

/**
 * 
 */
public class FunctInterfaceMain {

	/**
	 * Program to replace vowels with '#'
	 */
	public FunctInterfaceMain() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		String stringToReplace = null;
		System.out.print("Enter String : ");
		
		try (// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in)) {
			stringToReplace = scanner.nextLine();
		}
		String original = new String(stringToReplace);
		ReplaceVowels replaceVowels = (String string) ->{
			string = string.replace('a', '#');
			string = string.replace('e', '#');
			string = string.replace('i', '#');
			string = string.replace('o', '#');
			string = string.replace('u', '#');
			string = string.replace('A', '#');
			string = string.replace('E', '#');
			string = string.replace('I', '#');
			string = string.replace('O', '#');
			string = string.replace('U', '#');
			System.out.println("Original String : "+ original +" Replaced String : "+ string);
			
		};
		replaceVowels.replace(stringToReplace);

	}

}
