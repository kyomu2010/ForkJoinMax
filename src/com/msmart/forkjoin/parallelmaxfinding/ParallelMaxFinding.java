package com.msmart.forkjoin.parallelmaxfinding;

import java.util.concurrent.RecursiveTask;

import com.msmart.forkjoin.App;

public class ParallelMaxFinding extends RecursiveTask<Integer> {

	private static final long serialVersionUID = 1L;
	private int[] nums;
	private int lowIndex;
	private int highIndex;

	public ParallelMaxFinding(int[] nums, int lowIndex, int highIndex) {
		this.nums = nums;
		this.lowIndex = lowIndex;
		this.highIndex = highIndex;
	}

	@Override
	protected Integer compute() {
		
		// if number of items is below threshold - use sequential algorithm, otherwise use parallel algorithm
		if (highIndex - lowIndex < App.THRESHOLD) {
			return sequentialMaxFind();
		} else {
			
			int middleIndex = (lowIndex + highIndex) / 2;
			
			ParallelMaxFinding task1 = new ParallelMaxFinding(nums, lowIndex, middleIndex);
			ParallelMaxFinding task2 = new ParallelMaxFinding(nums, middleIndex + 1, highIndex);

			// add tasks to forkjoin pool to run parallel
			invokeAll(task1, task2);

			// return max of tasks
			return Math.max(task1.join(), task2.join());
		}

	}

	// time complexity -> linear O(N)
	private int sequentialMaxFind() {

		int max = nums[lowIndex];

		for (int i = lowIndex + 1; i < highIndex; i++) {
			if (nums[i] > max) {
				max = nums[i];
			}
		}
		return max;
	}

}
