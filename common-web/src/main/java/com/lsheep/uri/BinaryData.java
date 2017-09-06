package com.lsheep.uri;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class BinaryData {

	public static void main(String[] args) {
		try {
			URL url = new URL("https://lsheep.com/index.jpg");
			URLConnection connection = url.openConnection();
			DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(connection.getInputStream()));
			byte[] data = new byte[connection.getContentLength()];
			dataInputStream.readFully(data);
			dataInputStream.close();

			String path = connection.getURL().getPath();
			BufferedOutputStream outputStream = new BufferedOutputStream(
					new FileOutputStream("/Users/gaofeng/share/" + path));
			outputStream.write(data);
			outputStream.flush();
			outputStream.close();
			System.out.println("download success");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
