package mergeSort;

import java.util.Arrays;


public class MergeSort {
	private static long startTime, endTime, elapsedTime;
	private static int phase = 0;

	public static void main(String[] args) {
		//  Implementation of an Merge Sort Algorithm
		int n = 100000;
		int i = 0 ;
		forint(n, i);
		System.out.println();
		fordouble(n, i);
		System.out.println();
		forfloat(n, i);

	}

	private static void forfloat(int n, int i ) {
		// Merge Sort For Float Call
		Float[] Arr ;
		Arr = new Float[n];
		while(i<n)
		{
			Arr[i] = (float) (n-i);
			i++;
		}
		System.out.println("Time for merge sort for Float type !");
		timer();
		mergeSort(Arr, 0, Arr.length -1);
		timer();

	}

	private static void fordouble(int n, int i ) {
		// Merge Sort For Double  Call
		Double[] Arr ;


		Arr = new Double[n];
		while(i<n)
		{
			Arr[i] = (double) (n-i);
			i++;
		}
		System.out.println("Time for merge sort for Double type !");
		timer();
		mergeSort(Arr, 0, Arr.length -1);
		timer();
	}

	private static void forint(int n, int i ) {
		// Merge Sort For Integer Call
		Integer[] Arr ;


		Arr = new Integer[n];
		while(i<n)
		{
			Arr[i] = (n-i);
			i++;
		}
		System.out.println("Time for merge sort for Integer type  !");
		timer();
		mergeSort(Arr, 0, Arr.length -1);
		timer();


	}

	
		

	private static <T  extends Comparable<? super T>> void mergeSort(T[] arr, int start, int end) {
		// TODO Splitting followed by Merging.

		if(start<end)
		{
			int	mid =  (end+start)/2;

			mergeSort(arr,start,mid);
			mergeSort(arr,mid+1,end);
			merge(arr,start,mid,end);
		}


	}

	private static <T  extends Comparable<? super T>> void merge(T[] res,int start ,int mid, int end) {
		//  Merging Process.
		T[] L = Arrays.copyOfRange(res, start, mid +1);
		T[] R = Arrays.copyOfRange(res, mid + 1, end +1);

		int	i = 0; int j = 0; int k=start;

		while(k <= end)
		{
			if (i != L.length && j != R.length)
			{
				if (L[i].compareTo(R[j]) < 0) 
				{
					res[k] = L[i]; i++;k++;
				}
				else
				{
					res[k] = R[j]; j++;k++;
				}
			}
			else if (i != L.length)
			{
				res[k] = L[i] ; i++; k++;
			}
			else 
			{
				res[k] = R[j] ; j++; k++;
			}
		}
	}

	public static void timer()
	{
		if(phase == 0) {
			startTime = System.currentTimeMillis();
			phase = 1;
		} else {
			endTime = System.currentTimeMillis();
			elapsedTime = endTime-startTime;
			System.out.println("Time: " + elapsedTime + " msec.");
			memory();
			phase = 0;
		}
	}

	public static void memory() {
		long memAvailable = Runtime.getRuntime().totalMemory();
		long memUsed = memAvailable - Runtime.getRuntime().freeMemory();
		System.out.println("Memory: " + memUsed/1000000 + " MB / " + memAvailable/1000000 + " MB.");
	}
}

