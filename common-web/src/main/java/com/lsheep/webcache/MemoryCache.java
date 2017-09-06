package com.lsheep.webcache;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.CacheRequest;
import java.net.CacheResponse;
import java.net.ResponseCache;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

public class MemoryCache extends ResponseCache {

	// private static final Map<URI, CacheResponse> CACHE = new
	// ConcurrentHashMap<>();

	@Override
	public CacheResponse get(URI uri, String rqstMethod, Map<String, List<String>> rqstHeaders) throws IOException {
		System.err.println("enter MemoryCache.get()");
		return new CacheResponseImpl();
	}

	@Override
	public CacheRequest put(URI uri, URLConnection conn) throws IOException {
		System.err.println("enter MemoryCache.put()");
		return new CacheRequestImpl();
	}

	public static void main(String[] args) {
		ResponseCache responseCache = new MemoryCache();
		ResponseCache.setDefault(responseCache);

		while (true) {
			try {
				URL url = new URL("https://lsheep.com/index.html");
				URLConnection connection = url.openConnection();
				// connection.getHeaderFields();
				connection.setUseCaches(true);

				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));
				String line = null;
				while ((line = bufferedReader.readLine()) != null) {
					System.out.println(line);
				}
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(10 * 1000);
			} catch (InterruptedException e) {
			}
		}
	}

}
