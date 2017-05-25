package com.lsheep.common.check;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

public class ParamsCheck {

	private List<String> messages = new ArrayList<>();

	public ParamsCheck() {
	}

	private Check createCheck() {
		return new Check();
	}

	public CheckResult result() {
		CheckResult checkResult = new CheckResult();
		checkResult.setSuccess(CollectionUtils.isEmpty(messages));
		checkResult.setMessage(messages);
		return checkResult;
	}

	public class Check {

		private Check() {
		}

		public Check notEmpty(String message, String checkField) {
			if (StringUtils.isEmpty(checkField)) {
				messages.add(message);
			}
			return this;
		}

		public Check notNull(String message, Object checkFiled) {
			if (checkFiled == null) {
				messages.add(message);
			}
			return this;
		}

	}

	public static void main(String[] args) {
		ParamsCheck paramsCheck = new ParamsCheck();
		ParamsCheck.Check check = paramsCheck.createCheck();
		check.notEmpty("message_1", "content").notNull("message_2", null);
		CheckResult checkResult = paramsCheck.result();
		if (!checkResult.success()) {
			System.out.println(checkResult.message());
		}
	}

}
