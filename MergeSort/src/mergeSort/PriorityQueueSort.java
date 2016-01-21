package mergeSort;

import java.util.*;

public class PriorityQueueSort {
	private static long startTime, endTime, elapsedTime;
	private static int phase = 0;
	public static void main(String[] args) {
		//  Sorting Using Priority Queue.


		//Integer Call
		List<Integer> lst = new ArrayList<>();
		int n =  100000;
		int i =  0 ;
		while(i<n)
		{
			lst.add(n-i);
			i++;
		}
		PriorityQueue<Integer> p = new PriorityQueue<Integer>();
		System.out.println("Time in Priority Queue with Integer");
		timer();
		p.addAll(lst);
		timer();

		System.out.println();

		//Double Call
		List<Double> lst1 = new ArrayList<Double>();
		int n1 =  100000;
		int i1 =  0 ;
		while(i1<n1)
		{
			lst1.add((double) (n1-i1));
			i1++;
		}
		PriorityQueue<Double> p1 = new PriorityQueue<Double>();
		System.out.println("Time in Priority Queue with Double");
		timer();
		p1.addAll(lst1);
		timer();

		System.out.println();

		//Float Call
		List<Float> lst2 = new ArrayList<Float>();
		int n2 =  100000;
		int i2 =  0 ;
		while(i2<n2)
		{
			lst2.add((float) (n2-i2));
			i2++;
		}
		PriorityQueue<Float> p2 = new PriorityQueue<Float>();
		System.out.println("Time in Priority Queue with Float");
		timer();
		p2.addAll(lst2);
		timer();
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
