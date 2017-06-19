package graphProblem;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import org.graphstream.graph.implementations.SingleGraph;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class Driver {

	public static final int size = 31;

	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter k : ");
		DefaultCategoryDataset dataSetCost = new DefaultCategoryDataset();
		DefaultCategoryDataset dataSetDensity = new DefaultCategoryDataset();

		while (sc.hasNext()) {
			String input = sc.next().toString() ;
			if (input.equalsIgnoreCase("exit"))
				break;
			else {
				Integer k = Integer.parseInt(input);
				Integer[][] minDist = runCode(k, dataSetCost , dataSetDensity);
				prettyPrint(minDist, k);
				System.out.println("Enter 'exit' to end the program or continue for a new value of k .");
			}
		}
		sc.close();

		createChart(dataSetCost, dataSetDensity);

	}

	private static void createChart(DefaultCategoryDataset dataSetCost, DefaultCategoryDataset dataSetDensity ) throws IOException {
		
		JFreeChart LineChartCost = ChartFactory.createLineChart("K vs Cost", "K", "Cost", dataSetCost, PlotOrientation.VERTICAL,
				true, true, false);

		
		int width = 640;    /* Width of the image */
	      int height = 480;   /* Height of the image */ 
	      File LineChartCostFile = new File( "LineChartCost.jpeg" ); 
	      if(LineChartCostFile.exists()){
	    	  LineChartCostFile.delete();
	      }
	      ChartUtilities.saveChartAsJPEG(LineChartCostFile, LineChartCost, width ,height);
	      
	      JFreeChart LineChartDensity = ChartFactory.createLineChart("K vs Density", "K", "Density", dataSetDensity, PlotOrientation.VERTICAL,
					true, true, false);

			
		      File LineChartDensityFile = new File( "LineChartDensity.jpeg" ); 
		      if(LineChartDensityFile.exists()){
		    	  LineChartDensityFile.delete();
		      }
		      ChartUtilities.saveChartAsJPEG(LineChartDensityFile, LineChartDensity, width ,height);


	}

	private static void prettyPrint(Integer[][] minDist, Integer k) {

		org.graphstream.graph.Graph graphDisplay = new SingleGraph(k.toString());
		for (Integer i = 1; i < minDist.length; i++) {
			graphDisplay.addNode(i.toString());
		}

		for (Integer i = 1; i < minDist.length; i++) {
			for (Integer j = 1; j < minDist.length; j++) {
				if (minDist[i][j] != 0)
					graphDisplay.addEdge(i.toString() + "to" + j.toString(), i.toString(), j.toString(), Boolean.TRUE);
			}
		}

		graphDisplay.display();
	}

	private static Integer[][] runCode(Integer k, DefaultCategoryDataset dataSetCost, DefaultCategoryDataset dataSetDensity) {
		Integer[][] a = makeAijMatrix(k);
		// System.out.println();

		System.out.println("10 digit student id : 2021270592");
		String id = "2021270592";
		Integer[][] b = makeBijMatrix(id);
		// System.out.println();

		graphProblem.Graph graph = makeGraph(a);
		// System.out.println();

		Integer[][] minDist = dijikastra(graph);
		// System.out.println();

		findCost(minDist, b, dataSetCost , dataSetDensity, k);

		
		return minDist;
	}

	private static void findCost(Integer[][] minDist, Integer[][] b, DefaultCategoryDataset dataSetCost, DefaultCategoryDataset dataSetDensity, Integer k) {
		Double linksCount = 0.0;
		int az = 0;

		// System.out.println("Following matrix shows the shortest distances
		// between every pair of vertices");
		for (int i = 1; i < minDist.length; i++) {
			for (int j = 1; j < minDist[1].length; j++) {
				// System.out.print(minDist[i][j] + " ");
				if (minDist[i][j] != 0) {
					linksCount += 1;
					az += (minDist[i][j] * b[i][j]);
				}
			}
			// System.out.println();
		}

		System.out.println("Total cost :" + az);
		System.out.println();
		System.out.println("Non-zero link counts : " + linksCount);
		Double totalCount = (double) (30 * 29);
		System.out.println("Total link count " + totalCount);
		Double density = (linksCount / totalCount);
		System.out.println("density = " + density.toString());

		dataSetCost.addValue(az, "Cost", k);
		dataSetDensity.addValue(density, "Density", k);
		

	}

	private static Integer[][] dijikastra(graphProblem.Graph graph) {
		Integer[][] minDist = new Integer[size][size];
		int totalCost = 0;

		for (int i = 1; i < size; i++) {

			graph.source = graph.verts.get(i);
			DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm(graph);
			dijkstraAlgorithm.arrangeShortestPath();

			int sum = 0;
			if (graph.numNodes <= 100) {
				for (Vertex vertex : graph) {
					if (vertex.parent == graph.source) {
						minDist[i][Integer.parseInt(vertex.toString())] = vertex.distance;
					} else {
						minDist[i][Integer.parseInt(vertex.toString())] = 0;
					}
					if (vertex.distance != null) {
						sum += vertex.distance;
					}

				}
			}
			totalCost += sum;
		}
		// System.out.println("Total distance cost for Dijikastra : " +
		// totalCost);
		return minDist;
	}

	private static graphProblem.Graph makeGraph(Integer[][] a) {
		graphProblem.Graph g = new graphProblem.Graph(size - 1);
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
		// System.out.println("This will make the Aij array. ");

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
				// System.out.print(a[i][j] + " ");
			}
			// System.out.println();
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
		// System.out.println("The d array will be : ");
		for (Integer i = 1; i < d.length; i++) {
			d[i] = Integer.parseInt(Character.toString(id.charAt(count)));
			if (count == 9)
				count = 0;
			else
				count++;

			// System.out.print(d[i] + " ");
		}
		// System.out.println();
		// System.out.println("This will make the Bij array. ");

		for (Integer i = 1; i < b.length; i++) {
			for (Integer j = 1; j < b[1].length; j++) {
				if (i == j)
					b[i][j] = 0;
				else
					b[i][j] = Math.abs(d[i] - d[j]);
				// System.out.print(b[i][j] + " ");
			}
			// System.out.println();
		}

		return b;
	}

}
