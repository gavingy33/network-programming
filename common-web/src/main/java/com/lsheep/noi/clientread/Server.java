package com.lsheep.noi.clientread;

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
		byte[] array = new byte[26];
		for (byte i = 'a'; i <= 'z'; i++) {
			array[i - 'a'] = i;
		}
		return array;
	}

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
					ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
					SocketChannel socketChannel = server.accept();
					socketChannel.configureBlocking(false);
					SelectionKey clientKey = socketChannel.register(selector, SelectionKey.OP_WRITE);

					ByteBuffer byteBuffer = ByteBuffer.allocate(28);

					byteBuffer.put(ARRAY);
					byteBuffer.put((byte) '\r');
					byteBuffer.put((byte) '\n');
					byteBuffer.flip();
					clientKey.attach(byteBuffer);
					System.out.println(byteBuffer);
				} else if (selectionKey.isWritable()) {
					SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
					ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
					if (!byteBuffer.hasRemaining()) {
						byteBuffer.rewind();
						byte lastFirst = byteBuffer.get();
						int index = lastFirst - 'a' + 1;
						byteBuffer.clear();
						byteBuffer.put(ARRAY, index, ARRAY.length - index);
						byteBuffer.put(ARRAY, 0, index);
						byteBuffer.put((byte) '\r');
						byteBuffer.put((byte) '\n');
						byteBuffer.flip();
					}
					socketChannel.write(byteBuffer);
					System.out.println(byteBuffer);
				}
			}

		}

	}

}
