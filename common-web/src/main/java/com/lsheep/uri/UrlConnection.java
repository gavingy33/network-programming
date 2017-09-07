package com.lsheep.uri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import org.junit.Test;

public class UrlConnection {

	@Test
	public void test02() {
		try {
			URL url = new URL("http://lsheep.com/index.html");
			URLConnection connection = url.openConnection();

			connection.setUseCaches(true);
			connection.setIfModifiedSince(new Date().getTime());
			connection.setConnectTimeout(0);
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			for (String line = reader.readLine(); line != null; line = reader.readLine()) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test01() {
		try {
			URL url = new URL("http://lsheep.com");
			URLConnection connection = url.openConnection();

			System.out.println(connection.getContentType());
			System.out.println(connection.getConnectTimeout());
			System.out.println(connection.getContentEncoding());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
