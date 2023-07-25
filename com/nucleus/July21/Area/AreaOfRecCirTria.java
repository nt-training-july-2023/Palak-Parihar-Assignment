/**
 * 
 * This program calculates the Area of Rectangle Circle and Triangle
 * 
 * @author palak
 * 
 * 
 */
package com.nucleus.July21.Area;

class Rectangle {

	/**
	 * This method is use to calculate Area of Rectangle
	 * 
	 * @param length
	 * @param width
	 * @return
	 */
	public double AreaOfRec(double length, double width) {
		return length * width;
	}
}

class Circle {
	/**
	 * This method is use to calculate Area of Circle
	 * 
	 * @param radius
	 * @return
	 */
	public double AreaOfCir(double radius) {
		return 3.14 * radius * radius;
	}
}

class Triangle {
	/**
	 * This method is use to calculate Area of Triangle
	 * 
	 * @param height
	 * @param base
	 * @return
	 */
	public double AreaOfTriangle(double height, double base) {
		return 0.5 * height * base;
	}
}

/**
 * Class that calculates the Area of Rectangle, Circle and Triangle
 */
public class AreaOfRecCirTria {
	/**
	 * The application's entry point
	 *
	 * @param args an array of command-line arguments for the application
	 */
	public static void main(String[] args) {
		/**
		 * Creating an object of Rectangle class
		 */

		Rectangle rectangle = new Rectangle();

		/**
		 * Calculating the Area of rectangle and Storing the result in variable
		 * AreaOfrectangle which is of double type
		 */
		double AreaOfrectangle = rectangle.AreaOfRec(23.45, 3.45);
		/**
		 * Using Standard output stream for giving out
		 */
		System.out.println(AreaOfrectangle);

		/**
		 * Calculating the Area of Circle and Storing the result in variable
		 * AreaOfrectangle which is of double type
		 */
		Circle circle = new Circle();
		double AreaofCircle = circle.AreaOfCir(23.345);
		/**
		 * Using Standard output stream for giving out
		 */
		System.out.println(AreaofCircle);

		/**
		 * Calculating the Area of rectangle and Storing the result in variable
		 * AreaOfrectangle which is of double type
		 */
		Triangle triangle = new Triangle();
		double AreaOfTriangle = triangle.AreaOfTriangle(34.45, 78);
		/**
		 * Using Standard output stream for giving out
		 */
		System.out.println(AreaOfTriangle);
	}

}
