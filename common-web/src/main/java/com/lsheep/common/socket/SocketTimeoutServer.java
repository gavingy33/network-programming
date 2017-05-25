package com.lsheep.common.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketTimeoutServer {

	private static int MAX_READ_COUNT = 10000;

	public static void readLenData(StringBuffer httpRequest, int size, InputStream input, String encoding) {
		int readed = 0; // 已经读取数
		int readCount = 0;
		try {
			int available = 0;// input.available(); //可读数
			if (available > (size - readed)) {
				available = size - readed;
			}
			while (readed < size) {
				while (available == 0) { // 等到有数据可读
					if (readCount == MAX_READ_COUNT) {
						throw new IOException();
					}
					readCount++;
					available = input.available(); // 可读数
				}

				if (available > (size - readed)) {
					available = size - readed; // size-readed--剩余数
				}

				if (available > 2048) {
					available = 2048; // size-readed--剩余数
				}

				byte[] buffer = new byte[available];
				int reading = input.read(buffer);
				if (reading < 0)
					break;
				httpRequest = httpRequest.append(new String(buffer, 0, reading, encoding)); // byte数组相加
				readed += reading; // 已读字符
			}
		} catch (IOException e) {
			throw new RuntimeException("按长度读取报文出错，获取到的报文长度与报文头中定义的长度不符！可能为报文传输有误或丢包现象");
		}
	}

	public static void main(String[] args) throws IOException {
		try (ServerSocket serverSocket = new ServerSocket()) {
			InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 9000);
			serverSocket.bind(inetSocketAddress);
			System.out.println("socket server start success");

			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("accept a socket");
				InputStream inputStream = socket.getInputStream();
				StringBuffer stringBuffer = new StringBuffer();
				readLenData(stringBuffer, 20, inputStream, "GBK");
				System.out.println(stringBuffer.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
