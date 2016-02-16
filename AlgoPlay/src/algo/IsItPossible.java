package algo;


public class IsItPossible {

	public static void main(String[] args) {

		String res = isitpossible(1, 4, 5, 9);
		System.out.println(res);

	}

	static String isitpossible(int a, int b, int c, int d) {

		if((a>c || a>d) || (b>c || b>d)) return("NO");

		else{
			int res = 0;

			while((a<c || a<d ) && (b<c || b<d)){
				if(a==c || a==d) b=a+b;
				else if (b==d || b==c ) a=a+b;
				else{
					if(a<=b)a=a+b;
					else b=a+b;
				}
			}
			if(a==c || a==d){
				if(b==c ||b==d){
					res = 1;
				}
				else res = 0;
			}

			if(res==1) return ("Yes");
			else return ("No");
		}

	}

}
