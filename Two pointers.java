public class Two pointers {
    public static void main(String[] args) {
        //Solution of "Longest possible substring with atmost k distinct characters."
        int l = 0;
		int r = 0;
		int n = str.length();
		int maxLength = 0;
		HashMap<Character,Integer> map = new HashMap<>();
	

		while(r < n){
		   char curr = str.charAt(r);

		   if(map.containsKey(curr)){
			  //If already exists in the hashmap, then increment the frequency.
			  map.put(curr,map.get(curr)+1);
		   }
		   else if(!map.containsKey(curr)){
			   map.put(curr,1);

			   //If no. of distinct characters becomes more than k.
			   while(map.size() > k){
				   //Shrink the window.
				   map.put(str.charAt(l),map.get(str.charAt(l))-1);

				   if(map.get(str.charAt(l)) == 0){
					   //Remove the char from the map, if freq. is zero.
					   map.remove(str.charAt(l));
				   }

				   l++;
			   }
		   }

		   //Update the maxLength of the substring.
		   maxLength = Math.max(maxLength,r-l+1);
		   r++;
		}

		return maxLength;
    }
}
