package algo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

public class prac {

	public static void main(String[] args) {
		//TODO Auto-generated method stub
		String[] arguments = new String[]{"Code","Fight","On","!"};
		String separator = "/";
		System.out.println(myConcat(arguments,separator));
		String[] domains = new String[]{"en.wiki.org", "codefights.com", "happy.net", "code.info"};
		domainType(domains) ;
		htmlToLuna("<div><img /></div>");
		String[][] arr = new String[][]{{"John","1","1","6"}, {"Martin","1","2","6"}};
		System.out.println(smartAssigning(arr));
		String[][] redirects = {{"godaddy.net", "godaddy.com"}, 
				{"godaddy.org", "godaddycares.com"}, 
				{"godady.com", "godaddy.com"},
				{"godaddy.ne", "godaddy.net"}};
		System.out.println(domainForwarding(redirects));

		System.out.println(isHappy(59));

	}
	static boolean isHappy(int n) {
		Set<Integer> arr = new HashSet<>() ;
		while(n!=1){
			if(arr.add(n)){
				int res = 0 ;
				while(n>=10){
					int i = n%10 ;
					res = res + (i*i);
					n=n/10;
				}
				res = res + (n*n);
				n= res;
			}
			else return false;
		}
		return true;
	}

	static String[][] domainForwarding(String[][] redirects) {
		String[][] res = new String[redirects[0].length][redirects[1].length];
		HashMap<Integer, String> map = new HashMap<>();
		HashMap<String, String> map1 = new HashMap<>();    
		for(int i = 0 ; i < redirects[1].length ; i++ ){
			map.put(i,redirects[i][1]);
			map1.put(redirects[i][0],redirects[i][1]);
		}


		for(int i = 0 ; i < map.size() ; i ++){
			StringBuffer str = new StringBuffer();
			for(int j = 0 ; j < map.size()&&j!=i ; j++){
				if(map.get(i).equals(map.get(j))){
					str.append(redirects[i][0]);
					str.append(redirects[j][0]);
				}
				else if(map.get(i).equals(redirects[j][1])){
					str.append(redirects[i][0]);
					str.append(redirects[j][0]);	
				}
			}
			if(str.equals(null)){
				res[i][0] = redirects[i][0] ;
				res[i][1] = map.get(i);
			}
			else{
				res[i][0] = str.toString();
				res[i][1] = map.get(i);
			}
		}
		return res;

	}
	static String smartAssigning(String[][] information) {
		int prev = -1 ;
		for(int i = 0 ; i < information.length ; i++ ){
			if(!information[i][1].equals("0")){
				if(prev == -1 ) prev = i ;
				else if((Integer.parseInt(information[i][2]) < Integer.parseInt(information[prev][2])) ||
						(Integer.parseInt(information[i][3]) < 
								Integer.parseInt(information[prev][3]))){
					prev = i ;
				}
			}
		}
		return information[prev][0];
	}
	static void  htmlToLuna(String html) {
		String[] arr = html.split("<");
		StringBuffer res = new StringBuffer();
		for(int i = 0 ; i < arr.length; i++){
			if(arr[i].equals("div>")) res.append("DIV([");
			else if (arr[i].equals( "p>")) res.append("P([");
			else if (arr[i].equals( "b>")) res.append("B([");
			else if (arr[i].equals("img />")) res.append("IMG({})");
			else if(!arr[i].equals(""))res.append("])");
		}
		System.out.println( res.toString());
	}
	static String[] domainType(String[] domains) {

		Hashtable<String, String> hash = new Hashtable<>();

		hash.put("com","commercial");
		hash.put("org","organization");
		hash.put("net","network");
		hash.put("info","information");

		String[] out = new String[domains.length];

		for(int i = 0 ; i < domains.length ; i ++) {

			String[] arr = domains[i].split("\\.");
			out[i] = arr[arr.length-1];
		}

		return out;
	}
	static String myConcat(String[] arguments, String separator) {
		String[] arr = new String[arguments.length + separator.length()];
		int j = 0 ;
		System.out.println(arr.length);
		for(int i = 0 ; i < arguments.length ; i ++){
			arr[j] =arguments[i]; 
			j++;
			arr[j] = separator;
			j++;
		}

		StringBuffer str = new StringBuffer(arr[0]) ;
		int i = 1;
		while (i < arr.length){
			str.append( arr[i]);
			i++;
		}
		return str.toString();
	}
}
