package HW1;

import java.util.Arrays;

public class P1InsertionSort {

	public static void main(String[] args) {
		int[] arr = new int[] { 32, 41, 58, 24, 40, 47 };
		System.out.println(Arrays.toString(arr));
		insertionSort(arr);
	}

	private static void insertionSort(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			int j = i+1;
			while (j < arr.length) {
				if (arr[i] > arr[j]) {
					swap(arr, i, j);
					j=i+1;
					System.out.println(Arrays.toString(arr));
					continue;
				}
				j++;
			}
		}
	}

	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
