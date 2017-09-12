package com.lsheep.socket;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import org.junit.Test;

import com.lsheep.StreamUtil;

public class ClientSocket {

	@Test
	public void test01() {
		try {
			Socket socket = new Socket();
			InetAddress inetAddress = InetAddress.getByName("time.nist.gov");
			SocketAddress socketAddress = new InetSocketAddress(inetAddress, 13);

			socket.connect(socketAddress);
			StreamUtil.printStream(socket.getInputStream());
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
