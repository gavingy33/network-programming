package com.lsheep.uri;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;

public class Uri {

	@Test
	public void test06() {
		URI uri = URI.create("https://lsheep.com/Java∂.html");
		System.out.println(uri.toString());
		System.out.println(uri.toASCIIString());
	}

	@Test
	public void test05() {
		URI a = URI.create("https://lsheep.com/index.html");
		URI b = URI.create("https://lsheep.com");
		System.out.println(b.relativize(a));
	}

	@Test
	public void test04() {
		URI absolute = URI.create("https://lsheep.com/");
		URI relative = URI.create("/index.html");
		URI uri = absolute.resolve(relative);
		System.out.println(uri.getPath());
		System.out.println(uri.getRawPath());
		System.out.println(uri);
	}

	@Test
	public void test01() {
		try {
			URI uri = new URI("https://admin:pwd@lsheep.com/account/Java%20I%2FO.html?name=gavingy#003");
			System.out.println(uri.getScheme());
			System.out.println();
			System.out.println(uri.getAuthority());
			System.out.println(uri.getRawAuthority());
			System.out.println();
			System.out.println(uri.getFragment());
			System.out.println(uri.getRawFragment());
			System.out.println();
			System.out.println(uri.getHost());
			System.out.println();
			System.out.println(uri.getPath());
			System.out.println(uri.getRawPath());
			System.out.println();
			System.out.println(uri.getQuery());
			System.out.println(uri.getRawQuery());
			System.out.println();
			System.out.println(uri.getUserInfo());
			System.out.println(uri.getRawUserInfo());
			System.out.println();
			System.out.println(uri.getRawSchemeSpecificPart());
			System.out.println(uri.isAbsolute());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test02() {
		URI uri = URI.create("https://admin:pwd@lsheep.com/account/Java%20I%2FO.html?name=gavingy#003");
		System.out.println(uri.getScheme());
		System.out.println(uri.getFragment());
		System.out.println(uri.getHost());
		System.out.println(uri.getPath());
		System.out.println(uri.getQuery());
		System.out.println(uri.getUserInfo());
		System.out.println(uri.getRawSchemeSpecificPart());
		System.out.println(uri.isAbsolute());
	}

	@Test
	public void test03() {
		System.out.println(URI.create("https://lsheep.com/account.html").isOpaque());
		System.out.println(URI.create("mail:gavingy@lsheep.com").isOpaque());
	}
}
