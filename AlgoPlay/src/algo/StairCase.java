package algo;

public class StairCase {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 6 ;
		staircase(n);
	}

	private static void staircase(int n) {
		for(int i = 1 ; i < n+1 ;  i ++ )
		{
			for(int j = i ; j<n ; j++)
			{
				System.out.print(" ");
			}
			for(int k = 1 ; k <= i ; k ++)
			{
				System.out.print("#");
			}
			System.out.println();
		}

	}

}
