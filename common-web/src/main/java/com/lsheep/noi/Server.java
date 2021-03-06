package com.lsheep.noi;

import static java.nio.channels.SelectionKey.OP_ACCEPT;
import static java.nio.channels.SelectionKey.OP_READ;
import static java.nio.channels.SelectionKey.OP_WRITE;

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
		serverChannel.register(selector, OP_ACCEPT);

		System.out.println("server start");
		while (true) {
			selector.select();
			Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			while (iterator.hasNext()) {
				// System.out.println("key ready !");
				SelectionKey selectionKey = iterator.next();
				iterator.remove();

				if (selectionKey.isAcceptable()) {
					System.out.println("ready to accept");
					ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
					SocketChannel clientChannel = server.accept();
					clientChannel.configureBlocking(false);
					SelectionKey clientKey = clientChannel.register(selector, OP_WRITE | OP_READ);
					ByteBuffer byteBuffer = ByteBuffer.allocate(64);
					byteBuffer.flip();
					clientKey.attach(byteBuffer);
				} else if (selectionKey.isReadable()) {
					System.out.print("ready to read");
					SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
					ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
					socketChannel.read(byteBuffer);
				} else if (selectionKey.isWritable()) {
					System.out.println("ready to write");
					SocketChannel clientChannel = (SocketChannel) selectionKey.channel();
					ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
					if (byteBuffer.hasRemaining()) {
						clientChannel.write(byteBuffer);
						byteBuffer.compact();
						System.out.println(byteBuffer);
					}
				}

			}

		}

	}

}
