/**
 * 
 */
package com.nucleus.Final;

/**
 * 
 */

final class Sapiens{
	
}

class HomoErectus{
	public final void method() {
		System.out.println("Homo Erectus Method");
	}
}

public class FFinal {

	/**
	 * 
	 */
	final String Animal;
	final String type = "Mammal";
	
	public FFinal(String Animal) {
		// TODO Auto-generated constructor stub
		this.Animal = Animal;
		
	}
	

	@Override
	public String toString() {
		return "FFinal [Animal=" + Animal + ", type=" + type + "]";
	}

	public String getAnimal() {
		return Animal;
	}



	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FFinal cat = new FFinal("Cat");
		FFinal dog = new FFinal("Dog");
		
		System.out.println(cat);
		System.out.println(dog);
	}

}
