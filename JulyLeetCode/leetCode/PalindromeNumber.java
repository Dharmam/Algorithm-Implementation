package leetCode;
/**
 * 9. Palindrome Number
 * Determine whether an integer is a palindrome. Do this without extra space.
 * @author dbuch
 *
 */
public class PalindromeNumber {

	public static void main(String[] args) {
		isPalindrome(1221);
		isPalindrome2(12321);
	}

	/**
	 * Number manipulation.
	 * @param x
	 * @return
	 */
	 static boolean isPalindrome(int x) {
         int y = 0 ;
	        if(x < 0 || (x > 0 && x%10 == 0)) return false ;
	        else{
	        	int z = x ;
	            while(z >= 10){
	                y += (z%10);
	                z=z/10 ;
	                y = y*10 ;
	            }
             y += (z%10);
	        }
	        return x==y ;
 }
	 /**
	  * String based implementation
	  * @param x
	  * @return
	  */
	 static boolean isPalindrome2(int x) {
	        String str = Integer.toString(x);
			String strReverse = new String();
			if (x < 0) {
				strReverse+=(str.substring(str.length()-1,str.length()));
			}
			for (int i = (str.length()-1); i >= 0; i--) {
				strReverse+=(str.substring(i,i+1));
			}
		
			return strReverse.equals(str);
	    }
}
