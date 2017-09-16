package com.lsheep.socket;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lsheep.StreamUtil;

public class DemoServerSocket {

	public static final int PORT = 80;

	public static void main(String[] args) throws SocketException {
		SocketAddress endpoint = null;
		Enumeration<NetworkInterface> interfaceEnum = NetworkInterface.getNetworkInterfaces();
		while (interfaceEnum.hasMoreElements()) {
			NetworkInterface networkInterface = interfaceEnum.nextElement();
			Enumeration<InetAddress> addressEnum = networkInterface.getInetAddresses();
			while (addressEnum.hasMoreElements()) {
				InetAddress inetAddress = (InetAddress) addressEnum.nextElement();
				if (inetAddress.getAddress().length != 4) {
					continue;
				}
				if (!inetAddress.isLoopbackAddress()) {
					continue;
				}
				endpoint = new InetSocketAddress(inetAddress, PORT);
			}
		}

		try (ServerSocket serverSocket = new ServerSocket()) {
			serverSocket.setSoTimeout(1000 * 60 * 10);
			serverSocket.bind(endpoint);
			System.out.println("server run success :" + serverSocket.getLocalSocketAddress());
			ExecutorService executor = Executors.newFixedThreadPool(10);
			while (true) {
				executor.submit(new DealRequest(serverSocket.accept()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

class DealRequest implements Callable<Void> {

	private Socket socket;

	public DealRequest(Socket socket) {
		this.socket = socket;
	}

	@Override
	public Void call() throws Exception {
		try {
			StreamUtil.printStream(socket.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
