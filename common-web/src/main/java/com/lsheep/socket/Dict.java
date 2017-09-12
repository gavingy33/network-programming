package com.lsheep.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.lsheep.StreamUtil;

public class Dict {

	public static void main(String[] args) {
		try (Socket socket = new Socket()) {

			InetAddress inetAddress = InetAddress.getByName("dict.org");
			SocketAddress endpoint = new InetSocketAddress(inetAddress, 2628);
			// socket.setSoTimeout(5 * 1000);
			socket.connect(endpoint);

			SocketAddress remote = socket.getRemoteSocketAddress();
			System.out.println(remote);
			SocketAddress local = socket.getLocalSocketAddress();
			System.out.println(local);

			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

			while (true) {
				System.out.print("please enter a word :");
				String word = reader.readLine();
				if ("QUIT".equals(word)) {
					break;
				}

				BufferedWriter writer = new BufferedWriter(
						new OutputStreamWriter(socket.getOutputStream(), StreamUtil.UTF_8));
				writer.write("DEFINE fd-eng-deu ");
				writer.write(word);
				writer.write("\r\n");
				writer.flush();
				printStream(socket.getInputStream());
			}
			//
			// InputStream socketInputStream = socket.getInputStream();
			// StreamUtil.printStream(socketInputStream);
			System.out.println(socket.isClosed());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void printStream(InputStream inputStream) throws IOException {
		if (inputStream == null) {
			return;
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		String line = null;

		Pattern pattern = Pattern.compile("(\\d{3})(.*)");

		while ((line = reader.readLine()) != null) {
			if (".".equals(line)) {
				return;
			}
			Matcher matcher = pattern.matcher(line);
			if (matcher.find()) {
				String code = matcher.group(1);
				if (code.startsWith("5")) {
					System.out.println(matcher.group(2));
					return;
				}
				continue;
			}
			System.out.println(line);
		}
	}

}
