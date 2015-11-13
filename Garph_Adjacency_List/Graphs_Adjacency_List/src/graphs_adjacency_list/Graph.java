package graphs_adjacency_list;

public class Graph {
	static GraphVertex[] AdjList ;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		initVertex(4);
		addEdge(1,2);
		addEdge(1,3);
		addEdge(2,4);
		showAdjList();

	}

	private static void showAdjList() {
		for(int i = 0 ;i<AdjList.length; i ++)
		{
			System.out.print(AdjList[i].label + " - ");
			ListNode l = AdjList[i].l ; 
			while( l!=null)
			{
				System.out.print(l.val + " ");
				l = l.next;
			}
			System.out.println();
		}

	}

	private static void addEdge(int i, int j) {
		AdjList[i-1].add(j-1);
		AdjList[j-1].add(i-1);
	}

	private static void initVertex(int v) {
		AdjList = new GraphVertex[v];
		for(int i = 0 ; i<v ; i++)
		{
			AdjList[i] = new GraphVertex(i+1,false);
		}
	}

}

