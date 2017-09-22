package com.lsheep.noi.duplex;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class Server {

	public static void main(String[] args) throws Exception {

		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		serverChannel.configureBlocking(false);
		SocketAddress localAddress = new InetSocketAddress(InetAddress.getByName("127.0.0.1"), 1030);

		serverChannel.bind(localAddress);

		Selector selector = Selector.open();
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);

		while (true) {
			selector.select();

			Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			while (iterator.hasNext()) {
				SelectionKey selectionKey = iterator.next();
				iterator.remove();

				if (selectionKey.isAcceptable()) {
					System.out.println("server ready to accept");
					ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
					SocketChannel client = server.accept();
					client.configureBlocking(false);
					SelectionKey clientKey = client.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);

					ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
					byteBuffer.flip();
					clientKey.attach(byteBuffer);
				} else if (selectionKey.isReadable()) {
					System.out.println("server ready to read");
					SocketChannel clientChannel = (SocketChannel) selectionKey.channel();
					ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
					byteBuffer.clear();
					clientChannel.read(byteBuffer);
					byteBuffer.flip();
				} else if (selectionKey.isWritable()) {
					// System.out.println("server ready to write");
					SocketChannel clientChannel = (SocketChannel) selectionKey.channel();
					ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
					if (byteBuffer.hasRemaining()) {
						clientChannel.write(byteBuffer);
						byteBuffer.clear();
						byteBuffer.flip();
					}
				}

			}
		}

	}

}
