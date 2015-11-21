package redixSort;

public class RedixSort {

	public static void main(String[] args) {

		Integer[] input ={2346,1234,3752,4999,4567,1456,2345,1267,7453,2389,9123,3587,8723,6349,3987,4312,9876,3754};
			//{2346,123,3752,99,4567,1456,63,1267,7453,2389,876,3587,98,6349,100,4312,9876,3754,1,657};
		String[] input_str = new String[input.length];
		int no_of_digits = 0;
		for(int i=0; i<input.length;i++){
			input_str[i] = input[i].toString();		
			no_of_digits = (no_of_digits > input_str[i].length() ? no_of_digits : input_str[i].length());
		}
		System.out.println("Number Of Digits :-" + no_of_digits);
		System.out.println();
		System.out.println("Input Array Is :-");
		for(int i=0; i<input.length;i++){
			input_str[i] = String.format("%1$" + 4 + "s", input_str[i]).replace(' ', '0');
			System.out.print(input_str[i]+" ");
		}
		System.out.println();
		Integer[] output = redixSort(input_str,no_of_digits);
		System.out.println();
		System.out.println();
		System.out.println("Sorted Output Is :-");
		for(int i = 0 ; i<output.length;i++)
		{
			System.out.print(output[i]+" ");
		}
	}

	private static Integer[] redixSort(String[] in, int dig) {
		String[] input_str = in ;
		int no_of_digits = dig ;
		int ind = 0;
		while(no_of_digits>0)
		{
			int count =0;
			String[] sortingArray = new String[10];
			for(int i=0; i<input_str.length ; i++)
			{
				Integer val = input_str[i].length();	
				String temp = String.valueOf((input_str[i].charAt(val-1-ind)));
				Integer LSD = Integer.parseInt(temp);
				if(sortingArray[LSD]==null)sortingArray[LSD] = input_str[i].toString();
				else sortingArray[LSD] = sortingArray[LSD]+"," +input_str[i].toString();
			}
			for(int i=0;i<sortingArray.length ; i++)
			{
				if(sortingArray[i]!=null)
				{
					if(!sortingArray[i].contains(","))
					{
						input_str[count] = sortingArray[i];
						count++;
					}
					else
					{
						String temp = sortingArray[i];
						while(temp.length()>5)
						{
							int inde = temp.indexOf(",");
							input_str[count] = temp.substring(0,inde);
							temp = temp.substring(inde+1);
							count++;
						}
						input_str[count] = temp;
						count++;
					}

				}
			}
			System.out.println();
			System.out.println( "After pass " + (5-no_of_digits));
			for(int i=0; i<input_str.length;i++){
				System.out.print(input_str[i]+" ");
			}
			ind++;	
			no_of_digits--;
		}
		Integer out[] = new Integer[input_str.length];
		for(int i = 0 ;i < input_str.length; i++) out[i] = Integer.parseInt(input_str[i]);
		
		return out;
	}
		
	
}
