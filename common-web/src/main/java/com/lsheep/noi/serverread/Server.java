package com.lsheep.noi.serverread;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Iterator;

public class Server {

	public static void main(String[] args) throws Exception {

		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		serverChannel.configureBlocking(false);
		SocketAddress localAddress = new InetSocketAddress(InetAddress.getByName("127.0.0.1"), 1030);

		serverChannel.bind(localAddress);

		Selector selector = Selector.open();
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);

		WritableByteChannel writeChannel = Channels.newChannel(System.out);

		while (true) {
			selector.select();

			Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			while (iterator.hasNext()) {
				SelectionKey selectionKey = iterator.next();
				iterator.remove();

				if (selectionKey.isAcceptable()) {
					ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
					SocketChannel client = server.accept();
					client.configureBlocking(false);
					SelectionKey clientKey = client.register(selector, SelectionKey.OP_READ);

					ByteBuffer byteBuffer = ByteBuffer.allocate(10);
					clientKey.attach(byteBuffer);
				}
				if (selectionKey.isReadable()) {
					SocketChannel clientChannel = (SocketChannel) selectionKey.channel();
					ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
					clientChannel.read(byteBuffer);
					byteBuffer.flip();
					writeChannel.write(byteBuffer);
					byteBuffer.clear();
				}

			}
		}

	}

}
