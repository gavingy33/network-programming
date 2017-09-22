package com.lsheep.udp.echo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import org.apache.commons.io.IOUtils;

public class EchoUdpServer {

	public static void main(String[] args) {
		try {
			DatagramSocket socket = new DatagramSocket(1030);
			socket.connect(InetAddress.getByName("127.0.0.1"), 2215);
			System.out.println("server start !");
			new Thread(new Runnable() {
				@Override
				public void run() {
					while (true) {
						try {
							byte[] buffer = new byte[1024];
							DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
							socket.receive(packet);
							System.out.println(IOUtils
									.toString(new ByteArrayInputStream(packet.getData(), 0, packet.getLength())));
							socket.send(packet);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
