/**
 * 
 */
package com.nucleus.OOPs;

/**
 * 
 */


class ProgramminLanguage{
	
	String name;
	
	public ProgramminLanguage() {
		// TODO Auto-generated constructor stub
		System.out.println("Called");
	}
}

class Java extends ProgramminLanguage{
	String name = "Java";
	public Java() {
		// TODO Auto-generated constructor stub
		System.out.println(name);
	}
}

class CPP extends ProgramminLanguage{
	String name = "CPP";
}

class Python extends ProgramminLanguage{
	String name = "Python";
}

public class Hierarichical {

	/**
	 * 
	 */
	public Hierarichical() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new Python().name);
		new Java();
	}

}
