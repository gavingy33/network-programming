package com.lsheep.inet;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MailBlackList {

	private static final String HOST = "sbl.spamhaus.org";

	public static void main(String[] args) {
		try {
			String hostname = "poczta.rybnik.pl";

			InetAddress inetAddress = InetAddress.getByName(hostname);

			byte[] address = inetAddress.getAddress();
			StringBuffer buffer = new StringBuffer();
			for (int index = 0; index < address.length; index++) {
				byte b = address[index];
				buffer.append(b < 0 ? 256 + b : b).append(".");
			}
			buffer.append(HOST);

			String queryHost = buffer.toString();
			System.out.println(queryHost);

			InetAddress blackAddress = InetAddress.getByName(queryHost);
			System.out.println(blackAddress);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

	}

}
