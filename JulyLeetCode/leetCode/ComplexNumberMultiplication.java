package leetCode;

import com.sun.javafx.binding.BidirectionalBinding.StringConversionBidirectionalBinding;

/**
 * 537. Complex Number Multiplication
 * 
 * Given two strings representing two complex numbers. You need to return a
 * string representing their multiplication. Note i2 = -1 according to the
 * definition.
 * 
 * Example 1: Input: "1+1i", "1+1i" Output: "0+2i" Explanation: (1 + i) * (1 +
 * i) = 1 + i2 + 2 * i = 2i, and you need convert it to the form of 0+2i.
 * 
 * Example 2: Input: "1+-1i", "1+-1i" Output: "0+-2i" Explanation: (1 - i) * (1
 * - i) = 1 + i2 - 2 * i = -2i, and you need convert it to the form of 0+-2i.
 * 
 * @author dbuch
 *
 */
public class ComplexNumberMultiplication {

	public static void main(String[] args) {
		System.out.println(complexNumberMultiply("1+1i", "1+1i"));
		System.out.println(complexNumberMultiply("1+-1i", "1+-1i"));
		System.out.println(complexNumberMultiply("78+-76i", "-86+72i"));

	}

	static String complexNumberMultiply(String a, String b) {
		StringBuilder str = new StringBuilder();
		char[] aArr = a.toCharArray();
		String term1 = "";
		String term2 = "";
		for (int i = 0; i < aArr.length - 1; i++) {
			if (aArr[i] == '+') {
				term1 = str.toString();
				str.setLength(0);
			} else {
				str.append(aArr[i]);
			}
		}
		term2 = str.toString();
		str.setLength(0);

		int a1 = Integer.parseInt(term1);
		int b1 = Integer.parseInt(term2);

		char[] bArr = b.toCharArray();

		for (int i = 0; i < bArr.length - 1; i++) {
			if (bArr[i] == '+') {
				term1 = str.toString();
				str.setLength(0);
			} else {
				str.append(bArr[i]);
			}
		}
		term2 = str.toString();

		int a2 = Integer.parseInt(term1);
		int b2 = Integer.parseInt(term2);

		int t1 = (a1 * a2) + (b1 * b2 * (-1));

		int t2 = (a1 * b2) + (b1 * a2);

		return t1 + "+" + t2 + "i";

	}

	/*
	 * char[] aArr = a.toCharArray(); char[] bArr = b.toCharArray();
	 * 
	 * char aChar = '1'; char bChar = '1';
	 * 
	 * int aInt = 1 ; int bInt = 1;
	 * 
	 * if(aArr[0]=='-'){ aChar = aArr[1] ; aInt =
	 * (-1)*Integer.parseInt(Character.toString(aChar)); if(aArr[3] == '-'){
	 * bChar = aArr[4]; bInt = (-1)*Integer.parseInt(Character.toString(bChar));
	 * }else{ bChar = aArr[3]; bInt =
	 * Integer.parseInt(Character.toString(bChar)); } }else{ aChar = aArr[0];
	 * aInt = Integer.parseInt(Character.toString(aChar)); if(aArr[2] == '-'){
	 * bChar = aArr[3]; bInt = (-1)*Integer.parseInt(Character.toString(bChar));
	 * }else{ bChar = aArr[2]; bInt =
	 * Integer.parseInt(Character.toString(bChar)); } }
	 * 
	 * int a1 = aInt; int b1 = bInt;
	 * 
	 * if(bArr[0]=='-'){ aChar = bArr[1] ; aInt =
	 * (-1)*Integer.parseInt(Character.toString(aChar)); if(bArr[3] == '-'){
	 * bChar = bArr[4]; bInt = (-1)*Integer.parseInt(Character.toString(bChar));
	 * }else{ bChar = bArr[3]; bInt =
	 * Integer.parseInt(Character.toString(bChar)); } }else{ aChar = bArr[0];
	 * aInt = Integer.parseInt(Character.toString(aChar)); if(bArr[2] == '-'){
	 * bChar = bArr[3]; bInt = (-1)*Integer.parseInt(Character.toString(bChar));
	 * }else{ bChar = bArr[2]; bInt =
	 * Integer.parseInt(Character.toString(bChar)); } }
	 * 
	 * int a2 = aInt; int b2 = bInt;
	 * 
	 * int t1 = (a1 * a2) + (b1 * b2 * (-1)); int t2 = (a1 * b2) + (b1 * a2);
	 * 
	 * return t1 + "+" + t2 + "i";
	 * 
	 */
}
