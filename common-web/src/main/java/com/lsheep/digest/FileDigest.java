package com.lsheep.digest;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

public class FileDigest {

	public static void main(String[] args) {
		for (int i = 0; i < 3; i++) {
			String file = "/Users/gaofeng/share/digest/" + i + ".txt";
			DigestThread digestThread = new DigestThread(file);
			digestThread.registerCallback(new DigetsCallback() {
				@Override
				public void callback(byte[] digest) {
					System.out.println(String.format("digestThread callback, file: %s, digest: %s", file,
							DatatypeConverter.printHexBinary(digest)));
				}
			});
			digestThread.start();
		}
	}

}

class DigestThread extends Thread {

	private List<DigetsCallback> callback = new ArrayList<>();
	private final String file;

	public DigestThread(String file) {
		this.file = file;
	}

	public void registerCallback(DigetsCallback digetsCallback) {
		callback.add(digetsCallback);
	}

	@Override
	public void run() {
		DigestInputStream digestInputStream = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digestInputStream = new DigestInputStream(new BufferedInputStream(new FileInputStream(file)), digest);
			while (digestInputStream.read() != -1) {
			}
			for (DigetsCallback digetsCallback : callback) {
				digetsCallback.callback(digest.digest());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (digestInputStream != null) {
				try {
					digestInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
