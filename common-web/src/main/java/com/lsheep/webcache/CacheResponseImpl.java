package com.lsheep.webcache;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.CacheResponse;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CacheResponseImpl extends CacheResponse {

	private byte[] data = new byte[] { 72, 101, 108, 108, 111, 32, 87, 111, 114, 108, 100, 13, 10 };
	private final InputStream inputStream = new ByteArrayInputStream(data);

	@Override
	public Map<String, List<String>> getHeaders() throws IOException {
		System.err.println("enter CacheResponse.getHeaders()");
		Map<String, List<String>> map = new HashMap<>();
		map.put("Accept-Ranges", Arrays.asList(new String[] { "bytes" }));
		map.put("Content-Length", Arrays.asList(new String[] { "247" }));
		map.put("Content-Type", Arrays.asList(new String[] { "text/html" }));
		map.put("ETag", Arrays.asList(new String[] { "\"595ef6b6-f7\"" }));
		map.put("Server", Arrays.asList(new String[] { "nginx/1.12.0" }));
		return Collections.unmodifiableMap(map);
	}

	@Override
	public InputStream getBody() throws IOException {
		System.err.println("enter CacheResponse.getBody()");
		return inputStream;
	}

	public static void main(String[] args) {
		System.out.println(Arrays.toString("Hello World\r\n".getBytes()));
	}

}
