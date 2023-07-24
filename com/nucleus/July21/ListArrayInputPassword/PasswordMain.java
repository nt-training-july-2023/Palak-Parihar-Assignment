/**
 * 
 */
package com.nucleus.July21.ListArrayInputPassword;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 */
public class PasswordMain {

	/**
	 * 
	 */
	public PasswordMain() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";
		
		Pattern pattern = Pattern.compile(regex);
		
		System.out.println("Please enter your password");
		Scanner sc = new Scanner(System.in);
		
		String password =  null;
		try {
			password = sc.next();
			
			Matcher matcher = pattern.matcher(password);
			
			if(!matcher.matches()) {
				throw new InvalidPasswordException("Invalid Password Encountered");
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

}
