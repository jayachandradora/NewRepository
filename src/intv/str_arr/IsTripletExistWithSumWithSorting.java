package intv.str_arr;

import java.util.Arrays;

public class IsTripletExistWithSumWithSorting {

	public static void main(String[] args) {
		int arr[] = { 5, 1, 3, 4, 7 };
		int sum = 12, n = arr.length;
		System.out.println(IsTripletExistWithSumWithSorting(arr, n, sum));
	}

	static int IsTripletExistWithSumWithSorting(int[] arr, int n, int sum) {
        Arrays.sort(arr); 
		int ans = 0;

		// Every iteration of loop counts triplet with first element as arr[i].
		for (int i = 0; i < n - 2; i++) {
			// Initialize other two elements as corner elements of subarray arr[j+1..k]
			int j = i + 1, k = n - 1;

			// Use Meet in the Middle concept
			while (j < k) {
				// If sum of current triplet is more or equal, move right corner to look for
				// smaller values
				if (arr[i] + arr[j] + arr[k] >= sum)
					k--;

				// Else move left corner
				else {
					// This is important. For current i and j, there can be total k-j third
					// elements.
					ans += (k - j);
					j++;
				}
			}
		}
		return ans;
	}

}
