package com.liyang.learn.common.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.liyang.learn.common.file.ReadClasspathFile;

public class SocketClient {

	public static void main(String[] args) {
		try {
			Socket socket = new Socket("127.0.0.1", 9090);
			OutputStream outputStream = socket.getOutputStream();

			ReadClasspathFile reader = new ReadClasspathFile();
			String request = reader.readFile("config/code.json");
			outputStream.write(request.getBytes("UTF-8"));
			outputStream.flush();

			socket.shutdownOutput();
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
