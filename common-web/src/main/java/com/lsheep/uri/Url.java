package com.lsheep.uri;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;

import org.junit.Test;

public class Url {

	@Test
	public void test01() {
		try {
			URL url = new URL("https://lsheep.com/login/index.html?username=gavingy&pwd=123#01");
			System.out.println(url.getProtocol());
			System.out.println(url.getHost());
			System.out.println(url.getPath());
			System.out.println(url.getQuery());
			System.out.println(url.getDefaultPort());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test02() {
		try {
			URL url = new URL("https", "lsheep.com", 1030, "/login/index.html");
			System.out.println(url);
			System.out.println(url.getProtocol());
			System.out.println(url.getPort());
			System.out.println(url.getDefaultPort());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test03() {
		try {
			URL url = new URL("http://lsheep.com/login/index.html");
			System.out.println(new URL(url, "entry.html"));
			System.out.println(new URL(url, "/entry.html"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test04() {
		File file = new File("/Users/gaofeng/work/utils.js");
		System.out.println(file.toURI());
	}

	@Test
	public void test05() {
		try {
			System.out.println(getClass().getClassLoader().getResource("config/code.json"));
			System.out.println(ClassLoader.getSystemResource("config/code.json"));

			Enumeration<URL> enumeration = ClassLoader.getSystemResources("template/");
			while (enumeration.hasMoreElements()) {
				System.out.println(enumeration.nextElement());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
