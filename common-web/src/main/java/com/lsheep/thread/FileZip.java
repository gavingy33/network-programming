package com.lsheep.thread;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.GZIPOutputStream;

public class FileZip {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(5);

		File file = new File("/Users/gaofeng/work/opt/appdata/groovy/index");
		String[] files = file.list();
		for (String name : files) {
			Runnable task = new ZipTask(file.getPath() + "/" + name);
			executorService.submit(task);
		}
		executorService.shutdown();
		System.err.println("service start successful");
	}

}

class ZipTask implements Runnable {

	private String fileName;

	public ZipTask(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public void run() {
		try {
			if (fileName.endsWith(".gz")) {
				return;
			}
			File file = new File(fileName + ".gz");
			if (file.exists()) {
				return;
			}

			InputStream inputStream = new BufferedInputStream(new FileInputStream(fileName));
			GZIPOutputStream gzipOutputStream = new GZIPOutputStream(
					new BufferedOutputStream(new FileOutputStream(file)));

			byte[] buffer = new byte[1024];
			int size = inputStream.read(buffer, 0, buffer.length);
			while (size != -1) {
				gzipOutputStream.write(buffer, 0, size);
				size = inputStream.read(buffer, 0, buffer.length);
			}
			inputStream.close();

			gzipOutputStream.flush();
			gzipOutputStream.close();
			System.out.println(String.format("thred[%5d] %s zip down", Thread.currentThread().getId(), file));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}