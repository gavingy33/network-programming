package com.lsheep.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

	public static void main(String[] args) {
		String content = "12345 1234567890";

		Pattern pattern = Pattern.compile("(\\d+)( +)(\\d+)");
		Matcher matcher = pattern.matcher(content);

		if (matcher.find()) {
			System.out.println(matcher.group(0));
			System.out.println(matcher.group(1));
			System.out.println(matcher.group(2));
			System.out.println(matcher.group(3));
		}

	}

}
