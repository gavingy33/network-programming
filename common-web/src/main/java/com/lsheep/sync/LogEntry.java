package com.lsheep.sync;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogEntry {

	private Writer writer;
	private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	public LogEntry(Writer writer) {
		this.writer = writer;
	}

	public synchronized void write(String message) throws Exception {
		writer.write(FORMAT.format(new Date()));
		writer.write(message);
		writer.write(new char[] { '\r', '\n' });
		writer.flush();
	}

	public static void main(String[] args) throws IOException {
		LogEntry logEntry = new LogEntry(new FileWriter("/Users/gaofeng/share/digest/log.txt"));
		for (int i = 0; i < 10; i++) {
			final int index = i;
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						logEntry.write(" " + index);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			thread.setPriority(1);
			thread.start();
		}
	}

}
