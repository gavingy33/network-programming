package com.lsheep.file;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class ClassicFile {

	public static void main(String[] args) {
		try {
			File file = new File("/Users/gaofeng/filesys");
			file.mkdirs();

			RandomAccessFile accessFile = new RandomAccessFile(file, "rw");
			accessFile.seek(0);
			accessFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
