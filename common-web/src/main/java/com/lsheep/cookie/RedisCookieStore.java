package com.lsheep.cookie;

import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.util.List;

public class RedisCookieStore implements CookieStore {

	@Override
	public void add(URI uri, HttpCookie cookie) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<HttpCookie> get(URI uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HttpCookie> getCookies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<URI> getURIs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(URI uri, HttpCookie cookie) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll() {
		// TODO Auto-generated method stub
		return false;
	}

}
