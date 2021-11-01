package com.msmart.forkjoin.sequentialmaxfinding;

public class SequentialMaxFinding {

	// time complexity -> linear O(N)
	public int sequentialMaxFind(int[] nums, int highIndex) {

		int max = nums[0];

		for (int i = 1; i < highIndex; i++) {
			if (nums[i] > max) {
				max = nums[i];
			}
		}
		return max;
	}
}
