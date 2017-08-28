package com.lsheep.thread;

public class Await {

	public static void main(String[] args) {
		Wait wait = new Wait();

		Thread thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("thread 1 start");
				try {
					synchronized (wait) {
						System.out.println("thread 1 get mait");
						wait.wait(1000 * 5);
						System.out.println("thread 1 get");
					}
				} catch (InterruptedException e) {
					System.out.println("thread 1 interrupted");
				}
				System.out.println("thread 1 end");
			}
		});
		thread1.start();

		try {
			Thread.sleep(1000 * 2);
		} catch (InterruptedException e) {
		}
		synchronized (wait) {
			System.out.println("main thread get mait");
//			thread1.interrupt();
			wait.notify();
		}
	}

}

class Wait {

}
