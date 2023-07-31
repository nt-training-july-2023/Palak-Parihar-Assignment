/**
 * 
 */
package com.nucleus.July28;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 
 */
public class Threshold {

	/**
	 * 
	 */
	Map<Integer, String> map = null;

	public Threshold(int threshold) {
		// TODO Auto-generated constructor stub
		map = new HashMap<Integer, String>(threshold);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int threshold = 12;
		Threshold thresholdObject = new Threshold(threshold);

		int count = 1;
		try (Scanner scanner = new Scanner(System.in)) {
			int j = 15;
			while (j-- != 0) {
				System.out.println("Enter value");
				String value = scanner.nextLine();
				thresholdObject.map.put(count, value);
				
				System.out.println(thresholdObject.map);
				if (thresholdObject.map.size() > 12) {
					System.out.println("Erasing map");
					for (Map.Entry<Integer, String> entry : thresholdObject.map.entrySet()) {
						System.out.println("Key : " + entry.getValue() + " , " + "Value : " + entry.getValue());
					}
					thresholdObject.map.clear();
				}
				count++;
			}
		}

	}
}
