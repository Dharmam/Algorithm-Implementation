import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;




public class Practice1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		List<List<Integer>> triangle = new ArrayList<List<Integer>>();

		List<Integer> lst = new ArrayList<Integer>();
		List<Integer> lst1 = new ArrayList<Integer>();
		List<Integer> lst2 = new ArrayList<Integer>();
		//List<Integer> lst3 = new ArrayList<Integer>();

		lst.add(-1);

		lst1.add(2);
		lst1.add(3);

				lst2.add(1);
				lst2.add(-1);
				lst2.add(-3);
		//
		//		lst3.add(4);
		//		lst3.add(1);
		//		lst3.add(8);
		//		lst3.add(3);

		triangle.add(lst);
		triangle.add(lst1);
		triangle.add(lst2);
		//triangle.add(lst3);

		System.out.println(minimumTotal(triangle));
	}


	public static int minimumTotal(List<List<Integer>> triangle) {
		int i = 0 ;
		int sum =0 ;
		int smallestvalue = 0;
		int smallestindex = 0;
		while(i <triangle.size())
		{
			ArrayList<Integer> arr= (ArrayList<Integer>) triangle.get(i);
			Object[] arr1 = arr.toArray();
			if(arr1.length == 1) 
			{
				smallestvalue = Integer.parseInt( arr1[0].toString());
				smallestindex = 0 ;
			}
			else
			{
				if(smallestindex == 0)
				{
					if(Integer.parseInt(arr1[0].toString())>=Integer.parseInt(arr1[1].toString()) )
					{
						smallestvalue =Integer.parseInt( arr1[1].toString());
						smallestindex = 1;
					}
					else 
					{
						smallestvalue = Integer.parseInt(arr1[0].toString());
						smallestindex = 0;
					}
				}
				else
				{
					if(Integer.parseInt(arr1[smallestindex].toString())>=Integer.parseInt(arr1[smallestindex+1].toString()) 
							|| (Integer.parseInt(arr1[smallestindex].toString())>Integer.parseInt(arr1[smallestindex-1].toString())))
					{
						smallestvalue =(((Integer.parseInt( arr1[smallestindex+1].toString()))<(Integer.parseInt( arr1[smallestindex-1].toString())))
								? (Integer.parseInt( arr1[smallestindex+1].toString())):(Integer.parseInt( arr1[smallestindex-1].toString())));
						smallestindex = (((Integer.parseInt( arr1[smallestindex+1].toString()))<(Integer.parseInt( arr1[smallestindex-1].toString())))
								? (smallestindex+1):(smallestindex-1));
					}
					else if (Integer.parseInt(arr1[smallestindex].toString())==Integer.parseInt(arr1[smallestindex+1].toString()) )
					{
						smallestvalue =Integer.parseInt( arr1[smallestindex+1].toString());
						smallestindex = smallestindex+1;
					}
				}
			}
			sum = sum + smallestvalue;
			i++;
		}
		return sum;   

	}

}




//ArrayList<Integer> arr= (ArrayList<Integer>) triangle.get(i);
//Object[] arr1 = arr.toArray();
//Arrays.sort(arr1);
//sum = sum + Integer.parseInt(arr1[0].toString());
//i++;