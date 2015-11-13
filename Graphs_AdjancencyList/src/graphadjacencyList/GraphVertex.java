package graphadjacencyList;

public class GraphVertex {

	int label;
	boolean visited;
	ListNode l;

	public GraphVertex(int i, boolean v) {
		this.label = i;
		this.visited = v;
	}
	
	public void add(int j) {
		if (l != null) {
			l.add(j);
		} 
		else {
			l = new ListNode(j);
		}

	}
}
