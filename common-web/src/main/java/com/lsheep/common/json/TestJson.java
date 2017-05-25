package com.lsheep.common.json;

import java.math.BigDecimal;

import com.alibaba.fastjson.JSON;

public class TestJson {
	
	public static void main(String[] args) {
		Data data = new Data();
		data.setAmount(new BigDecimal("100"));
		System.out.println(JSON.toJSONString(data));
	}

}

class Data {
	
	BigDecimal amount;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	
}