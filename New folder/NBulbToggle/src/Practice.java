
public class Practice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println(bulbSwitch(0));
		//System.out.println(bulbSwitch(1));
		for(int i = 0 ;i <= 40 ; i++)
		{
		System.out.println(i + " --- " +bulbSwitch(i));
		}
}
	
	
	// *******************************
//	if(n ==0) return 0;
//	else { sum =  Math.sqrt(n); return (int)sum;}
	
	
	
	public static int bulbSwitch(int n) {
		int[] arr = new int[n+1];
		int sum = 0;
		int i = 1;
		do
		{
			int j = 0;
			while(j<n)
			{
				j=j+ i;
				if(j<=n)
				{
					if(arr[j] == 1) {arr[j]=0; sum--;}
					else {arr[j]= 1;sum++;}
				}
				else break;
			}
			i++;
		}while (i<=n);
//		for(int k =0; k<=n ; k++)
//		{
//			sum = sum + arr[k];
//		}
		return sum; 
	}
		
}

