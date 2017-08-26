package com.lsheep.network;

import java.io.DataInputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class BinaryDataServer {

	public static void main(String[] args) {
		try (ServerSocket serverSocket = new ServerSocket()) {
			SocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 1030);
			serverSocket.bind(socketAddress);
			System.out.println("server start at port [1030]");
			while (true) {
				Socket socket = serverSocket.accept();
				DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
				byte[] buffer = new byte[1024];
				dataInputStream.readFully(buffer);
				// dataInputStream.read(buffer);
				socket.shutdownInput();
				socket.close();

				for (byte b : buffer) {
					System.out.print((char) b);
				}
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("server shutdown");
	}

}
