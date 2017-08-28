package com.lsheep.thread;

public class Interrupt {

	public static void main(String[] args) {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("thread start");
				try {
					Thread.sleep(1000 * 10);
				} catch (InterruptedException e) {
					e.printStackTrace();
					System.out.println("thread interrupted ...");
				}
				System.out.println("thread end");
			}
		});
		thread.start();
		thread.interrupt();
	}

}
