package com.liyang.learn.common.commands;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Command {

	public static void main(String[] args) throws ParseException {
		Options options = new Options();

		Option option = new Option("help", "print this help message");
		options.addOption(option);

		options.addOption("start", "server start !");

		CommandLine command = new DefaultParser().parse(options, new String[] { "-help" });

		if (command.hasOption("help")) {
			System.err.println("HELP");
		}
	}

}
