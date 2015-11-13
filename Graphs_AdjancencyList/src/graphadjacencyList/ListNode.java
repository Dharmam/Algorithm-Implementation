package graphadjacencyList;

public class ListNode {

	int val;
	ListNode next;

	public ListNode() {
	}
	
	public ListNode(int i) {
		this.val = i+1;
		this.next = null;
	}

	public void add(int j) {
		ListNode temp = new ListNode(j);
		if (next != null) {
			ListNode temp1 = next;
			while(temp1.next != null){
				temp1 = temp1.next;
			}
			temp1.next = temp;
		} 
		else {
			this.next = temp;
		}

	}

}
