package graph_mst_using_kruskal_algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Kruskal_Algo_Implementation {
	static int adjancy_matrix[][];
	static int mst_adjancy_matrix[][];
	final static int number_of_vertices = 11;
	static int mstedges = 0 ;
	final static int max_value = 999;
	static Vertexstate vertex_state[] =  new Vertexstate[number_of_vertices];;
	static MSTCycleState state[] = new MSTCycleState[number_of_vertices] ;

	private static void kruskalMST() {
		// check for loop, if yes remove it
		for(int i = 0 ; i < number_of_vertices ; i++)
		{
			if(adjancy_matrix[i][i] != 0) adjancy_matrix[i][i] = 0 ;  
		}
		// assign each "non-edge" index a large value, in order to find the minimum weight edge later.
		Map<EdgePoints, Integer>  edges = new HashMap<>();
		for(int i = 0 ; i < number_of_vertices ; i++)
		{
			for(int j = 0 ; j < number_of_vertices ; j++)
			{
				if(adjancy_matrix[i][j] == 0){ adjancy_matrix[i][j] = max_value ;  }
				else{
					if(adjancy_matrix[i][j] != max_value){
						edges.put(new EdgePoints(i+1, j+1), adjancy_matrix[i][j]);
						adjancy_matrix[j][i] = max_value;
					}
				}
			}
		}
		System.out.println();
		//create the edge array to put in the edge in ascending order of their weight
		System.out.println("Edge table for Graph used.(Vertex-to-Vertex-with-Weight)");
		System.out.println(edges);
		Set<Entry<EdgePoints, Integer>> set = edges.entrySet();
		List<Entry<EdgePoints, Integer>> list = new ArrayList<Entry<EdgePoints, Integer>>(set);

		Collections.sort( list, new Comparator<Map.Entry<EdgePoints, Integer>>()
				{
			public int compare( Map.Entry<EdgePoints, Integer> o1, Map.Entry<EdgePoints, Integer> o2 )
			{
				return (o1.getValue()).compareTo( o2.getValue() );
			}
				} );
		edges = new LinkedHashMap<>();
		System.out.println("Edge table for Graph used in terms of edges with ascending weights.(Vertex-to-Vertex-with-Weight)");
		for (Entry<EdgePoints, Integer> entry : list) {
			edges.put(entry.getKey(), entry.getValue());
		}
		// Check For cycle.
		System.out.println(edges);
		System.out.println();
		for(int i = 0 ; i< number_of_vertices; i++)
		{
			vertex_state[i] = Vertexstate.not_visited;
		}
		int u , v;
		for (Entry<EdgePoints, Integer> entry : edges.entrySet()) {
			// Run the MST algorithm till you find "vertex-1" MST edges. 
			if (mstedges != number_of_vertices-1 )
			{
				u = entry.getKey().getRightJoint(); 
				v = entry.getKey().getLeftJoint();
				--u;
				--v;

				if(checkcycle(u,v))
				{
					mst_adjancy_matrix[u][v] = mst_adjancy_matrix[v][u] = entry.getValue();
					vertex_state[u] = Vertexstate.visited ;
					vertex_state[v] = Vertexstate.visited ;
					System.out.println(++mstedges + ". Edge Found Between "+ (u+1) + " and " + (v+1) +" with weight " + entry.getValue());
				}
				else
				{
					System.out.println("Edge Found Between "+ (u+1) + " and " + (v+1) +" with weight REJECTED DUE TO CYCLE CREATION.");
				}
			}
		}
		System.out.println();
		System.out.println("MST for the graphs used.");
		representgraphmatrix(mst_adjancy_matrix);
	}
	private static boolean checkcycle(int u, int v) {
		if(vertex_state[u]== Vertexstate.visited && vertex_state[v]== Vertexstate.visited)
		{
			for(int j = 0 ; j< number_of_vertices ; j++) state[j] = MSTCycleState.not_visited;
			if (findparent(u,v)) return true;
			else return false;
		}
		if(vertex_state[v]== Vertexstate.not_visited) vertex_state[v] = Vertexstate.visited ;
		if(vertex_state[u]== Vertexstate.not_visited) vertex_state[u] = Vertexstate.visited ;
		return true;
	}
	private static boolean findparent(int u, int v) {
		state[v] = MSTCycleState.visiting;
		boolean res = true;
		for(int j = 0 ; j< number_of_vertices ; j++)
		{
			if(res)
			{
				if((mst_adjancy_matrix[v][j] != 0 || mst_adjancy_matrix[j][v] != 0) && state[j] == MSTCycleState.not_visited)
				{
					state[j] = MSTCycleState.visiting;
					if(j == u){ res = false;break;}
					else{
						res = findparent(u,j);
					}
				}
			}
		}
		state[v] = MSTCycleState.visited;

		return res;
	}
	public static void adjancymatrix( int Vertex_Nos) {

		int Vertex_No = Vertex_Nos;
		adjancy_matrix = new int[Vertex_No+1][Vertex_No+1];
		mst_adjancy_matrix = new int[Vertex_No+1][Vertex_No+1];
	}
	private static void setedge(int from, int to, int weight) {
		adjancy_matrix[from][to] = weight;
		adjancy_matrix[to][from] = weight;
	}
	private static void representgraphmatrix(int[][] matrix) {

		for (int i = 0; i < number_of_vertices; i++) 
		{
			if(i<10)System.out.print(i+ "  ");
			else System.out.print(i+ " ");
			for (int j = 0; j < number_of_vertices; j++)
			{ 
				System.out.print(matrix[i][j]);
			} 
			System.out.println();
		}
	}
	
    private static void mstdfs() {

		for(int i = 0 ; i< number_of_vertices; i++)
		{
			state[i] = MSTCycleState.not_visited;
		}
		System.out.print("DFS on MST Result");
		for(int i = 0 ; i <number_of_vertices ; i++)
		{
			mstrundfs(i);
		}
	}
	private static void mstrundfs( int i )
	{
		if(	state[i] == MSTCycleState.not_visited)
		{
			state[i] = MSTCycleState.visiting;
			for(int j = 0 ; j< number_of_vertices ; j++)
			{
				if(state[j] == MSTCycleState.not_visited && mstisedge(i, j))
				{
					mstrundfs(j);
				}
			}
			System.out.print(" - "+ (i+1));
			vertex_state[i] = Vertexstate.visited;
		}
	}
	private static boolean mstisedge(int i, int j) {
		if(mst_adjancy_matrix[i][j] != 0 || mst_adjancy_matrix[j][i] != 0) return true;
		else return false;
	}


	private static void graphdfs() {
		vertex_state = new Vertexstate[number_of_vertices];
		for(int i = 0 ; i< number_of_vertices; i++)
		{
			vertex_state[i] = Vertexstate.not_visited;
		}
		System.out.print("DFS On Graph Result");
		for(int i = 0 ; i <number_of_vertices ; i++)
		{
			graphrundfs(i);
		}
	}
	private static void graphrundfs( int i )
	{
		if(	vertex_state[i] == Vertexstate.not_visited)
		{
			vertex_state[i] = Vertexstate.visiting;
			
			for(int j = 0 ; j< number_of_vertices ; j++)
			{
				if(vertex_state[j] == Vertexstate.not_visited && graphhasedge(i, j))
				{
					graphrundfs(j);
				}
			}
			System.out.print(" - "+ (i+1));
			vertex_state[i] = Vertexstate.visited;
		}
	}
	private static boolean graphhasedge(int i, int j) {
		if(adjancy_matrix[i][j] != 0 || adjancy_matrix[j][i] != 0) return true;
		else return false;
	}

	public static void main(String[] args) {
		adjancymatrix(number_of_vertices);
		System.out.println();

		// (8,12 graph)
		//		setedge(0,1,3); setedge(0,2,6); setedge(0,7,1);
		//		setedge(1,7,2); setedge(1,2,2);	
		//		setedge(2,3,1);	
		//		setedge(3,5,3); setedge(3,4,6); setedge(3,5,3);
		//		setedge(4,5,3);
		//		setedge(5,6,2);
		//		setedge(6,7,10);

		// (5,8 graph)		

		//				setedge(0,1,4); setedge(0,4,6); setedge(0,2,4); setedge(0,3,3);
		//				setedge(1,4,7); setedge(1,2,3);	
		//				setedge(2,3,2);	
		//				setedge(3,4,5); 

		// (11,22) graph


		setedge(0,1,2); setedge(0,4,5); setedge(0,7,3);
		setedge(1,4,4); setedge(1,5,4); setedge(1,2,4);	
		setedge(2,5,6); setedge(2,6,2); setedge(2,3,1);	
		setedge(3,6,3); setedge(3,10,2); 
		setedge(4,7,4); setedge(4,8,3);
		setedge(5,9,7); setedge(5,8,5); setedge(5,4,5);
		setedge(6,10,2);
		setedge(6,9,4); setedge(6,5,3);
		setedge(7,8,3);
		setedge(8,9,6);
		setedge(9,10,3);



		System.out.println("Adjacency Matrix Of The Graph Used.");
		representgraphmatrix(adjancy_matrix);
		graphdfs();
		System.out.println();
		kruskalMST();
		System.out.println();
		mstdfs();
	}

}