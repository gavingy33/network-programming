package com.lsheep.noi;

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

	private static final byte[] ARRAY = initArray();

	private static byte[] initArray() {
		byte[] array = new byte[28];
		for (byte c = 'a'; c <= 'z'; c++) {
			array[(int) c - (int) 'a'] = c;
		}
		array[26] = '\r';
		array[27] = '\n';
		return array;
	}

	public static void main(String[] args) throws Exception {
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		serverChannel.configureBlocking(false);
		SocketAddress localAddress = new InetSocketAddress(InetAddress.getByName("127.0.0.1"), 1030);
		serverChannel.bind(localAddress);

		Selector selector = Selector.open();
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);

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
					SelectionKey clientKey = clientChannel.register(selector, SelectionKey.OP_WRITE);
					ByteBuffer byteBuffer = ByteBuffer.allocate(10);
					byteBuffer.put((byte) 'a');
					byteBuffer.put((byte) 'b');
					byteBuffer.put((byte) '\r');
					byteBuffer.put((byte) '\n');

					byteBuffer.flip();
					clientKey.attach(byteBuffer);
				} else if (selectionKey.isWritable()) {
					System.out.println("ready to write");
					SocketChannel clientChannel = (SocketChannel) selectionKey.channel();
					ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
					if (!byteBuffer.hasRemaining()) {
						byteBuffer.clear();
						byteBuffer.put((byte) 'a');
						byteBuffer.put((byte) 'b');
						byteBuffer.put((byte) '\r');
						byteBuffer.put((byte) '\n');
						byteBuffer.flip();
					}
					clientChannel.write(byteBuffer);
				}

			}

		}

	}

}
