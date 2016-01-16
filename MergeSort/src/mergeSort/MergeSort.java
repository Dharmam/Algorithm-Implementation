package mergeSort;

import java.util.ArrayList;


public class MergeSort {

	public static void main(String[] args) {
		// TODO Implementation of an Merge Sort Algorithm
		ArrayList<Integer> lst = new ArrayList<Integer>();
		Integer[] arr = //{2,4,1,3};
			{38,27,43,3,9,82,10};
		int i = 0 ;
		while(i<arr.length)
		{
			lst.add(arr[i]);
			i++;
		}
		ArrayList<Integer> res = mergeSort(lst);
		System.out.println(res);
	}

	private static ArrayList<Integer> mergeSort(ArrayList<Integer> lst) {
		// TODO Splitting followed by Merging.
		if(lst.size()<=1)
		{
			return lst;
		}
		{

			ArrayList<Integer> left = new ArrayList<Integer>();
			ArrayList<Integer> right = new ArrayList<Integer>();
			int mid = (lst.size())/2;
			left.addAll(lst.subList(0, mid));
			right.addAll(lst.subList(mid, (lst.size())));
			ArrayList<Integer> result = new ArrayList<Integer>();
			result.addAll(merge(mergeSort(left),mergeSort(right)));
			return result ;
		}

	}


	private static ArrayList<Integer>  merge(ArrayList<Integer> lst1, ArrayList<Integer> lst2) {
		// TODO Merging Process.
		ArrayList<Integer> res = new ArrayList<Integer>();
		int i = 0 ;
		int k = 0;
		int j = 0;
		while(i < lst1.size() && (j < lst2.size()))
		{
			if (lst1.get(i)<lst2.get(j))
			{
				res.add(k,lst1.get(i));
				i++;k++;
			}
			else 
			{
				res.add(lst2.get(j));
				j++;k++;
			}
		}
		while(i != lst1.size())
		{
			res.add(k,lst1.get(i));
			k++;
			i++;

		}
		while(j != lst2.size())
		{
			res.add(k,lst2.get(j));
			k++;
			j++;

		}
		System.out.println(res);
		return res;
	}

}
