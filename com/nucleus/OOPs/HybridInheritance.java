package com.nucleus.OOPs;


class DataStructure{
	public DataStructure() {
		// TODO Auto-generated constructor stub
		System.out.println("Data Structure");
	}
}

class List extends DataStructure{
	public List() {
		// TODO Auto-generated constructor stub
	  System.out.println("List");
	}
}

class ArrayList extends List{
	public ArrayList() {
		// TODO Auto-generated constructor stub
		System.out.println("Array List");
	}
}

class LinkedList extends List{
	public LinkedList() {
		System.out.println("Linked List");
	}
}


public class HybridInheritance {

	public HybridInheritance() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String args[]) {
		new ArrayList();
	}

}
