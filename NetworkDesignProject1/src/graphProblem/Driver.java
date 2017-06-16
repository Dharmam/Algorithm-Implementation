package graphProblem;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Driver {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Please eneter your 10 digit student id :");
		String id = sc.nextLine();

		/**
		 * 2021270592 
		 * 
		 * For generating the bij values, take your 10-digit student
		 * ID, and repeat it 3 times, to obtain a 30-digit number. For example,
		 * if the ID is 0123456789, then after repetition it becomes
		 * 012345678901234567890123456789. Let d1, d2, . . . , d30 denote the
		 * digits in this 30-digit number. Then the value of bij is computed by
		 * the formula bij = |di − dj|.
		 * 
		 * For example, using the above sample ID, the value of b37 will be b37
		 * = |d3 − d7| = |2 − 6| = 4.
		 * 
		 **/
		int[] d = new int[30];
		int[][] b = new int[30][30];
		int count = 0;
		System.out.println("The d array will be : ");
		for (int i = 0; i < d.length; i++) {
			d[i] = Integer.parseInt(Character.toString(id.charAt(count)));
			if (count == 9)
				count = 0;
			else
				count++;

			System.out.print(d[i]);
		}
		System.out.println();
		System.out.println("This will make the Bij array. ");

		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b[1].length; j++) {
				b[i][j] = Math.abs(d[i] - d[j]);
				System.out.print(b[i][j] + " ");
			}
			System.out.println();
		}

		/**
		 * For generating the aij values, do the following. For any given i,
		 * pick k random indices j1, j2, . . . , jk, all different from each
		 * other and from i. Then set aij1 = aij2 = . . . = aijk = 1,and set aij
		 * = 300, whenever j 6= j1, . . . , jk. Carry out this independently for
		 * every i.
		 **/

		System.out.println("K is 3 !");

		System.out.println();
		System.out.println("This will make the Aij array. ");
		
		int[][] a = new int[30][30];
		for (int i = 0; i < a.length; i++) {
			int k = 3;
			HashSet<Integer> set = new HashSet<Integer>();
			while (set.size() != k) {
				int randomNum = ThreadLocalRandom.current().nextInt(0, 30);
				if(randomNum != i )set.add(randomNum);
			}
			for (int j = 0; j < a[1].length; j++) {
				if (set.contains(j))
					a[i][j] = 1;
				else
					a[i][j] = 300;
				System.out.print(a[i][j] + " ");
			}
			System.out.println();
		}

	}

}
