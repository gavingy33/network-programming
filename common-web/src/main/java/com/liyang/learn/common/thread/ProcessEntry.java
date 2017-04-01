package com.liyang.learn.common.thread;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProcessEntry extends Thread {

	private static List<String> entries = new ArrayList<>();

	@Override
	public void run() {
		synchronized (entries) {
			while (entries.isEmpty()) {
				try {
					entries.wait();
				} catch (InterruptedException e) {
					return;
				}
			}
			String entry = entries.get(entries.size() - 1);
			entries.remove(entries.size() - 1);
			System.out.println(this + " " + entry);
		}
	}

	public static void main(String[] args) {
		LoadFile loadFile = new LoadFile(args[0], entries);
		for (int i = 0; i < 10; i++) {
			Thread thread = new ProcessEntry();
			loadFile.register(thread);
			thread.start();
		}
		loadFile.read();
	}

}

class LoadFile {

	private String path;
	private List<String> entries;
	private List<Thread> threads;

	public LoadFile(String path, List<String> entries) {
		this.path = path;
		this.entries = entries;
	}

	public void register(Thread thread) {
		if (threads == null) {
			threads = new ArrayList<>();
		}
		threads.add(thread);
	}

	public void read() {
		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
			String line;
			while ((line = reader.readLine()) != null) {
				synchronized (entries) {
					entries.add(0, line);
					entries.notifyAll();
				}
			}
			for (Thread thread : threads) {
				thread.interrupt();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}