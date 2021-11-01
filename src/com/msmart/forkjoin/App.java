package com.msmart.forkjoin;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

import com.msmart.forkjoin.parallelmaxfinding.ParallelMaxFinding;
import com.msmart.forkjoin.sequentialmaxfinding.SequentialMaxFinding;

public class App {

	public static int THRESHOLD = 0;

	public static void main(String[] args) {

		int[] nums = initializeNums();
		THRESHOLD = nums.length / Runtime.getRuntime().availableProcessors();

		SequentialMaxFinding sequentialMaxFinding = new SequentialMaxFinding();

		long start = System.currentTimeMillis();
		System.out.println("Max: " + sequentialMaxFinding.sequentialMaxFind(nums, nums.length));
		System.out.println("Time taken with sequential approach: " + (System.currentTimeMillis() - start) + "ms");
		System.out.println();
		
		// size fork join pool thread to match number of processors
		ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		ParallelMaxFinding parallelMaxFinding = new ParallelMaxFinding(nums, 0, nums.length);

		start = System.currentTimeMillis();
		System.out.println("Max: " + pool.invoke(parallelMaxFinding));
		System.out.println("Time taken with parallel approach: " + (System.currentTimeMillis() - start) + "ms");
	}

	private static int[] initializeNums() {

		Random random = new Random();

		int[] nums = new int[10000];

		for (int i = 0; i < 10000; i++) {
			nums[i] = random.nextInt(1000);
		}

		return nums;

	}

}
