package com.liyang.learn.common.freemarker;

import java.io.File;
import java.io.IOException;

import freemarker.template.Configuration;

public class FreemarkerFactory {

	private static final Configuration INSTANCE;

	static {
		INSTANCE = new Configuration(Configuration.VERSION_2_3_26);
		try {
			INSTANCE.setDirectoryForTemplateLoading(new File(ClassLoader.getSystemResource("template").getPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Configuration getInstance() {
		return INSTANCE;
	}

}
