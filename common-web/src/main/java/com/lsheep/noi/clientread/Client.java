package com.lsheep.noi.clientread;

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
		SocketChannel socketChannel = SocketChannel.open();
		socketChannel.configureBlocking(false);
		socketChannel.connect(remoteAddress);

		ByteBuffer byteBuffer = ByteBuffer.allocate(5);
		WritableByteChannel writeChannel = Channels.newChannel(System.out);
		while (socketChannel.finishConnect()) {
			while (socketChannel.read(byteBuffer) != -1) {
				byteBuffer.flip();
				writeChannel.write(byteBuffer);
				byteBuffer.clear();
			}
		}

	}

}
