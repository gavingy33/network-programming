package com.lsheep.socket;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
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
			InetAddress inetAddress = InetAddress.getByName("10.160.8.87");
			SocketAddress socketAddress = new InetSocketAddress(inetAddress, 1030);

			socket.connect(socketAddress);
			Writer writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StreamUtil.UTF_8));
			writer.write("hello world");
			writer.flush();
			socket.close();
			writer.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void test02() {
		try (Socket socket = new Socket()) {
			InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
			SocketAddress socketAddress = new InetSocketAddress(inetAddress, 1030);

			socket.connect(socketAddress);
			StreamUtil.printStream(socket.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
