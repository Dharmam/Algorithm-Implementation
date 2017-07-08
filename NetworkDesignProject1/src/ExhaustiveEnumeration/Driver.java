package ExhaustiveEnumeration;

import java.io.File;
import java.io.IOException;
import java.util.Stack;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import graphProblem.Edge;
import graphProblem.Graph;
import graphProblem.Vertex;
import graphProblem.VisitingState;

/**
 * Created by dbuch on 7/4/17.
 */
public class Driver {

	static int size = 6;

	public static void main(String[] args) throws IOException {
		Integer[][] a = makeGraphMatrix(5);

		/**
		 * 
		 * STEP - 1
		 * 
		 */
		System.out.println("####### Step 1 for p vs reliability. ");
		System.out.println();
		
		DefaultCategoryDataset dataSetCost1 = new DefaultCategoryDataset();

		for (double p = 0.05; p < 1; p = p + 0.05) {
			Double[] edgeReliability = calculateEdgeReliability(a, p);

			/**
			 * The following generates 2^10 combinations for the probable
			 * network state.
			 */
			final int n = 10;
			Double reliability = 0.0;

			for (int i = 0; i < Math.pow(2, n); i++) {
				String bin = Integer.toBinaryString(i);
				while (bin.length() < n)
					bin = "0" + bin;
				char[] chars = bin.toCharArray();
				boolean[] boolArray = new boolean[n];
				for (int j = 0; j < chars.length; j++) {
					boolArray[j] = chars[j] == '0' ? true : false;
				}

				graphProblem.Graph graph = makeGraph(a, boolArray);

				dfs(graph);
				boolean isConnected = resetSeenStatus(graph);
				if (isConnected) {
					// System.out.println("####### #### State " + i + " is
					// connected " + isConnected);
					Double stepReliability = calculateNetworkReliability(edgeReliability, boolArray);
					reliability = reliability + stepReliability;
					// System.out.println("Network reliability for state :" + i
					// + " is : " + stepReliability);
				}
			}

			System.out.println("Network Reliability for p : " + p + " is :" + reliability);
			String pString = Double.toString(p);
			if (pString.length() > 4)
				pString = pString.substring(0, 4);
			dataSetCost1.addValue(reliability, "reliability", pString);

		}
		createChart(dataSetCost1, "P");

		/**
		 * 
		 * STEP - 2
		 * 
		 */
		System.out.println();
		System.out.println();
		System.out.println("####### Step 2 for k vs reliability at fixed P. ");
		System.out.println();
		
		DefaultCategoryDataset dataSetCost = new DefaultCategoryDataset();
		double[] arrAvg = new double[20];

		for (int x = 0; x < 50; x++) {
			for (int k = 0; k < 20; k++) {
				Double[] edgeReliability = calculateEdgeReliability(a, 0.9);

				/**
				 * The following generates 2^10 combinations for the probable
				 * network state.
				 */
				final int n = 10;
				Double reliability = 0.0;

				for (int i = 0; i < Math.pow(2, n); i++) {
					int localK = 0;
					String bin = Integer.toBinaryString(i);
					while (bin.length() < n)
						bin = "0" + bin;
					char[] chars = bin.toCharArray();
					boolean[] boolArray = new boolean[n];
					for (int j = 0; j < chars.length; j++) {
						boolArray[j] = chars[j] == '0' ? true : false;
					}

					graphProblem.Graph graph = makeGraph(a, boolArray);

					dfs(graph);
					boolean isConnected = resetSeenStatus(graph);

					if (localK < k) {
						boolean isSwitch = getRandomBoolean();
						if (isSwitch != isConnected) {
							localK++;
							isConnected = !isConnected;
						}
					}
					if (isConnected) {
						// System.out.println("####### #### State " + i + " is
						// connected " + isConnected);
						Double stepReliability = calculateNetworkReliability(edgeReliability, boolArray);
						reliability = reliability + stepReliability;
						// System.out.println("Network reliability for state :"
						// + i
						// + " is : " + stepReliability);
					}
				}

				arrAvg[k] += reliability ;
			}
			
		}
		for (int i = 0; i < arrAvg.length; i++) {
			System.out.println("Average Network Reliability for k : " + i + " is :" + arrAvg[i]/50);
			dataSetCost.addValue(arrAvg[i]/50, "reliability", (i+1) + "");
		}

		createChart(dataSetCost, "K");

	}

	/**
	 * Calculates reliability for 1 state of network.
	 * 
	 * @param edgeReliability
	 * @param boolArray
	 * @return
	 */
	private static Double calculateNetworkReliability(Double[] edgeReliability, boolean[] boolArray) {
		Double result = 0.0;
		if (boolArray[0]) {
			result = edgeReliability[0];
		} else {
			result = (1 - edgeReliability[0]);

		}
		for (int i = 1; i < boolArray.length; i++) {
			if (boolArray[i]) {
				result = (result * edgeReliability[i]);
			} else {
				result = (result * (1 - edgeReliability[i]));

			}
		}
		return result;
	}

	/**
	 * Reset graph for next step of DFS.
	 * 
	 * @param graph
	 */
	private static boolean resetSeenStatus(Graph graph) {
		boolean isConnected = false;
		for (Vertex vertex : graph) {
			if (vertex.visitingState.equals(VisitingState.VISITED))
				vertex.visitingState = VisitingState.NOTVISITED;
			else
				isConnected = true;
		}
		return isConnected;
	}

	/**
	 * 
	 * @param graph
	 * @param boolArray
	 * @return
	 */
	private static void dfs(Graph graph) {

		Stack<Vertex> pq = new Stack<>();
		pq.push(graph.source);

		while (!pq.isEmpty()) {
			Vertex curr = pq.pop();
			for (Edge edge : curr.Adj) {
				if (edge.To.visitingState.equals(VisitingState.NOTVISITED)) {
					edge.To.visitingState = VisitingState.VISITING;
					pq.add(edge.To);

				}
			}
			curr.visitingState = VisitingState.VISITED;
		}

	}

	/**
	 * Calculate Reliability based on the "p" values and di which is the ith
	 * digit of 10 digit student id.
	 * 
	 * @param graph
	 * @param p
	 * @return
	 */
	private static Double[] calculateEdgeReliability(Integer[][] graph, Double p) {
		int[] d = new int[] { 2, 0, 2, 1, 2, 7, 0, 5, 9, 2 };
		Double[] reliabilty = new Double[10];

		for (int i = 0; i < d.length; i++) {
			Double power = Math.ceil(d[i] / 3);
			reliabilty[i] = Math.pow(1 - p, power + 1);

			/*
			 * System.out.println("ceiling of d[i]/3 : " + power +
			 * " reliability for i : " + i + " is :" + reliabilty[i]);
			 */
		}

		return reliabilty;
	}

	/**
	 * Makes A complete undirected graph on n = 5 nodes. Each vertex is
	 * connected with every other graph.
	 * 
	 * @param vertex
	 * @return int[][] graph.
	 */
	private static Integer[][] makeGraphMatrix(int vertex) {
		Integer[][] graph = new Integer[5][5];
		for (int i = 0; i < graph.length; i++) {
			for (int j = i + 1; j < graph[1].length; j++) {
				/**
				 * A cell graph[i][j] with 1 denotes, a edge connection between
				 * vertex i and vertex j.
				 */
				graph[i][j] = 1;
				graph[j][i] = 1;
			}
		}
		return graph;
	}

	/**
	 * Makes a graph from a 2-d matrix representation.
	 * 
	 * @param a
	 * @param boolArray
	 * @return
	 */
	private static graphProblem.Graph makeGraph(Integer[][] a, boolean[] boolArray) {
		graphProblem.Graph g = new graphProblem.Graph(5);
		int count = 0;
		for (int i = 0; i < a.length; i++) {
			for (int j = i + 1; j < a[1].length; j++) {
				if (boolArray[count])
					g.addEdge(i + 1, j + 1, count);
				count++;
			}
		}
		// System.out.println(count);
		return g;
	}

	/**
	 * Returns a random boolean value.
	 * 
	 * @return
	 */
	public static boolean getRandomBoolean() {
		return Math.random() < 0.5;
		// I tried another approaches here, still the same result
	}

	private static void createChart(DefaultCategoryDataset dataSetCost, String vs) throws IOException {

		JFreeChart LineChartCost = ChartFactory.createLineChart(vs + " vs Reliability", vs, "Reliability", dataSetCost,
				PlotOrientation.VERTICAL, true, true, false);

		int width = 1000; /* Width of the image */
		int height = 480; /* Height of the image */
		File LineChartCostFile = new File(vs + "_vs_ReliabilityLineChart.jpeg");
		if (LineChartCostFile.exists()) {
			LineChartCostFile.delete();
		}
		ChartUtilities.saveChartAsJPEG(LineChartCostFile, LineChartCost, width, height);
	}
}
