package com.liyang.learn.common.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class SocketServer {

	public static void main(String[] args) {
		try (ServerSocket serverSocket = new ServerSocket()) {
			SocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 9090);
			serverSocket.bind(socketAddress);
			System.err.println("server socket start");

			while (true) {
				Socket socket = serverSocket.accept();
				BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
				String line;
				while ((line = reader.readLine()) != null) {
					System.out.println(line);
				}

				reader.close();
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.err.println("server socket shutdown");
		}
	}

}
