public class TrieDS {
    static class Node{
        Node[] children; //To stores all children of a node.
        boolean eow;

        public Node(){
             children = new Node[26]; //Initialise the empty children array.

             for(int i=0; i<26;i++){
                children[i] = null;
             }
             eow = false;
        }
    }

    static Node root = new Node(); //Create empty root node for our trie.

    public static void insert(String word){ //T.C. = O(l)
        Node curr = root;
        //Loop over the world.
        for(int i=0;i<word.length();i++){
            //Check if the curr char is already present in the next level of trie or not.
            int idx = word.charAt(i) - 'a'; //Find index of curr word in children array.
            
            if(curr.children[idx] == null){
                //Create a new node.
                curr.children[idx] = new Node();
            }
            //If it's the last char of our word.
            if(i == word.length() - 1){
                curr.children[idx].eow = true; //End of word.
            }
            //Move to the next children node, update the root.
            curr = curr.children[idx];
        }
    }

    public static boolean search(String key){ //T.C. = O(L)
        Node curr = root;

         for(int i=0;i<key.length();i++){
               int idx = key.charAt(i) - 'a';
               
               if(curr.children[idx] == null){
                   //System.out.println(key + " doesn't exists in the TRIE.");
                   return false;
               }
               if(i == key.length()-1 && curr.children[idx].eow == false){
                    return false;
               }
               curr = curr.children[idx]; //update the root to move in our trie further.
         }

         return true;
    }
    
    public static boolean wordBreak(String input){
        if(input.length() == 0){
            return true;
       }

        for(int i=1;i<=input.length();i++){
            //As empty string always exists.
            
             String subStr1 = input.substring(0,i);
             String subStr2 = input.substring(i);
             
             //Return true only if both of them returns true.
             if(search(subStr1) && wordBreak(subStr2)){
                 return true;
             }
        }
        return false;
    }
    
    public static boolean startsWith(String prefix){
          Node curr = root;

          for(int i=0;i<prefix.length();i++){
             int idx = prefix.charAt(i) - 'a';
             //Check if it exists in the trie or not.
             if(curr.children[idx] == null){
                 return false;
             }
                //Update the root.
                curr = curr.children[idx];
          }
          
          return true;
    }

    public static int countNodes(Node root){
        if(root == null){
             return 0;
        }

         int count = 0;

         for(int i=0;i<26;i++){
            //Get the count of nodes of all the children and add the current node count and return final.
            if(root.children[i] != null){
                //count all the children of a node.
                count += countNodes(root.children[i]);
            }
         }
         return count +1;
    }

    public static int uniqueSubstrings(String s){
         Node curr = root;
        //First of all, find all substrings of our s.
         for(int i=0;i<s.length();i++){
             //Find suffixes and create a trie from all the suffixes.
             insert(s.substring(i));
         }

         //Now count the nodes of TRIE as they'll be equal to the unique prefixes of suffixes Trie.
        int totalUniqueSubstrings =  countNodes(root);

        return totalUniqueSubstrings;
    }

    static String ans = "";

    public static void longestWord(Node root,StringBuilder temp){
         if(root == null){
             return;
         }

         //We'll traverse over our TRIE and try to find the longest word.
         //As loop is running in alphabetical order it's certain that even if multiple answers are there,we'll
         //get the answer that's lexicographically smaller.
         for(int i=0;i<26;i++){
              if(root.children[i] != null && root.children[i].eow == true){
                  //Add the char to the temp.
                   temp.append((char)(i + 'a'));
                  //Compare with the final string and update it accordingly.
                   if(temp.length() > ans.length()){
                       ans = temp.toString();
                   };
                  //Make recursive call to find in the children.
                  longestWord(root.children[i], temp);
                  //While backtracking we also have to remove the value at each level, so as to keep track of 
                  //the new values.
                  temp.deleteCharAt(temp.length()-1);
              }
         }
    }

    public static void main(String[] args) {
        System.out.println("Hello everyone!");
        //String[] words = {"the","a","there","their","any"};
        // String[] dictionary = {"i","like","sam","samsung","mobile","ice"};
        //String[] words2 = {"app","apple","mango","man","woman"};
        String[] words3 = {"a","banana","app","appl","ap","apply","apple"};

        for(int i=0;i<words3.length;i++){
             insert(words3[i]);
        }

        //System.out.println(search("the"));
        //System.out.println(search("an"));
        //System.out.println(wordBreak("ilikesamsung"));
        //System.out.println(startsWith("moon"));
        //System.out.println(uniqueSubstrings("ababa"));
        //We've used String builder for our temp operation as T.C. of diff. operation on this is less then 
        //that of a normal string.
        StringBuilder sb = new StringBuilder("");
        longestWord(root,sb);
        System.out.println(ans);
        
    }
}
