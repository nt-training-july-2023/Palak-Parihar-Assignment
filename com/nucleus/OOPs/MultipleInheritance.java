/**
 * 
 */
package com.nucleus.OOPs;

/**
 * 
 */

interface Mammal{
	static void method() {
		System.out.println("Mammal");
	}
}

class Herbivores{
	public Herbivores() {
		// TODO Auto-generated constructor stub
		System.out.println("Herbivores");
	}
}

class Animal extends Herbivores implements Mammal{
	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Animal [name=" + name + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
}
public class MultipleInheritance {

	/**
	 * 
	 */
	public MultipleInheritance() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Animal animal = new Animal();
		animal.setName("Girrafe");
		Mammal.method();
		System.out.println(animal);
	}

}
