package com.lsheep.common.freemarker;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ProcessObject<K> {

	private Map<K, K> map;

	private List<K> list;

	private BigDecimal amount;

	public Map<K, K> getMap() {
		return map;
	}

	public void setMap(Map<K, K> map) {
		this.map = map;
	}

	public List<K> getList() {
		return list;
	}

	public void setList(List<K> list) {
		this.list = list;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
