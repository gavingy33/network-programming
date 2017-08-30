package com.lsheep.uri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.nio.charset.Charset;

import org.springframework.util.StringUtils;

public class Auth extends Authenticator {

	private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		String userName = null;
		char[] password = null;
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in, DEFAULT_CHARSET));
			do {
				System.err.print("username:");
				userName = bufferedReader.readLine();
			} while (StringUtils.isEmpty(userName));
			do {
				System.err.print("password:");
				String pwdString = bufferedReader.readLine();
				if (StringUtils.isEmpty(pwdString)) {
					continue;
				}
				password = pwdString.toCharArray();
			} while (password == null || password.length == 0);
//			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new PasswordAuthentication(userName, password);
	}

	public static void main(String[] args) {
		try {
			Authenticator.setDefault(new Auth());
			URL url = new URL("http://127.0.0.1:8080/spring-security-http-basic-authentication/secured/mypage");

			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(url.openStream(), DEFAULT_CHARSET));
			for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
				System.out.println(line);
			}
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
