package com.lsheep;

public class CalAge {

	public static void main(String[] args) {
		for (int birth = 1970; birth < 1980; birth++) {
			for (int year = birth; year < birth + 100; year++) {
				int age = year - birth;
				int sum = 0;
				for (char c : String.valueOf(year).toCharArray()) {
					sum += Integer.parseInt(String.valueOf(c));
				}
				if (age == sum) {
					System.out.println(String.format("birth:%4s age:%2s year:%s", birth, age, year));
				}
			}

		}
	}
}
