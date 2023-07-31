/**
 * 
 */
package com.nucleus.July28.Collections;

import java.util.Comparator;

/**
 * 
 */

public class StringCollection implements Comparator<String>{

	String value;

	public StringCollection(String value) {
		super();
		this.value = value;
	}

	/**
	 * 
	 */
	public StringCollection() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compare(String o1, String o2) {
		// TODO Auto-generated method stub
		return o1.compareTo(o2);
	}

}
