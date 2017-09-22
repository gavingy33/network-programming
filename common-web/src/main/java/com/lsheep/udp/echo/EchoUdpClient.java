package com.lsheep.udp.echo;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import org.apache.commons.io.IOUtils;

public class EchoUdpClient {

	public static void main(String[] args) {

		try {
			DatagramSocket socket = new DatagramSocket(2215);
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
						String line = null;
						while ((line = bufferedReader.readLine()) != null) {
							byte[] buffer = line.getBytes();
							DatagramPacket packet = new DatagramPacket(buffer, buffer.length,
									InetAddress.getByName("127.0.0.1"), 1030);
							socket.send(packet);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();

			new Thread(new Runnable() {
				@Override
				public void run() {
					while (true) {
						try {
							byte[] buffer = new byte[1024];
							DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
							socket.receive(packet);
							InputStream inputStream = new ByteArrayInputStream(packet.getData(), 0, packet.getLength());
							System.out.println(IOUtils.toString(inputStream));
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		} catch (SocketException e) {
			e.printStackTrace();
		}

	}

}
