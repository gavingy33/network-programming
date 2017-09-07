package com.lsheep.http;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class HttpConnection {

	@Test
	public void test03() {
		try {
			URL url = new URL("http://localhost:8080/common-web/web");
			HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
			httpConnection.setRequestMethod("GET");
			System.out.println(httpConnection.usingProxy());
			httpConnection.setInstanceFollowRedirects(false);
			HttpMethod.printStream(httpConnection.getInputStream());
			HttpMethod.printStream(httpConnection.getErrorStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test02() {
		System.out.println(URLConnection.guessContentTypeFromName("LiYang.xml"));
		System.out.println(URLConnection.guessContentTypeFromName("LiYang.html"));
		System.out.println(URLConnection.guessContentTypeFromName("LiYang.json"));
		System.out.println(URLConnection.guessContentTypeFromName("LiYang.css"));
		System.out.println(URLConnection.guessContentTypeFromName("LiYang.jpg"));
		System.out.println(URLConnection.guessContentTypeFromName("LiYang.png"));
		System.out.println(URLConnection.guessContentTypeFromName("LiYang.data"));
		System.out.println(URLConnection.guessContentTypeFromName("LiYang.mp3"));
		System.out.println(URLConnection.guessContentTypeFromName("LiYang.mp4"));
	}

	@Test
	public void test01() {
		try {
			URL url = new URL("http://localhost:8080/common-web/web");

			HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();

			httpConnection.setDoOutput(true);
			httpConnection.addRequestProperty("Cookie", "name=SD9FWRDSG723FS");
			httpConnection.addRequestProperty("Cookie", "code=HASH_CODE");
			System.out.println(httpConnection.getRequestProperties());

			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(httpConnection.getOutputStream()));
			writer.write("Hello World");
			writer.flush();
			writer.close();

			System.out.println(httpConnection.getResponseCode());
			System.out.println(httpConnection.getResponseMessage());

			BufferedReader reader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			reader.close();

			Map<String, List<String>> headers = httpConnection.getHeaderFields();
			System.out.println(headers);
			System.out.println(httpConnection.getHeaderField(0));

			httpConnection.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
