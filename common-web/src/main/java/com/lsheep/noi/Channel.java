package com.lsheep.noi;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;

public class Channel {

	private static final int PORT = 80;

	public static void main(String[] args) {
		try {
			InetAddress address = InetAddress.getByName("lsheep.com");
			SocketAddress remote = new InetSocketAddress(address, PORT);
			SocketChannel channel = SocketChannel.open(remote);
			channel.configureBlocking(true);

			ByteBuffer byteBuffer = ByteBuffer.allocate(74);
			WritableByteChannel write = Channels.newChannel(System.out);
//			while (true) {
				int length = channel.read(byteBuffer);
				System.out.println(byteBuffer.position());
				byteBuffer.flip();
				System.out.println(byteBuffer.position());
				write.write(byteBuffer);
				System.out.println(byteBuffer.position());
				byteBuffer.clear();
				System.out.println(byteBuffer.position());
//			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
