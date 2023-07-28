/**
 * @author palak
 */
package com.nucleus.July26.Lamda;

/**
 * 
 */

public class CalculatingArea {

	class Rectangle implements Shape {
		 double length;
		double breadth;

		public Rectangle(double length, double breadth) {
			// TODO Auto-generated constructor stub
			this.length = length;
			this.breadth = breadth;
		}

		@Override
		public double area() {
			// TODO Auto-generated method stub
			return length * breadth;
		}
	}

	class Circle implements Shape {
		double radius;

		public Circle(double radius) {
			this.radius = radius;
		}

		@Override
		public double area() {
			// TODO Auto-generated method stub
			return Math.PI * radius * radius;
		}
	}

	class Square implements Shape {
		double side;

		public Square(double side) {
			this.side = side;
		}

		@Override
		public double area() {
			// TODO Auto-generated method stub
			return side * side;
		}
	}

	class Sphere implements Shape {
		double radius;

		public Sphere(double radius) {
			super();
			this.radius = radius;
		}

		@Override
		public double area() {
			// TODO Auto-generated method stub
			return Math.PI * radius * radius * radius;
		}
	}

	class Cylinder implements Shape {
		double radius;
		double height;

		public Cylinder(double radius, double height) {
			super();
			this.radius = radius;
			this.height = height;
		}

		@Override
		public double area() {
			// TODO Auto-generated method stub
			return 2 * Math.PI * radius * (radius + height);
		}
	}
	
	class Cube implements Shape{
		double side;

		public Cube(double side) {
			super();
			this.side = side;
		}
		
		@Override
		public double area() {
			// TODO Auto-generated method stub
			return Math.pow(side, 3);
		};
	}

	public static void main(String[] args) {
		Square square = new CalculatingArea().new Square(12);
		System.out.println("Area of Square : " + square.area());
		
		Circle circle = new CalculatingArea().new Circle(34.5);
		System.out.println("Area of Circle : " + circle.area());
		
		Rectangle rectangle = new CalculatingArea().new Rectangle(356.56, 2.3);
		System.out.println("Area of Rectangle : " + rectangle.area());
		
		Cube cube = new CalculatingArea().new Cube(34.44);
		System.out.println("Area of Cube : " + cube.area());
		
		Sphere sphere = new CalculatingArea().new Sphere(99);
		System.out.println("Area of Sphere : " + sphere.area());
		
		Cylinder cylinder = new CalculatingArea().new Cylinder(45, 11);
		System.out.println("Area of Cylinder : "+cylinder.area());
		
	}

}
