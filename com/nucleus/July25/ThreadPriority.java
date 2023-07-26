/**
 * 
 */
package com.nucleus.July25;

/**
 * 
 */

public class ThreadPriority extends Thread {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Thread Id : " + Thread.currentThread().getId() + " "+ " and Priority "+ Thread.currentThread().getPriority());
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThreadPriority priority1 = new ThreadPriority();
		priority1.setPriority(10);
		
		
		ThreadPriority priority2 = new ThreadPriority();
		priority2.setPriority(2);
		
		ThreadPriority priority3 = new ThreadPriority();
		priority3.setPriority(1);
		
		ThreadPriority priority4 = new ThreadPriority();
		priority4.setPriority(5);
		
		priority4.start();
		priority2.start();
		priority3.start();
		priority1.start();

	}

}
