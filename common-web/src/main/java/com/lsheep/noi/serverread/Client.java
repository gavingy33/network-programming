package com.lsheep.noi.serverread;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SocketChannel;

public class Client {

	public static void main(String[] args) throws Exception {

		SocketChannel clientChannel = SocketChannel.open();
		clientChannel.configureBlocking(false);
		SocketAddress remoteAddress = new InetSocketAddress(InetAddress.getByName("127.0.0.1"), 1030);
		clientChannel.connect(remoteAddress);

		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
		while (clientChannel.finishConnect()) {
			ReadableByteChannel readChannel = Channels.newChannel(System.in);
			readChannel.read(byteBuffer);
			byteBuffer.flip();
			clientChannel.write(byteBuffer);
			byteBuffer.clear();
		}
	}

}
