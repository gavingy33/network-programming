package com.lsheep.file;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NewFile {

	public static void main(String[] args) {
		Path path = Paths.get("/Users/gaofeng/share");
		System.out.println(path.getRoot());
		System.out.println(path.getParent());
		System.out.println(path.getFileSystem());
		System.out.println(path.getFileName());
		System.out.println(System.getProperty("user.dir"));
		System.out.println(System.getProperty("file.separator"));
		System.out.println(new File("parent", "child").getAbsolutePath());
	}

}
