package com.lsheep.uri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.junit.Test;

public class Connection {

	@Test
	public void test01() {
		try {
			URL url = new URL("https://lsheep.com/account/login/login.html");
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(url.openStream(), Charset.forName("UTF-8")));
			for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
				System.out.println(line);
			}
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test02() {
		try {
			URL url = new URL("https://lsheep.com/account/login/login.html");
			Object object = url.getContent();
			System.out.println(object.getClass());
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader((InputStream) object, Charset.forName("UTF-8")));
			for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
				System.out.println(line);
			}
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test03() {
		try {
			System.out.println(new URL("https://lsheep.com/index.jpg").getContent().getClass());
			System.out.println(new URL("https://lsheep.com/index.html").getContent().getClass());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test04() {
		try {
			URL url = new URL("https://lsheep.com/index.html");
			Object object = url.getContent(new Class[] { String.class, Reader.class, InputStream.class });
			System.out.println(object.getClass());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
