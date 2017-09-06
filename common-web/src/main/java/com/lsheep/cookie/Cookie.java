package com.lsheep.cookie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class Cookie {

	@Test
	public void test01() {

		HttpCookie httpCookie = new HttpCookie("ID", "000001");
		httpCookie.setPath("/account");
		httpCookie.setHttpOnly(true);
		httpCookie.setDomain("lsheep.com");

		CookieManager cookieManager = new CookieManager();
		cookieManager.setCookiePolicy(new CookiePolicy() {
			@Override
			public boolean shouldAccept(URI uri, HttpCookie cookie) {
				return false;
			}
		});

		CookieHandler.setDefault(cookieManager);
	}

	@Test
	public void test02() {
		CookieManager cookieManager = new CookieManager();
		CookieStore cookieStore = cookieManager.getCookieStore();

		URI uri = URI.create("https://lsheep.com");
		HttpCookie httpCookie = new HttpCookie("ID", "000001");
		cookieStore.add(uri, httpCookie);
		cookieStore.getCookies();
		cookieStore.getURIs();
	}

	public static void main(String[] args) {
		// CookieStore cookieStore = new RedisCookieStore();
		// CookiePolicy cookiePolicy = new DefaultCookiePolicy();
		// CookieManager cookieManager = new CookieManager(cookieStore,
		// cookiePolicy);
		// CookieManager.setDefault(cookieManager);

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			URL url = new URL("https://lsheep.com/index.html");
			URLConnection connection = url.openConnection();

			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
			}
			System.err.println(connection.getContentType());
			System.err.println(connection.getContentLength());
			System.err.println(connection.getContentEncoding());
			System.err.println(dateFormat.format(new Date(connection.getDate())));
			System.err.println(dateFormat.format(new Date(connection.getLastModified())));
			System.err.println(dateFormat.format(new Date(connection.getExpiration())));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
