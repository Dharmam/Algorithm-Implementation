package leetCode;

public class Equation {

	public static void main(String[] args) {
		System.out.println(isPalindrome(-2147483648));
		System.out.println(isPalindrome(-10));
		System.out.println(isPalindrome(0));
		System.out.println(isPalindrome(-2147447412));
		System.out.println(isPalindrome(101));
		System.out.println(isPalindrome(1001));



	}

	static String solveEquation(String equation) {

		int equateIndex = equation.indexOf('=');

		String lhs = equation.substring(0, equateIndex);
		String rhs = equation.substring(equateIndex + 1, equation.length());
		System.out.println(lhs);
		System.out.println(rhs);

		char[] lhsArr = lhs.toCharArray();
		char[] rhsArr = rhs.toCharArray();
		return equation;

	}

	static boolean isPalindrome(int x) {
		  int y = 0 ;
	        if(x < 0 || (x > 0 && x%10 == 0)) return false ;
	        else{
	        	int z = x ;
	            while(z >= 10){
	                y += (z%10);
	                System.out.println("y is :" +  y);
	                z=z/10 ;
	                System.out.println("z is :" +  z);
	                y = y*10 ;
	            }
                y += (z%10);
                System.out.println("y is :" +  y);

	        }
	        return x==y ;
	}
}
