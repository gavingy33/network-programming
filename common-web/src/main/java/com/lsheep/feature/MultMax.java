package com.lsheep.feature;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MultMax {

	private static int[] numbers = { 32546, 645, 75, 23534564, 2423, 456, 879877, 654, 4233, 46458, 90, 9889, 0, 75, 7,
			9, 6, 32, 53, 36442423, 434, 75869, 8, 686979, 34, 324, 25, 36457, 1, 2, 5, 3, 35, 6532, 534, 556, 23425,
			234345, 234, 346547, 585, 453, 45, 4, 75765, 35, 34455766, 243532, 423534, 65475685, 534, 52342, 432, 5,
			364754, 3346, 6, 7, 79, 34, 8, 35, 34, 7, 4, 3, 53, 67, 64, 35, 3, 64, 64, 8965, 5, 3, 3, 4636, 35465, 2344,
			325, 4, 6423, 235, 364, 234, 6877, 976, 546, 5634, 32, 435, 6243, 36, 4234, 364, 724, 454, 32443, 23434546,
			23436, 43346 };

	public static int max(int[] numbers, int start, int end) {
		int max = numbers[0];
		for (int index = start; index <= end; index++) {
			max = Math.max(max, numbers[index]);
		}
		return max;
	}

	public static void main(String[] args) throws Exception {
		ExecutorService executorService = Executors.newFixedThreadPool(3);

		int first = 0;
		int last = numbers.length - 1;
		int middle_1_3 = last / 3;
		int middle_2_3 = middle_1_3 * 2;

		Future<Integer> future1 = executorService.submit(new CalThread(numbers, first, middle_1_3));
		Future<Integer> future2 = executorService.submit(new CalThread(numbers, middle_1_3, middle_2_3));
		Future<Integer> future3 = executorService.submit(new CalThread(numbers, middle_2_3, last));
		int[] numbers = { future1.get().intValue(), future2.get().intValue(), future3.get().intValue() };
		System.out.println(MultMax.max(numbers, 0, 2));
		executorService.shutdown();
	}

}

class CalThread implements Callable<Integer> {

	private int[] numbers;
	private int start;
	private int end;

	public CalThread(int[] numbers, int start, int end) {
		this.numbers = numbers;
		this.start = start;
		this.end = end;
	}

	@Override
	public Integer call() throws Exception {
		return MultMax.max(numbers, start, end);
	}

}