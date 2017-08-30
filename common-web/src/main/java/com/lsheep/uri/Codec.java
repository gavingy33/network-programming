package com.lsheep.uri;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;

import org.junit.Test;

public class Codec {

	@Test
	public void test01() {
		try {
			String[] codes = new String[] { "UTF-8", "GBK", "UTF-16", "GB2312" };
			for (String encode : codes) {
				URI uri = URI.create(URLEncoder.encode("https://lsheep.com/Java I/O.html", encode));
				System.out.println(uri);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
