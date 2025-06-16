import java.util.*;
import java.util.LinkedList;



class HashMapImp<K,V>{
   private class Node {
     K key;
     V value;

    Node(K key, V value){
        this.key = key;
        this.value = value;
    }
}


      private int n; //No.of nodes.
      private int N; //No. of buckets.
      private LinkedList<Node> buckets[]; //Array of linked list.

        HashMapImp(){
         this.N = 4;
         this.buckets = new LinkedList[4];
         for(int i=0;i<N;i++){
            buckets[i] = new LinkedList();
         }
      }

      private void rehash(){
          //We've to take the old bucket data and create a new bigger bucket and store old buckets data into
          //the new one.
          LinkedList<Node> oldBucket[] = buckets;
          
          buckets = new LinkedList[N*2];

          for(int i=0;i<N*2;i++){
             buckets[i] = new LinkedList();
          }

          for(int i=0;i<oldBucket.length;i++){
              LinkedList<Node> ll = oldBucket[i];
              System.out.println(ll);
              for(int j=0;j<ll.size();j++){
                Node node = ll.get(j);
                 System.out.println(node);
                 //Put the nodes in the new bucket now.
                 put(node.key,node.value);
              }
          }
      }

      public int hashFunction(K key){
        //Hover over hashCode to see it's details, here we use it to hash our key.
         int bi = key.hashCode(); 
         //It can return both +ve and -ve values, but we always need +ve ones so we'll use absolute functoin.
         return Math.abs(bi) % N;
         //Here it can also return large values, but we want our values to always lie between 0 and N-1 so 
         //we'll take the remainder of the big number with N.
         //Ex. If u divide any number by 5, then it's remainder will always lie between 0-4 right, exactly,
         //that's what we're doing here also. 
      }

      public int searchInLL(K key, int bi){
         //Find the LL at the bvcket index.
         LinkedList<Node> ll = buckets[bi];

         for(int i=0;i<ll.size();i++){
            if(ll.get(i).key == key){
                 return i;
            }
         }

         return -1;
      }

      public void put(K key, V value){
         //Decide in which bucket we've to put the value. bi = bucket index.
         int bi = hashFunction(key);
         //Check if the key already exists or not. di = data index in linked list.
         int di = searchInLL(key,bi);
         if(di == -1){
             //Create a new node with K-V pair and insert it.
             buckets[bi].add(new Node(key, value));
             n++; //As new node is created, so increment the no.of nodes.
         }else{
              //If key already exists.
              Node node = buckets[bi].get(di);
              node.value = value;
         }

         //Check if lambda is withi safe value(K) or not.
         double lambda = (double)n/N;
         if(lambda > 2.0){
             rehash();
         }
      }

      public V get(K key){
         
         int bi = hashFunction(key);//Get the bucket index.
         int di = searchInLL(key, bi);
         if(di == -1){
             return null;
         }
         else{
             Node node = buckets[bi].get(di);
             
             return node.value;
         }

      }

      public V remove(K key){
         
        int bi = hashFunction(key);//Get the bucket index.
        int di = searchInLL(key, bi);
        if(di == -1){
            return null;
        }
        else{
            Node node = buckets[bi].remove(di);
            n--;
            return node.value;
        }

     }

     public boolean isEmpty(){
         return n == 0; //If no. of nodes are zero, then return true,else false.
     }

      public boolean containsKey(K key){
        int bi = hashFunction(key);//Get the bucket index.
        int di = searchInLL(key, bi);
        if(di == -1){
            return false;
        }
        else{
            return true;
        }
      }

      public ArrayList<K> keySet(){
         ArrayList<K> keys = new ArrayList<>();

         //Traverse over the whole bucket and get the keys.
         for(int i = 0;i<buckets.length;i++){
               LinkedList<Node> ll = buckets[i];
               //Traverse over every node of the Linked list.
               for(int j=0;j<ll.size();j++){
                   Node node = ll.get(j);
                   keys.add(node.key);
               }
         }
         return keys;
      }
}

public class HashMapDS {
    public static void main(String[] args) {
        System.out.println("Hello everyone");

        // HashMap<String,Integer> myMap = new HashMap<>();
        // myMap.put("India",150);
        // myMap.put("USA",300);
        // myMap.put("China", 200);
        // myMap.remove("China");
        // System.out.println(myMap);
        //System.out.println(myMap.get("India"));
       //System.out.println( myMap.get("USA"));
       //System.out.println(myMap.containsKey("India"));
       //System.out.println(myMap.containsKey("asdd"));

       //Iteration over a HashMap.
    //    for(Map.Entry<String,Integer> e:myMap.entrySet()){
    //         System.out.println(e.getKey() +  e.getValue());
    //    }

    //    //Another method.
    //    Set<String> keys = myMap.keySet();
    //    for(String key:keys){
    //       System.out.println(key + " " + myMap.get(key));
    //    }

    HashMapImp<String,Integer> map = new HashMapImp();
    map.put("India", 130);
    map.put("China",150);
    map.put("US",200);

    ArrayList<String> keys = map.keySet();
    System.out.println(keys);
    for(int i=0;i<keys.size();i++){
        System.out.println(keys.get(i) + "," + map.get(keys.get(i)));
    }
    //System.out.println(map.get("India"));
    //System.out.println(map.isEmpty());
    

    }
}
