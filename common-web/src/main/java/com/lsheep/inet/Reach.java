package com.lsheep.inet;

import java.io.IOException;
import java.net.InetAddress;

public class Reach {

	public static void main(String[] args) {
		try {
			InetAddress inetAddress = InetAddress.getByAddress(new byte[] { 118, 89, (byte) 196, (byte) 241 });
			System.out.println(inetAddress.isReachable(10 * 1));
			
			System.out.println(inetAddress.getAddress().length);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
