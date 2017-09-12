package com.lsheep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public abstract class StreamUtil {

	public static final Charset UTF_8 = Charset.forName("UTF-8");

	public static void printStream(InputStream inputStream) throws IOException {
		if (inputStream == null) {
			return;
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		String line = null;
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}
	}

}
