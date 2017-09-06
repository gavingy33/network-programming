package com.lsheep.webcache;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.CacheRequest;

public class CacheRequestImpl extends CacheRequest {

	private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

	@Override
	public OutputStream getBody() throws IOException {
		System.err.println("enter CacheRequest.getBody()");
		return outputStream;
	}

	@Override
	public void abort() {
		System.err.println("enter CacheRequest.abort()");
		outputStream.reset();
	}

}
