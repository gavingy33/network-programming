package com.lsheep.network;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class Rocketmq {

	public static void main(String[] args) {
		try {
			InetAddress inetAddress = InetAddress.getByName("47.94.249.222");
			SocketAddress socketAddress = new InetSocketAddress(inetAddress, 22);

			Socket socket = new Socket();
			socket.setSoTimeout(2 * 1000);
			socket.connect(socketAddress);
			socket.getOutputStream();

			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
