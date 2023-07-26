/**
 * 
 */
package com.nucleus.July25;

/**
 * 
 */
public class ThreadState extends Thread {

	/**
	 * @param args
	 */

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("executing run method : " + Thread.currentThread().getState());
	}

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		ThreadState state = new ThreadState();
		System.out.println(state.getState());

		state.start();
		System.out.println(state.getState());
		
		state.join();
		if(!state.isAlive()) {
			System.out.println(state.getState());
		}

	}

}
