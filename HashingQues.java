import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class HashingQues {

    
     public static ArrayList<Integer> majorityEl(HashMap<Integer,Integer> map,int[] arr){
          int N = arr.length / 3;
          ArrayList<Integer> finalArr = new ArrayList<>();
          
          //Finding and storing frequency of each element of array.
          for(int i = 0;i<arr.length;i++){
              if(map.containsKey(arr[i])){
                  //get the existing key and update it's value.
                 
                  map.put(arr[i],map.get(arr[i])+1);
                  
              }else{
                  map.put(arr[i],1);
              }
          }

          for(Map.Entry<Integer,Integer> e: map.entrySet()){
               if(e.getValue() > N){
                  finalArr.add(e.getKey());
               }
          }

          //Here instead of returning an arraylist, you can also just print the key that specifies the condition
          //of our question, so we won't use extra space that way and space complexity will decrease.
          
          return finalArr;
     }
    
     public static void unionOfArrays(int[] arr1,int[] arr2,HashSet<Integer> set){
         for(int i=0;i<arr1.length;i++){
              set.add(arr1[i]);
         }

         for(int j=0;j<arr2.length;j++){
            set.add(arr2[j]);
       }

     }

     public static void intersectionOfArrays(int[] arr1,int[] arr2,HashSet<Integer> bigSet,HashSet<Integer> finalSet){
          //Traverse over array 1 and add it to our map.
          for(int i=0;i<arr1.length;i++){
               bigSet.add(arr1[i]);
          }

          //Traverse over array 2.
          for(int i=0;i<arr2.length;i++){
              if(bigSet.contains(arr2[i])){
                   finalSet.add(arr2[i]);
              }
          }
     }


     public static void findItinerary(HashMap<String,String> map){
          String startingPoint = " ";
          //Finding the starting point.
          ArrayList<String> allKeys = new ArrayList<>();
          ArrayList<String> allValues = new ArrayList<>();
          
          for(String s:map.keySet()){
              allKeys.add(s);
          }

          for(String s:map.values()){
              allValues.add(s);
          }
          
          //Iterate over all the keys and find the starting point.
          //Note:- A destination can't be the starting point.
          //Instead of making arrayList for both, we can also use the map.contains() method to searach in our
          //allKeys, and we can iterate using for loop over the allValues part.
          //You can create a reverse map(not good for every case, but works for this case), means K-V -> V-K 
          //and you can now search in both maps keys and compare.
          //for(String key:map.keySet()){
                //if(revMap.contains(key) == false){
                     //return key;
                  //}
          //}


          for(int i=0;i<allKeys.size();i++){
               if(!allValues.contains(allKeys.get(i))){
                   startingPoint = allKeys.get(i);
               }
          }

          //Now print the path.
         
        
          while(map.containsKey(startingPoint)){
              System.out.print(startingPoint + "->");
              startingPoint = map.get(startingPoint);
          }
          System.out.print(startingPoint);
          
     }
    public static void main(String[] args) {
        System.out.println("Hello everyone!");
        
        //Majority element.
       //HashMap<Integer,Integer> ourMap = new HashMap<>();
       //int[] nums = {1,3,2,5,1,3,1,5,1};
       //int[] nums2 = {1,2};
       
      // System.out.println(majorityEl(ourMap,nums2));

      //Union of two arrays.

    //   HashSet<Integer> ourSet = new HashSet<>();
    //   int[] arr1 = {7,3,9};
    //   int[] arr2 = {6,3,9,2,9,4};

    //   unionOfArrays(arr1, arr2,ourSet);
    //   System.out.println(ourSet.size() );
     

    //Intersection of two arrays.
      //HashMap<Integer,Integer> ourMap = new HashMap<>();
      //Instead of a map and a set, you could have also used two sets instead, that would've worked 100%.
      //Infact, you don't really need the finalSet, you can just print the common elements or just create a
      //count variable to count the common elements, so space complexity will go donw too.
    //   HashSet<Integer> ourSet = new HashSet<>();
    //   HashSet<Integer> finalSet = new HashSet<>();
    //  int[] arr1 = {7,3,9};
    //  int[] arr2 = {6,3,9,2,9,4};
    //  intersectionOfArrays(arr1, arr2, ourSet,finalSet);
     
    //  System.out.println(finalSet);
    

    //Finding Itinerary from Tickets.
//     HashMap<String,String> pathMap = new HashMap<>();
//     pathMap.put("Chennai","Bengaluru");
//     pathMap.put("Mumbai", "Delhi");
//     pathMap.put("Goa", "Chennai");
//     pathMap.put("Delhi", "Goa");
//    findItinerary(pathMap);

    
     //No. of subarrays with sum equal to k.
     
     int[] arr={10,2,-2,-20,10};
     int k = -10;
     int sum = 0; //tracks the prefix sum at each index of the array.
     int ans = 0;
     //Create hashmap to store prefix sum at each index.
     HashMap<Integer,Integer> map = new HashMap<>();
     map.put(0,1); //For the empty subarray, put sum = 0 with frequency 1.

     for(int j=0;j<arr.length;j++){
         sum += arr[j]; //Update the sum at each index.
         if(map.containsKey(sum-k)){
            ans += map.get(sum-k);
         }

         if(map.containsKey(sum)){
            //If map already contains the sum then update it's frequency.
            map.put(sum, map.get(sum) + 1); 
         }else{
            //Else just store it as it is in the map.
            map.put(sum, 1);
         }
     }

    System.out.println(ans);
    
    }
}
