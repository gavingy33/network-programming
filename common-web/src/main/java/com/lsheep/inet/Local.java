package com.lsheep.inet;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Local {

	
	
	public static void main(String[] args) {
		try {
			InetAddress inetAddress =  InetAddress.getLocalHost();
			System.out.println(inetAddress);
			System.out.println(inetAddress.isAnyLocalAddress());
			System.out.println(inetAddress.isLinkLocalAddress());
			System.out.println(inetAddress.isLoopbackAddress());
			System.out.println(inetAddress.isMCGlobal());
			System.out.println(inetAddress.isMCLinkLocal());
			System.out.println(inetAddress.isMCNodeLocal());
			System.out.println(inetAddress.isMCOrgLocal());
			System.out.println(inetAddress.isMulticastAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
	}
}
