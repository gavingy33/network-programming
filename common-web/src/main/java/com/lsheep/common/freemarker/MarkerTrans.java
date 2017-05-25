package com.liyang.learn.common.freemarker;

import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class MarkerTrans {

	public static void main(String[] args) {
		try {
			Configuration configuration = FreemarkerFactory.getInstance();
			Template template = configuration.getTemplate("html.ftl");

			ProcessObject<String> process = new ProcessObject<String>();

			Map<String, String> map = new HashMap<>();
			map.put("name", "World");
			process.setMap(map);

			List<String> list = new ArrayList<>();
			list.add("context 01");
			list.add("&");
			process.setList(list);

			process.setAmount(new BigDecimal("100.00"));
			template.process(process, new OutputStreamWriter(System.out));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
