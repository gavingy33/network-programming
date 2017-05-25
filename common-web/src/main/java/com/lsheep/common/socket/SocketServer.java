package com.lsheep.common.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SocketServer {

	static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	public static void main(String[] args) {
		try (ServerSocket serverSocket = new ServerSocket()) {
			SocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 9090);
			serverSocket.bind(socketAddress);
			System.err.println("server socket start");

			while (true) {
				new Thread(new SocketAccept(serverSocket.accept())).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.err.println("server socket shutdown");
		}
	}

}

class SocketAccept implements Runnable {

	private Socket socket;

	public SocketAccept(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			System.out.println(SocketServer.FORMAT.format(new Date()));
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			StringBuffer buffer = new StringBuffer();
			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line).append('\n');
			}
			System.out.println(buffer.substring(0, buffer.length() - 1).toString());

			reader.close();
			socket.close();
			Thread.sleep(1000 * 10);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

}
