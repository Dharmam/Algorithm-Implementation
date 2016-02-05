package algo;

import java.util.Stack;

public class ParenMatching {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "]]";
		char[] strarr = str.toCharArray();
		checkParenthesis(strarr);
	}

	static void checkParenthesis(char[] str){
		Stack<Character> stack = new Stack<>();
		if(str.length == 0)System.out.println( "not valid");
		else if(str[0] == '}' || str[0] == ']' || str[0] == ')' ) 
			System.out.println("Not valid");
		else{
			for(int i = 0 ; i < str.length ; i++ ){
				if(str[i] == '{' || str[i] == '[' || str[i] == '(' ) stack.push(str[i]);
				else if (str[i] == '}') {
					if(stack.peek() == '{') stack.pop() ;
					else{
						while(stack.isEmpty()){
							char a = stack.pop();
							if(a== '}') break;
						}
						System.out.println("Not Valid ");
						break;						
					}
				}
				else if (str[i] == ']') {
					if(stack.peek() == '[') stack.pop() ;
					else{
						while(stack.isEmpty()){
							char a = stack.pop();
							if(a== ']') break;
						}
						System.out.println("Not Valid ");
						break;						
					}
				}
				else if (str[i] == ')') {
					if(stack.peek() == '(') stack.pop() ;
					else{
						while(stack.isEmpty()){
							char a = stack.pop();
							if(a== ')') break;
						}
						System.out.println("Not Valid ");
						break;						
					}
				}
			}
			if(stack.isEmpty())
				System.out.println("Valid");
			else
				System.out.println("Not Valid");
		}
	}
}

