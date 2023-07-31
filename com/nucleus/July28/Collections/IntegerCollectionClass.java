/**
 * 
 */
package com.nucleus.July28.Collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * 
 */
public class IntegerCollectionClass implements IntegerCollection {

	/**
	 * 
	 */

	CollectionsClass class1;

	public IntegerCollectionClass(CollectionsClass class1) {
		super();
		this.class1 = class1;
	}


	@Override
	public void addIntegerCollection(CollectionsClass class1, Integer value) {
		// TODO Auto-generated method stub
		class1.integerCollection.add(value);

	}

	
	//Printing elements of list in reverse order
	@Override
	public void iterateReverse(CollectionsClass class1) {
		// TODO Auto-generated method stub

		System.out.println("Printing List in Reverse Order");
		ListIterator<Integer> iterator = class1.integerCollection.listIterator(class1.integerCollection.size());
		while (iterator.hasPrevious()) {
			System.out.println(iterator.previous());
		}

	}
	
	@Override
	public void increaseSizeBy5(CollectionsClass class1, IntegerCollectionClass collection) {
		// TODO Auto-generated method stub
		Iterator<Integer> iterator = class1.integerCollection.iterator();
		boolean flag = false;
		while(iterator.hasNext()) {
			if(iterator.next() > 50) {
				flag = true;
				break;
			}
		}
		if(flag) {
			for(int i=0; i<5; i++) {
				collection.addIntegerCollection(class1, class1.integerCollection.size() + 23);
			}
		}
		
	}
	
	@Override
	public void displayElementLessThan60(CollectionsClass class1) {
		// TODO Auto-generated method stub
		Iterator<Integer> iterator = class1.integerCollection.iterator();
		while(iterator.hasNext()) {
			int k = iterator.next();
			if(k < 60) {
				System.out.println(" Elements less than 60 : " + k);
			}
		}
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		IntegerCollectionClass integerCollectionClass = new IntegerCollectionClass(
				new CollectionsClass(new ArrayList<Integer>()));
		
		int count = 20;
		for(int i=0; i<20; i++) {
			integerCollectionClass.addIntegerCollection(integerCollectionClass.class1, count+i+12);
		}
		
		integerCollectionClass.iterateReverse(integerCollectionClass.class1);
		
		integerCollectionClass.increaseSizeBy5(integerCollectionClass.class1, integerCollectionClass);
		
		integerCollectionClass.displayElementLessThan60(integerCollectionClass.class1);
	}

}
