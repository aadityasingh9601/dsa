import java.util.HashSet;

public class Recursion2 {
    public static void towerOfHanoi(int n,String src,String help,String dest){
        //Base condition, when does recursion has to stop.
        if(n==1){
            System.out.println("Transfering disk " + n + " from " + src + " to " + dest);
            return;
        }
          
        //First of all transfer (n-1) disks from src to helper using dest as a helper.
        towerOfHanoi(n-1, src,dest,help);

        //Then transfer first disk from src to dest.
        System.out.println("Transfering disk " + n + " from " + src + " to " + dest);

        //Last, transfer those (n-1) disks from helper (taking it as src) to dest, using src as a helper.
        towerOfHanoi(n-1,help, src, dest);
    }

    public static void reverseStr(String s,int idx){
        //Base condition.
        if(idx == 0){
             System.out.print(s.charAt(idx));
             return;
        }
         System.out.print(s.charAt(idx));
         reverseStr(s, idx-1);
    }

    public static int first = -1;
    public static int last = -1;

    public static void occInString(String s,int idx,char elem){
         char currChar = s.charAt(idx);

         if(currChar == elem){
             //If we've not find our fist occurence yet.
             if(first == -1){
                 first = idx;
                 last = idx;
             }
             else{
                 last = idx;
             }
         }

         if(idx == s.length()-1){
            System.out.println("First occ of " + elem + " is at index " + first);
            System.out.println("Last occ of " + elem + " is at index " + last);
             return ;
         }

         occInString(s, idx+1, elem);
    }

    public static boolean isSorted(int[] arr,int idx){

        if(idx == (arr.length -1)){
            //if all elements are checked.
             return true;
        }

        if(arr[idx] < arr[idx+1]){
            //Whatever the answer is , return is important here, as the type the fn returns is a boolean.
             return isSorted(arr, idx+1);
        }
        else{
             return false;
        }
     }
    
    public static void moveAlltoEnd(String str,int count,int idx,char elem,String newStr){
         if(idx == str.length()){
             for(int i=1;i<=count;i++){
                 newStr = newStr+elem;
             }
             System.out.println(newStr);
             return;
         }

         char currChar = str.charAt(idx);

         if(currChar == elem){
             count++;
         }
         else{
            newStr = newStr + currChar;
         }

         moveAlltoEnd(str, count, idx+1, elem, newStr);
    }

    public static void removeDuplicates(String str,int idx,String newStr){
         
         if(idx == str.length()){
             System.out.println(newStr);
             return;
         }

         char currChar = str.charAt(idx);
         
         if(newStr.indexOf(currChar) == -1){
             newStr += currChar;
         }

         removeDuplicates(str, idx+1, newStr);
    }
     
    public static void printSubseq(String str,int idx, String newStr){
        if(idx == str.length()){
             System.out.println(newStr);
             return;
        }

        char currChar = str.charAt(idx);

         //if currChar wants to come
         printSubseq(str, idx+1, newStr+currChar);

         //if currChat doesn't wants to come
         printSubseq(str, idx+1, newStr);
    }
    
    public static void uniqueSubseq(String str,int idx, String newStr,HashSet myHash){
         if(idx == str.length()){
            if(myHash.contains(newStr)){
                 return;
            }
            else{
                System.out.println(newStr);
                myHash.add(newStr);
                return;
            }
         }

         char currChar = str.charAt(idx);

         //If currChar wants to come.
            uniqueSubseq(str, idx+1, newStr+currChar,myHash);

         //If currChar doesn't wants to come.
         uniqueSubseq(str, idx+1, newStr,myHash);
    }

    public static String[] keypad = {".","abc","def","ghi","jkl","mno","pqrs","tu","vwx","yz"};

    public static void keypadComb(String str,int idx,String combination){
        if(idx == str.length()){
             System.out.println(combination);
             return;
        }
        char currChar = str.charAt(idx);
        //We subtract the '0' as 0 equals 48 in deciaml. Ascii characters have their decimal/number value.
        String mapping = keypad[currChar - '0'];
         
         //The no. of choices for currStr can vary, so we'll generalise it using a for loop.
         for(int i=0;i<mapping.length();i++){
            keypadComb(str, idx+1, combination+mapping.charAt(i));
         }
    }

    public static void main(String[] args) {
        System.out.println("Hello everyone!");

        //TOWER OF HANOI.

        //int n = 4;
        //towerOfHanoi(n, "S", "H", "D");
        
        
        //PRINT A STRING IN REVERSE.

        //String name = "aaditya";
        //int n = name.length()-1;
        //reverseStr(name, n);

        

        //FIND THE FIRST AND LAST OCCURENCE OF A ELEMENT IN A STRING.
        //String random = "abaacdaefaah";
        //char elemToFind = 'a';
        //We initialized it with -1 as it's a invalid index in the string, if we have assigned 0 or 1 or maybe
        //something else, then we're not sure that the element we've to find is present at point or not,
        //so remember this tip for future, this is an important point to know from this question.
       
        //occInString(random, 0,elemToFind,first,last);
        //occInString(random, 0,elemToFind);

        //Also instead of passing first and last as a parameter,we'll just declare them as static variables
        //so they don't get created again and again in the memory because of recursion.
        
       
        //Check if an array is sorted or not(Strictly increasing).

        //int[] arr = {1,2,3,4,5};
        //System.out.println(isSorted(arr, 0));


        //MOVE ALL THE OCCURANCES OF THE SPECIFIED ELEMENT TO THE END.

        // String str = "axbxxcxdx";
        // char elemToMove = 'x';
        // int count = 0;
        // String newStr = "";
        // moveAlltoEnd(str, count, 0, elemToMove, newStr);



        //REMOVE DUPLICATES FROM A STRING.

       // String str = "abbccabdde";
       // String newStr = "";
       // removeDuplicates(str, 0, newStr);
        
       
       //PRINT ALL SUBSEQUENCES OF A STRING.

      //String str = "abc";
       //String newStr = "";
      // printSubseq(str, 0, newStr);


       //PRINT ALL UNIQUE SUBSEQUENCES OF A STRING.

       //String str = "aaa";
       // newStr = "";
       //We'll use a HashSet to store the subsequences as HashSet stores only unique values and removes the 
       //repetitive values.
       //HashSet<String> myHash = new HashSet<>();
      // uniqueSubseq(str, 0, newStr,myHash);



      //PRINT KEYPAD COMBINATIONS.
      //Instead of applying loops on an array , you can also pass a string like "23" to the function can we
      //create a keypad map to know the string for a particular char of the string.

     // String [] arr = {"abc","def"};
      String str = "911";
      String combination = "";
      keypadComb(str, 0, combination);
      



    }
}
