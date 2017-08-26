package com.lsheep.network;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class BinaryDataClient {

	public static void main(String[] args) {
		try (Socket socket = new Socket()) {
			SocketAddress remote = new InetSocketAddress("127.0.0.1", 1030);
			socket.connect(remote);
			OutputStream outputStream = new BufferedOutputStream(socket.getOutputStream());
			outputStream.write("I love LiYang so much".getBytes());
			outputStream.flush();
			socket.shutdownOutput();
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("send message success");
	}

}
