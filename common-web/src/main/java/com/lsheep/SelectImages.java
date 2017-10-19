package com.lsheep;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;

import javax.imageio.ImageIO;

public class SelectImages {

	public static void main(String[] args) {

		try {
			File file = new File("/Users/gaofeng/share/File");
			File[] children = file.listFiles();
			for (File child : children) {
				System.out.println(child.getAbsolutePath());
				if (!child.getName().endsWith(".gif")) {
					child.delete();
					continue;
				}

				FileInputStream fileInputStream = new FileInputStream(child.getAbsolutePath());
				BufferedImage bufferedImage = ImageIO.read(fileInputStream);
				int height = bufferedImage.getHeight();
				int width = bufferedImage.getWidth();
				if (height != 240 || width != 240) {
					child.delete();
					continue;
				}

				System.out.println(String.format("%dx%d", bufferedImage.getHeight(), bufferedImage.getWidth()));
				fileInputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
