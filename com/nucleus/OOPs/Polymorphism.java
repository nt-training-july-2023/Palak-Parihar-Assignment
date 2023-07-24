/**
 * 
 */
package com.nucleus.OOPs;

/**
 * 
 */


abstract class Base{
	abstract double AreaOfTriangle(double b, double h);
}

public class Polymorphism extends Base{
	
	@Override
	double AreaOfTriangle(double b, double h) {
		// TODO Auto-generated method stub
		return (b*h)/2;
	}
	
	//Area of square
	public double Area(double a) {
		return a*a;
	}
	
	public double Area(double l, double b) {
		return l*b;
	}
	
	public static void main(String args[]) {
		Polymorphism p = new Polymorphism();
		System.out.println("Area of Square : "+ p.Area(12));
		System.out.println("Area of Rectangle : "+ p.Area(23, 67.9));
		
		
		System.out.println("Area of Triangle : "+p.AreaOfTriangle(23, 56));
	}
}
