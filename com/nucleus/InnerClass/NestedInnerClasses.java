/**
 * 
 */
package com.nucleus.InnerClass;

/**
 * 
 */


class Outer{
	
	public void outermethod() {
		System.out.println("Outer");
		
		class LocalInner{
			public void show() {
				System.out.println("Local Inner CLasses");
			}
		}
		LocalInner inner = new LocalInner();
		inner.show();
	}
	
	class Inner{
		public void method() {
			System.out.println("Inner");
		}
	}
}



public class NestedInnerClasses {

	/**
	 * 
	 */
	public NestedInnerClasses() {
		// TODO Auto-generated constructor stub
	}
	
	
	static Outer outer = new Outer() {
		public void outermethod() {
			System.out.println("Anonymous class outer method");
		}
	};
	
	
	static Outer.Inner outerinner = new Outer().new Inner() {
		public void method() {
			System.out.println("Anonymous class extending Local Inner Class");
		}
	};

	/**
	 * @param args
	 */	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Nested Inner Classes
		Outer.Inner inner = new Outer().new Inner();
		inner.method();
		
		
		//Local Inner Class
		new Outer().outermethod();
		
		//Static Inner Class
		StaticInnerClass.InnerClass.show();
		
		
		
		outer.outermethod();
		
		outerinner.method();
	}

}
