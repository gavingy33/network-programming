package com.lsheep.noi;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Iterator;

public class Client {

	public static void main(String[] args) throws Exception {
		SocketAddress remoteAddress = new InetSocketAddress(InetAddress.getByName("127.0.0.1"), 1030);

		SocketChannel clientChannel = SocketChannel.open();
		clientChannel.configureBlocking(false);
		clientChannel.connect(remoteAddress);

		ByteBuffer byteBuffer = ByteBuffer.allocate(10);
		WritableByteChannel writeChannel = Channels.newChannel(System.out);
		ReadableByteChannel readChannel = Channels.newChannel(System.in);

		System.out.println("client start");
		Selector selector = Selector.open();
		clientChannel.register(selector, SelectionKey.OP_READ);
		clientChannel.register(selector, SelectionKey.OP_WRITE);

		while (clientChannel.finishConnect()) {
			selector.select();
			Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			SelectionKey selectionKey = iterator.next();
			iterator.remove();

			if (selectionKey.isWritable()) {
				System.out.println("read to write");
				SocketChannel channel = (SocketChannel) selectionKey.channel();
				readChannel.read(byteBuffer);
				byteBuffer.flip();
				if (byteBuffer.hasRemaining()) {
					channel.write(byteBuffer);
					byteBuffer.clear();
				}
			} else if (selectionKey.isReadable()) {
				System.out.println("ready to read");
				SocketChannel channel = (SocketChannel) selectionKey.channel();
				channel.read(byteBuffer);
				byteBuffer.flip();
				if (byteBuffer.hasRemaining()) {
					writeChannel.write(byteBuffer);
					byteBuffer.clear();
				}
			}
		}

	}

}
