package com.lsheep.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import com.lsheep.StreamUtil;

public class SecuritySocket {

	public static void main(String[] args) throws Exception {

		SocketFactory socketFactory = SSLSocketFactory.getDefault();
		SSLSocket socket = (SSLSocket) socketFactory.createSocket();

		SocketAddress endpoint = new InetSocketAddress("lsheep.com", 443);
//		socket.setSoTimeout(1000 * 3);
		socket.connect(endpoint);
		socket.setKeepAlive(true);

		String[] suites = socket.getSupportedCipherSuites();
		// for (String suite : suites) {
		// System.out.println(suite);
		// }
		socket.setEnabledCipherSuites(suites);

		BufferedReader reader = new BufferedReader(
				new InputStreamReader(ClassLoader.getSystemResourceAsStream("packet")));

		Writer writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		String line = null;
		while ((line = reader.readLine()) != null) {
			writer.write(line + "\r\n");
		}

		writer.write("\r\n");
		writer.flush();

		StreamUtil.printStream(socket.getInputStream());
		socket.close();
	}

}
