package graphProblem;

import java.util.HashSet;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Driver {

	public static final int size = 31;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter k : ");
		Integer k = sc.nextInt();
		Integer[][] a = makeAijMatrix(k);
		sc.close();
		System.out.println();

		System.out.println("10 digit student id : 2021270592");
		String id = "2021270592";
		Integer[][] b = makeBijMatrix(id);
		System.out.println();

		Graph graph = makeGraph(a);
		System.out.println();

		Integer[][] minDist = dijikastra(graph);
		System.out.println();

		findCost(minDist, b);

	}

	private static void findCost(Integer[][] minDist, Integer[][] b) {
		int linksCount = 0;
		int az = 0;

		System.out.println("Following matrix shows the shortest distances between every pair of vertices");
		for (int i = 1; i < minDist.length; i++) {
			for (int j = 1; j < minDist[1].length; j++) {
				System.out.print(minDist[i][j] + " ");
				if (minDist[i][j] != 0) {
					linksCount++;
					az += (minDist[i][j] * b[i][j]);
				}
			}
			System.out.println();
		}

		System.out.println(az);
		System.out.println("density = " + ((30 * (30 - 1))) / linksCount);

	}

	private static Integer[][] dijikastra(Graph graph) {
		Integer[][] minDist = new Integer[size][size];
		int totalCost = 0;

		for (int i = 1; i < size; i++) {

			graph.source = graph.verts.get(i);
			DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm(graph);
			dijkstraAlgorithm.arrangeShortestPath();

			int sum = 0;
			if (graph.numNodes <= 100) {
				for (Vertex vertex : graph) {
					minDist[i][Integer.parseInt(vertex.toString())] = vertex.distance;
					if (vertex.distance != null) {
						sum += vertex.distance;
					}
				}
			}
			totalCost += sum;
		}
		System.out.println("Total distance cost for Dijikastra : " + totalCost);
		return minDist;
	}

	private static Graph makeGraph(Integer[][] a) {
		Graph g = new Graph(size - 1);
		for (int i = 1; i < a.length; i++) {
			for (int j = 1; j < a[1].length; j++) {
				if (i != j)
					g.addDirectedEdge(i, j, a[i][j]);
			}
		}
		return g;
	}

	/**
	 * For generating the aij values, do the following. For any given i, pick k
	 * random indices j1, j2, . . . , jk, all different from each other and from
	 * i. Then set aij1 = aij2 = . . . = aijk = 1,and set aij = 300, whenever j
	 * 6= j1, . . . , jk. Carry out this independently for every i.
	 * 
	 * @param k
	 * @return Integer[][]
	 */
	private static Integer[][] makeAijMatrix(Integer k) {
		System.out.println("This will make the Aij array. ");

		Integer[][] a = new Integer[size][size];
		for (Integer i = 1; i < a.length; i++) {
			HashSet<Integer> set = new HashSet<Integer>();
			while (set.size() != k) {
				Integer randomNum = ThreadLocalRandom.current().nextInt(0, 30);
				if (randomNum != i)
					set.add(randomNum);
			}
			for (Integer j = 1; j < a[1].length; j++) {
				if (i == j)
					a[i][j] = 0;
				else {
					if (set.contains(j))
						a[i][j] = 1;
					else
						a[i][j] = 300;
				}
				System.out.print(a[i][j] + " ");
			}
			System.out.println();
		}

		return a;
	}

	/**
	 * 2021270592
	 * 
	 * For generating the bij values, take your 10-digit student ID, and repeat
	 * it 3 times, to obtain a 30-digit number. For example, if the ID is
	 * 0123456789, then after repetition it becomes
	 * 012345678901234567890123456789. Let d1, d2, . . . , d30 denote the digits
	 * in this 30-digit number. Then the value of bij is computed by the formula
	 * bij = |di − dj|.
	 * 
	 * For example, using the above sample ID, the value of b37 will be b37 =
	 * |d3 − d7| = |2 − 6| = 4.
	 * 
	 * @param id
	 * @return Integer[][]
	 */
	private static Integer[][] makeBijMatrix(String id) {

		Integer[] d = new Integer[size];
		Integer[][] b = new Integer[size][size];
		Integer count = 0;
		System.out.println("The d array will be : ");
		for (Integer i = 1; i < d.length; i++) {
			d[i] = Integer.parseInt(Character.toString(id.charAt(count)));
			if (count == 9)
				count = 0;
			else
				count++;

			System.out.print(d[i] + " ");
		}
		System.out.println();
		System.out.println("This will make the Bij array. ");

		for (Integer i = 1; i < b.length; i++) {
			for (Integer j = 1; j < b[1].length; j++) {
				if (i == j)
					b[i][j] = 0;
				else
					b[i][j] = Math.abs(d[i] - d[j]);
				System.out.print(b[i][j] + " ");
			}
			System.out.println();
		}

		return b;
	}

}
