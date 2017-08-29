package com.lsheep.inet;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Inet {

	public static void main(String[] args) {
		// InetAddress inetAddress;
		try {
			// inetAddress = InetAddress.getByName("lsheep.com");
			// System.out.println(inetAddress);
			//
			// inetAddress = InetAddress.getByName("118.89.196.241");
			// System.out.println(inetAddress);

			InetAddress[] addresses = InetAddress.getAllByName("118.89.196.241");
			for (InetAddress address : addresses) {
				System.out.println(address.getHostName());
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}
