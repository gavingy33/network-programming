package com.lsheep.uri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class Sysin {

	public static void main(String[] args) {
		try {
			System.out.println("start entry");
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(System.in, Charset.forName("UTF-8")));
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
			}
			bufferedReader.close();
			System.out.println("end entry");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
