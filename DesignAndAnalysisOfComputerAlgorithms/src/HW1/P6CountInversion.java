package HW1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class P6CountInversion {

	public static void main(String[] args) {
		//int[] arr1 = new int[] { 3, 4, 9, 7, 1 };
		//int[] arr1 = new int[]{1,3,4,7,9};
		int[] arr1 = new int[]{9,7,4,3,1};

		ArrayList<String> inversions = findInversion(arr1);
		System.out.println(inversions.toString());

		System.out.println("Inversions(I) : " + inversions.size());
		int t = insertionSort(arr1);
		System.out.println("Shifts(T) : " + t);



	}

	private static int[] makeArray(int i) {
		int[] arr = new int[i];
		for (int j = 0; j < arr.length; j++) {
			arr[j] = j;
		}
		return arr;
	}

	private static ArrayList<String> findInversion(int[] arr) {
		ArrayList<String> inversions = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			for (int j = i+1; j < arr.length; j++) {
				if (arr[i] > arr[j])
					inversions.add("(" + arr[i] + "," + arr[j] + ")");
			}
		}
		return inversions;
	}

	private static int insertionSort(int[] arr) {
		int count = 0 ;
		for (int i = 0; i < arr.length; i++) {
			int j = i+1;
			while (j < arr.length) {
				if (arr[i] > arr[j]) {
					swap(arr, i, j);
					count++;
					j=i+1;
					continue;
				}
				j++;
			}
		}
		return count ;
	}

	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
