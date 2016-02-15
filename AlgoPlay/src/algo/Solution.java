package algo ;
import java.util.*;

public class Solution {  
	public static void main(String[] args){
		
		String[] _letter = new String[]{"abc","abcba","abcd"};
		mystery(_letter);

	}
	static void mystery(String[] letter) {

		for(int i = 0 ; i < letter.length ; i++){
			char[] input = letter[i].toCharArray();
			int mid = input.length/2;
			char[] firstHalf ;
			char[] secondHalf;
			if(input.length%2==0) {
				mid = input.length/2-1 ;
				firstHalf = Arrays.copyOfRange(input, 0, mid+1);
				secondHalf = Arrays.copyOfRange(input, mid+1, input.length);
			}
			else{
				mid = input.length/2 ;
				firstHalf = Arrays.copyOfRange(input, 0, mid+1);
				secondHalf = Arrays.copyOfRange(input, mid, input.length);
			}
			int result = operations(firstHalf,secondHalf);
			System.out.println(result);
		}


	}
	private static int operations(char[] firstHalf, char[] secondHalf) {
		int count = 0 ;
		int lstIndex = firstHalf.length-1;
		for(int i = 0 ; i <= lstIndex ; i++ ){
			if(firstHalf[i]!=secondHalf[lstIndex-i]){
				while(firstHalf[i]!=secondHalf[lstIndex-i]){
					if(secondHalf[lstIndex-i]>=firstHalf[i] )secondHalf[lstIndex-i]--;
					else firstHalf[i]--;
					count++;
				}
			}
		}
		return count;
		
	}
}