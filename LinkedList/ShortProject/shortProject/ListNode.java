package shortProject;

	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
		public ListNode(int i, ListNode next) {
			this.val = i;
			this.next = next;
		}
		protected static void mergeSort(ListNode begin, ListNode end) {
			// MergeSort in Linked List
			ListNode half;
			if(end == null)		 half = findhalf(begin,null);
			else  half = findhalf(begin,end);
			System.out.println(half.val);
			if(begin.next != end)
			{
				mergeSort(begin, half);
				mergeSort(half,null);
			}
		}


		private static ListNode findhalf(ListNode begin, ListNode end) {
			// TODO returns the half way mark.
			ListNode temp = begin.next;
			ListNode temp2 = begin.next.next;
			if(end == null)
			{
				while(temp2.next != null && temp2.next.next != null){
					temp = temp.next;
					temp2 = temp2.next.next;
				}
				return temp;
			}
			else{
				while(temp != end && temp.next != end){
					temp = temp.next;
					temp2 = temp2.next.next;
				}
				return temp; // this will be the half of the list.
			}
		}
}
