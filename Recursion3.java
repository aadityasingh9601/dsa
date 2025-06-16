import java.util.ArrayList;

public class Recursion3 {
    public static void permutatios(String str,String permutation){
        if(str.length() == 0){
             System.out.println(permutation);
             return;
        }
         for(int i=0;i<str.length();i++){
            //Understand this line carefully.
           String newStr = str.substring(0,i) + str.substring(i+1);
            //Call the function with the string but subtracting the element already seated.
            permutatios(newStr, permutation+str.charAt(i));
         }
    }

    public static int countPaths(int i,int j,int n,int m){
        //If boundary is reached.
        if(i == n || j == m){
             return 0;
        }

        //If destination is reached
        if(i == n-1 && j == m-1){
            return 1;
       }

         //Move downwards.
         int downPaths = countPaths(i+1,j,n, m);

         //Move rightwards.
         int rightPaths = countPaths(i, j+1, n, m);

         return (downPaths+rightPaths);
    }

    public static int placeTiles(int n,int m){
        if(n == m){
             return 2;
        }
        if(n<m){
             return 1;
        }
         //Place vertically.
        int vertPlacements =  placeTiles(n-m,m);

        //Place horizontally.
        int horiPlacements = placeTiles(n-1,m);
        
        //Return the total number of ways in which tiles can be placed.
        return vertPlacements + horiPlacements;
    }

    public static int callGuests(int n){
     //We took less than one condition also as what if we've 0 guests? how would we invite 0 guests,there's
     //only one way, we can't return 0 as it'll cause problem in the logic below.
         if(n <=1){
             return 1;
         }
        //Invite guest single
        int singleWays = callGuests(n-1);

        //Invite in paris.
        int pairWays = (n-1) * callGuests(n-2);

        return singleWays + pairWays;
    }
    
    public static void printSubsets(int n,ArrayList<Integer> arrList){
        if(n == 0){
           System.out.println(arrList);
           return;
        }

        //If element wants to come.
        arrList.addLast(n);
        printSubsets(n-1, arrList);

        //If element doens't want to come.
          arrList.removeLast();
          printSubsets(n-1, arrList);
        
        
    }
    public static void main(String[] args) {
        System.out.println("Hello everyone!");

        //PRINT ALL PERMUTATIONS OF A STRING.

        //String str = "abc";
        //String newStr = "";
        //permutatios(str, newStr);


        //COUNT ALL THE PATHS TO MOVE IN A MATRIX.
       // int i=0;
       // int j=0;
       // int n=3;
       // int m=4;
        //System.out.println(countPaths(i,j, n, m));;


        //PLACE TILES OF SIZE 1XM ON FLOOR OF SIZE NxM.

        //int n=4;
        //int m=2;
        //System.out.println( placeTiles(n,m));


        //INVITE GUESTS TO A PARTY.

        //int n = 4;
        //System.out.println(callGuests(10));


        //PRINT SUBSETS OF A SET OF FIRST N NATURAL NUMBERS.

        int n = 5;
        ArrayList<Integer> arrList = new ArrayList<>();
        printSubsets(n, arrList);
    }
}
