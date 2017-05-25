package com.liyang.learn.common.network;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

public class Print {

	public static void main(String[] args) throws IOException {
		InputStream inputStream = ClassLoader.getSystemResourceAsStream("");
		IOUtils.toString(inputStream);
		System.out.println();
	}

}
