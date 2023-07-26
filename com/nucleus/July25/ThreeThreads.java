/**
 * 
 */
package com.nucleus.July25;

/**
 * 
 */
public class ThreeThreads extends Thread {
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(Thread.currentThread().getName());
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThreeThreads thread1 = new ThreeThreads();
		thread1.setName("Thread 1");
		
		ThreeThreads thread2 = new ThreeThreads();
		thread2.setName("Thread 2");
		
		ThreeThreads thread3 = new ThreeThreads();
		thread3.setName("Thread 3");
		
		
		thread1.start();
		thread2.start();
		thread3.start();

	}

}
