package com.lsheep.udp;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

import org.apache.commons.io.IOUtils;

public class UdpServer {

	public static void main(String[] args) {
		DatagramSocket datagramSocket = null;
		try {
			SocketAddress socketAddress = new InetSocketAddress(InetAddress.getByName("127.0.0.1"), 1030);
			datagramSocket = new DatagramSocket(socketAddress);
			datagramSocket.connect(InetAddress.getByName("127.0.0.1"), 2215);
			System.out.println("server bind !");
			System.out.println(datagramSocket.getSoTimeout());
			System.out.println(datagramSocket.getReceiveBufferSize() / 1024);
			System.out.println(datagramSocket.getReuseAddress());
			datagramSocket.setReuseAddress(true);
			
			byte[] buffer = new byte[1024];
			DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
			datagramSocket.receive(datagramPacket);

			System.out.println(String.format("remote address :%s", datagramPacket.getSocketAddress()));
			ByteArrayInputStream outputStream = new ByteArrayInputStream(buffer);
			System.out.println(IOUtils.toString(outputStream));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (datagramSocket != null) {
				datagramSocket.close();
			}
		}

	}

}
