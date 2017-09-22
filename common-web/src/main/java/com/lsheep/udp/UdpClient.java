package com.lsheep.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class UdpClient {

	public static void main(String[] args) {
		DatagramSocket datagramSocket = null;
		try {
			datagramSocket = new DatagramSocket(2215);
			datagramSocket.setReuseAddress(true);
			SocketAddress socketAddress = new InetSocketAddress(InetAddress.getByName("127.0.0.1"), 1030);
			byte[] buffer = "Helle World !".getBytes();
			DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, socketAddress);
			datagramSocket.send(datagramPacket);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (datagramSocket != null) {
				datagramSocket.close();
			}
		}
	}

}
