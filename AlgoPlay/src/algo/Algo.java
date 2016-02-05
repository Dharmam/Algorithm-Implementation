package algo;

import java.util.*;
import java.util.Set;

public class Algo {

	public static void main(String[] args){
	Set<Integer> myset = new HashSet<Integer>() ;
	int i = 72;
	myset.add(72);
	
	myset.add(new Integer(72));
	myset.add(Integer.valueOf(i));
	}

}
