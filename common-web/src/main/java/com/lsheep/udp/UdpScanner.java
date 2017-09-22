package com.lsheep.udp;

import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpScanner {

	public static void main(String[] args) {
		for (int port = 1025; port < 65536; port++) {
			DatagramSocket socket = null;
			try {
				socket = new DatagramSocket(port);
			} catch (SocketException e) {
				System.out.println(String.format("port [%d] is on use", port));
			} finally {
				if (socket != null) {
					socket.close();
				}
			}
		}
		System.out.println("scan end");
	}

}
