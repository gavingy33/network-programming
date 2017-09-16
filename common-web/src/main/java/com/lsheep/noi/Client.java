package com.lsheep.noi;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;

public class Client {

	public static void main(String[] args) throws Exception {
		SocketAddress remoteAddress = new InetSocketAddress(InetAddress.getByName("127.0.0.1"), 1030);

		SocketChannel clientChannel = SocketChannel.open();
		clientChannel.configureBlocking(false);
		clientChannel.connect(remoteAddress);

		ByteBuffer byteBuffer = ByteBuffer.allocate(5);
		WritableByteChannel writeChannel = Channels.newChannel(System.out);
		System.out.println("client start");
		System.out.println("ready to read");
		while (clientChannel.finishConnect()) {
			while (clientChannel.read(byteBuffer) != -1) {
				byteBuffer.flip();
				writeChannel.write(byteBuffer);
				byteBuffer.clear();
			}
		}
	}

}
