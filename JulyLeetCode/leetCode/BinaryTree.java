package leetCode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * 230. Kth Smallest Element in a BST
 * 
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
 * Note: 
 * You may assume k is always valid, 1 ? k ? BST's total elements.
 * Follow up:
 * What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?
 * @author dbuch
 *
 */
public class BinaryTree {

	static class TreeNode {
		      int val;
		      TreeNode left;
		      TreeNode right;
		      TreeNode(int x) { val = x; }
	}
		 
	public static void main(String[] args) {
		/*TreeNode root = new TreeNode(3);
		root.left = new TreeNode(9);
		root.right = new TreeNode(20);
		root.right.right = new TreeNode(7);
		root.right.left = new TreeNode(15);*/
		
		/*TreeNode root = new TreeNode(3);
		root.left = new TreeNode(1);
		root.right = new TreeNode(5);
		root.left.left = new TreeNode(0);
		root.left.right = new TreeNode(2);
		root.right.left = new TreeNode(4);
		root.right.right = new TreeNode(6);*/
		
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(2);
		root.right = new TreeNode(-3);
		List<Double> list = averageOfLevels(root);
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Double double1 = (Double) iterator.next();
			System.out.println(double1);
		}
	}
	
	 static int kthSmallest(TreeNode root, int k) {
	        ArrayList<Integer> result = new  ArrayList<Integer>();
	        traverse(root, result, k);
	        return result.get(k-1) ;
	    }
	    
	    static  void traverse(TreeNode node,  ArrayList<Integer> result, int k){
	                if(node.left != null)  traverse(node.left , result, k);
	                result.add(node.val);                 
	                if(node.right != null)  traverse(node.right , result, k);  
	    }
	
public static List<Double> averageOfLevels(TreeNode root) {
	Queue<TreeNode> stack = new LinkedList<>();
	List<Double> list = new LinkedList<>() ;
	double n = 0.0 ;
	double count = 0.0 ;
	double sum = 0.0 ;
	double nodeCount = 0.0 ;
	
	stack.offer(root);
	
	while(!stack.isEmpty()){
		TreeNode temp = stack.poll();
		if(temp == null) {
			count++;
			if(count >= Math.pow(2, n)){
				if(nodeCount != 0.0)
				list.add((double) (sum/nodeCount));
				sum = 0.0 ;
				count = 0.0 ;
				nodeCount = 0.0;
				n++ ;
			}
			continue ;
		}
		System.out.println("current node :" + temp.val + " level : " +  n);
		count++;
		sum+=temp.val ;
		nodeCount++;

		if(count >= Math.pow(2, n)){
			list.add((double) (sum/nodeCount));
			sum = 0.0 ;
			count = 0.0 ;
			nodeCount = 0.0;
			n++ ;
		}
		stack.offer(temp.left); 
		stack.offer(temp.right);
	}
	System.out.println("current nodecount :" +nodeCount + " sum : " +  sum);
if(sum > 0.0) {
	list.add((double) (sum/nodeCount));

}
	return list;
        
    }


}
