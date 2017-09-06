package com.lsheep.uri;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import org.junit.Test;

public class UrlConnection {

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
