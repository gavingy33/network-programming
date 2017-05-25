package com.lsheep.common.file;

import java.io.IOException;
import java.io.InputStream;

public class ReadClasspathFile {

	public String readFile(String filepath) throws IOException {
		InputStream inputStream = ClassLoader.getSystemResourceAsStream(filepath);
		int character;
		StringBuffer buffer = new StringBuffer();
		while ((character = inputStream.read()) != -1) {
			buffer.append((char) character);
		}
		return buffer.toString();
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(new ReadClasspathFile().readFile("config/code.json"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
 