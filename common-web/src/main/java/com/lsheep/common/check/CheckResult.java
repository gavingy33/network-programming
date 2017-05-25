package com.lsheep.common.check;

import java.util.List;

public class CheckResult {

	private boolean success;
	private List<String> message;

	void setSuccess(boolean success) {
		this.success = success;
	}

	void setMessage(List<String> message) {
		this.message = message;
	}

	public boolean success() {
		return success;
	}

	public String message() {
		return message == null ? "" : message.toString();
	}

}
