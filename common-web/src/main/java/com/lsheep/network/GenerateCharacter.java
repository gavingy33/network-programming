package com.lsheep.network;

import java.io.IOException;
import java.io.OutputStream;

public class GenerateCharacter {

	static final int start = 33;
	static final int printSize = 94;
	static final int lineSize = 72;

	public static void write(OutputStream outputStream) throws IOException {
		int first = start;
		while (true) {
			for (int index = 0; index < lineSize; index++) {
				outputStream.write((first - start + index) % printSize + start);
			}
			outputStream.write('\r');
			outputStream.write('\n');
			first = (first - start + 1) % printSize + start;
		}
	}

	public static void main(String[] args) throws IOException {
		write(System.out);
	}
}
