package com.lsheep.inet;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

import org.junit.Test;

public class Network {

	@Test
	public void testGetByName() {
		try {
			NetworkInterface networkInterface = NetworkInterface.getByName("en0");
			System.out.println(networkInterface);
			System.out.println(networkInterface.getDisplayName());
			System.out.println(networkInterface.getIndex());
			System.out.println(networkInterface.getName());

		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetByInet() {
		try {
			InetAddress inetAddress = InetAddress.getByName("127.0.0.1");

			NetworkInterface networkInterface = NetworkInterface.getByInetAddress(inetAddress);
			System.out.println(networkInterface);
			System.out.println(networkInterface.getDisplayName());
			System.out.println(networkInterface.getName());
			System.out.println(networkInterface.getIndex());
		} catch (UnknownHostException | SocketException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetAll() {
		try {
			Enumeration<NetworkInterface> enumeration = NetworkInterface.getNetworkInterfaces();
			while (enumeration.hasMoreElements()) {
				NetworkInterface networkInterface = enumeration.nextElement();
				System.out.println(networkInterface);
				System.out.println(networkInterface.getName());
				Enumeration<InetAddress> addressEnum = networkInterface.getInetAddresses();
				while (addressEnum.hasMoreElements()) {
					InetAddress inetAddress = addressEnum.nextElement();
					System.out.println("+++++  " + inetAddress.getHostName());
				}
				System.out.println("-------------------------");
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}

	}

}
