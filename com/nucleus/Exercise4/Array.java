package com.nucleus.Exercise4;

import java.util.Arrays;

public class Array {

	public Array() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String args[]) {
		//Add two matrices
		int a[][]={{1,3,4},{2,4,3},{3,4,5}};    
		int b[][]={{1,3,4},{2,4,3},{1,2,4}};
		
		int c[][] = new int[a.length][a[0].length];
		
		for(int i=0; i<a.length; i++) {
			for(int j=0; j<a.length; j++) {
				c[i][j] = a[i][j] + b[i][j];
			}
		}
		for(int i=0; i<a.length; i++) {
			for(int j=0; j<a.length; j++) {
				System.out.print(c[i][j]+" ");
			}
			System.out.println();
		}
		
		
		//Rotate Array b 2 positions
		int arr[] = {1, 2, 3, 4, 5};
		int n = arr.length;
		int d = 2;
		 int temp[] = new int[n];
		 
		    // Keeping track of the current index
		    // of temp[]
		    int k = 0;
		 
		    // Storing the n - d elements of
		    // array arr[] to the front of temp[]
		    for (int i = d; i < n; i++) {
		        temp[k] = arr[i];
		        k++;
		    }
		 
		    // Storing the first d elements of array arr[]
		    //  into temp
		    for (int i = 0; i < d; i++) {
		        temp[k] = arr[i];
		        k++;
		    }
		 
		    // Copying the elements of temp[] in arr[]
		    // to get the final rotated array
		    for (int i = 0; i < n; i++) {
		        arr[i] = temp[i];
		    }
		    
		    for(int e : arr) {
		    	System.out.print(e+" ");
		    }
	}
}
