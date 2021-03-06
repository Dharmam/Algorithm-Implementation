package linkedListOddEvenOperation;

public class LinkedListFunc {
	static ListNode head;

	private static void addNode(int i) {
		
		if(head == null) head = new ListNode(i,null) ;
		else{
			ListNode temp = head ;
			while(temp.next != null){
				temp = temp.next;
			}
			ListNode temp1 = new ListNode(i,null);
			temp.next = temp1;
		}

	}


	public static ListNode oddEvenList(ListNode h1) {

		ListNode evenhead = null ;
		ListNode oddhead = null ;

		ListNode temp = head;
		int count = 1 ;
		if(head == null) return head;
		else{
			while(temp != null)
			{
				if(count%2 != 0)
				{

					if(oddhead == null) oddhead = new ListNode(temp.val,null) ;
					else{
						ListNode oddtemp = oddhead ;
						while(oddtemp.next != null){
							oddtemp = oddtemp.next;
						}
						ListNode tempodd = new ListNode(temp.val,null);
						oddtemp.next = tempodd;
					}
				}

				else
				{
					if(evenhead == null) evenhead = new ListNode(temp.val,null) ;
					else{
						ListNode eventemp = evenhead ;
						while(eventemp.next != null){
							eventemp = eventemp.next;
						}
						ListNode temp1 = new ListNode(temp.val,null);
						eventemp.next = temp1;
					}
				}
				temp = temp.next;
				count++;
			}
		}
		head = oddhead;
		while(oddhead.next != null)
		{
			oddhead = oddhead.next;
		}
		oddhead.next = evenhead;

		ListNode temp1 = head;
		while(temp1 != null) {
			System.out.print(temp1.val + " ");
			temp1 = temp1.next;
		}

		return head;
	}


	public static void main(String[] args) {
		// Odd-even linked list
		for(int i = 5 ; i > 0 ; i--)	
			addNode(i);

		ListNode temp = head;
		while(temp != null) {
			System.out.print(temp.val + " ");
			temp = temp.next;
		}
		System.out.println();	
		oddEvenList(head);
		

	}


}
