package com.liyang.learn.common.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SocketTimeoutClient {

	public static void main(String[] args) {
		try (Socket socket = new Socket()) {
			InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 9000);
			socket.connect(inetSocketAddress);
			OutputStream outputStream = socket.getOutputStream();
			String content = "我是发送报";
			byte[] arr = content.getBytes("GBK");
			System.out.println(arr.length);
			outputStream.write(arr);
			outputStream.flush();
			socket.shutdownOutput();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
