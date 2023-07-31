/**
 * 
 */
package com.nucleus.July28.Collections;

import java.util.LinkedHashSet;

/**
 * 
 */
public class StringCollectionCLass implements StringCollectionInterface {

	LinkedHashSet<String> linkedHashSet;

	public StringCollectionCLass(LinkedHashSet<String> collections) {
		super();
		this.linkedHashSet = collections;
	}

	/**
	 * 
	 */
	public StringCollectionCLass() {
		// TODO Auto-generated constructor stub
	}

//	@Override
//	public void addElement(StringCollection collection, LinkedHashSet<StringCollection> set) {
//		// TODO Auto-generated method stub
//		set.add(collection);
//
//	}
	
	@Override
	public void addElement(String collection, LinkedHashSet<String> set) {
		

	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StringCollectionCLass cLass = new StringCollectionCLass(new LinkedHashSet<String>());
		
		for(int i=0; i<20; i++) {
			cLass.linkedHashSet.add("Hello" + i);
		}
		System.out.println(cLass.linkedHashSet);

	}

	
}
