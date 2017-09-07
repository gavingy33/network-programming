package com.lsheep.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;

public class HttpMethod {

	public static void printStream(InputStream inputStream) throws IOException {
		if (inputStream == null) {
			System.out.println("inputStream is null");
			return;
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		String line = null;
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}
		reader.close();
	}

	@Test
	public void testGet() {
		try {
			URL url = new URL("http://localhost:8080/common-web/web");

			HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
			httpConnection.setRequestMethod("GET");
			printStream(httpConnection.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testPost() {
		try {
			URL url = new URL("http://localhost:8080/common-web/web");

			HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
			httpConnection.setRequestMethod("POST");
			printStream(httpConnection.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testPut() {
		try {
			URL url = new URL("http://localhost:8080/common-web/web");

			HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
			httpConnection.setRequestMethod("PUT");
			printStream(httpConnection.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDelete() {
		try {
			URL url = new URL("http://localhost:8080/common-web/web");

			HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
			httpConnection.setRequestMethod("DELETE");
			printStream(httpConnection.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testOptions() {
		try {
			URL url = new URL("http://localhost:8080/common-web/web");

			HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
			httpConnection.setRequestMethod("OPTIONS");
			printStream(httpConnection.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
