package com.lsheep.noi.duplex;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;

public class Client {

	public static void main(String[] args) throws Exception {

		SocketChannel clientChannel = SocketChannel.open();
		clientChannel.configureBlocking(false);
		SocketAddress remoteAddress = new InetSocketAddress(InetAddress.getByName("127.0.0.1"), 1030);
		clientChannel.connect(remoteAddress);

		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
		ReadableByteChannel readChannel = Channels.newChannel(System.in);
		WritableByteChannel writeChannel = Channels.newChannel(System.out);
		while (clientChannel.finishConnect()) {
			// System.out.println("client connected");
			readChannel.read(byteBuffer);
			// byteBuffer.put((byte) '\r');
			// byteBuffer.put((byte) '\n');
			byteBuffer.flip();
			clientChannel.write(byteBuffer);
			// System.out.println("client success wirte");
			byteBuffer.clear();
			clientChannel.read(byteBuffer);
			// System.out.println("client success read");
			byteBuffer.flip();
			writeChannel.write(byteBuffer);
			byteBuffer.clear();
		}

	}

}
