package com.lsheep;

import java.math.BigDecimal;

public class CashValue {

	public static void main(String[] args) {
		BigDecimal start = new BigDecimal(278);
		BigDecimal end = new BigDecimal(295);

		BigDecimal value = start.multiply(new BigDecimal("320")).add(end.multiply(new BigDecimal("45")))
				.divide(new BigDecimal("365"), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("21000"))
				.divide(new BigDecimal("1000"), 2, BigDecimal.ROUND_HALF_UP);
		System.out.println(value);
	}

}
