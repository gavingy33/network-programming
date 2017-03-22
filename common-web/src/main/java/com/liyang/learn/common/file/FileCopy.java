package com.liyang.learn.common.file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

public class FileCopy {

	public static void copyByChannel(String source, String target) {
		try (FileChannel sourceChannel = new FileInputStream(source).getChannel();
				FileChannel targetChannel = new FileOutputStream(target).getChannel()) {
			sourceChannel.transferTo(0, sourceChannel.size(), targetChannel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void copyByStream(String source, String target) {
		try (InputStream sourceStream = new FileInputStream(source);
				OutputStream targetStream = new FileOutputStream(target)) {
			byte[] buffer = new byte[10];
			int offset;
			while ((offset = sourceStream.read(buffer, 0, buffer.length)) != -1) {
				targetStream.write(buffer, 0, offset);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		FileCopy.copyByStream("/Users/gaofeng/share/source_1.txt", "/Users/gaofeng/share/target_1.txt");
		FileCopy.copyByChannel("/Users/gaofeng/share/source_2.txt", "/Users/gaofeng/share/target_2.txt");
	}

}
