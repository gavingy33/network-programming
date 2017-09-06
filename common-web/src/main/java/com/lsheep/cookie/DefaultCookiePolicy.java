package com.lsheep.cookie;

import java.net.CookiePolicy;
import java.net.HttpCookie;
import java.net.URI;

public class DefaultCookiePolicy implements CookiePolicy {

	@Override
	public boolean shouldAccept(URI uri, HttpCookie cookie) {
		// TODO Auto-generated method stub
		return false;
	}

}
