package com.lsheep.thread;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogEntry {

	private static List<String> entries = new ArrayList<>();
	private static List<Thread> threads = new ArrayList<>();

	public void registerThread(Thread thread) {
		threads.add(thread);
	}

	public static void main(String[] args) throws IOException {
		LogEntry logEntry = new LogEntry();
		for (int i = 0; i < 10; i++) {
			Thread thread = new Thread(new ProcessLog(entries, i));
			logEntry.registerThread(thread);
			thread.start();
		}

		try {
			Thread.sleep(1000 * 1);
		} catch (InterruptedException e1) {
		}

		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader("/Users/gaofeng/share/digest/log.txt"));
			String line = bufferedReader.readLine();
			while (line != null) {
				synchronized (entries) {
					entries.add(line);
					entries.notifyAll();
				}
				line = bufferedReader.readLine();
			}
			bufferedReader.close();
			for (Thread thread : threads) {
				thread.interrupt();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}

class ProcessLog implements Runnable {

	private List<String> entries;
	private Integer index;

	public ProcessLog(List<String> entries, Integer index) {
		this.entries = entries;
		this.index = index;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (entries) {
				while (entries.isEmpty()) {
					try {
						entries.wait();
					} catch (InterruptedException e) {
						return;
					}
				}
				String message = entries.get(0);

				System.out.println(String.format("%2s %s", index, message));
				entries.remove(message);
			}
		}
	}

}
