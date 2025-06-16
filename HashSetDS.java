import java.util.*;

public class HashSetDS {
    public static void main(String[] args) {
        System.out.println("Hello everyone!");
        
        HashSet<Integer> hs = new HashSet<>();

        hs.add(1);
        hs.add(2);
        hs.add(3);
        //System.out.println(hs);
       //System.out.println(hs.contains(4));
       //hs.remove(3);
       //System.out.println(hs.contains(3));
       
       //Iterating over the hashset.
       Iterator it = hs.iterator();

       while(it.hasNext()){
         System.out.println(it.next());
       }
       
    }
}
