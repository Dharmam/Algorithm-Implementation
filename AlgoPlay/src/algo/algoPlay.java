package algo;

import java.util.ArrayList;


public class algoPlay {

	public static void main(String[] args) {

		int[] nums = {2};

		System.out.println(removeElement(nums,2));
	}


	public static int removeElement(int[] nums, int val) {
		int result = 0;	    
		ArrayList<Integer> nums1 = new ArrayList<>();
		
		for(int i = 0 ; i < nums.length ; i ++){
			if(nums[i]!=val){ 
				nums1.add(nums[i]);
			}
		}
		if(nums1.size() == 0) result = 0 ;
		else if(nums1.size() == 1){
			nums[0] = nums1.get(0);
			result = 1;
		}
		else{
			for(int i = 0 ; i < nums1.size() ; i ++){
				nums[i] = nums1.get(i);
			}
			result = nums1.size();		
		}


		return result;
	}

}
