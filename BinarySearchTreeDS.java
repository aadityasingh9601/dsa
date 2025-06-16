import java.util.ArrayList;

class Node{
    int data;
    Node left;
    Node right;

    Node(int data){
         this.data = data;
         this.left = null;
         this.right = null;
    }
}

class BinarySearchTree {
    int idx = 1;

     public Node insertNode(Node root,int val){
          if(root == null){ 
              root = new Node(val);
              return root;
          }

          if(root.data > val){
             //Inset into left subtree.
             root.left = insertNode(root.left,val);
          }
          else{
              //Insert into right subtree.
              root.right = insertNode(root.right,val);
          }

          return root;
     }

     public void inorderTraversal(Node root){
           if(root == null){
              return;
           }

           inorderTraversal(root.left);
           System.out.print(root.data + " ");
           inorderTraversal(root.right);
     }

     public boolean searchNode(Node root,int val){
        //If key doesn't exists, because not found here, then it's nowhere as we've searched the whole BST 
        //logically till this point.
          if(root == null){
              return false;
          }
          
          if(root.data > val){
             //Search in the left subtree.
             return searchNode(root.left, val);
          }
          else if(root.data == val){
            return true;
        }
        else{
            return searchNode(root.right, val);
        }
        
     }
    
     public Node inOrderSuccessor(Node root){
         //IOS = left most node in the subtree.
         while(root.left != null){
              root = root.left;
         }

         return root;
     }

     public Node deleteNode(Node root, int val){
           //We'll use binary search to find the node to be deleted first.
           if(root.data > val){
                //the node we've to delete must be present in the left subtree.
                root.left = deleteNode(root.left, val);
           }
           else if(root.data < val){
               //the node we've to delete must be present in the right subtree.
               root.right = deleteNode(root.right, val);
           }
           //Case where the current node is the node we've to delete, i.e. root.data == val.
           //So we'll delete the node now.
           else{
              //case 1 : It's leaf node.
              if(root.left == null && root.right == null){
                  return null;
              }

              //Case 2 : It has one child, either left or right.
              if(root.left == null){
                  return root.right;
              }
              if(root.right == null){
                  return root.left;
              }

              //Case 3 : It has two child.
              Node IOS = inOrderSuccessor(root.right);
              //Replace the data of the root with the data of IOS.
              root.data = IOS.data;
              //Remove the actual IOS node from the right subtree.
              root.right = deleteNode(root.right, IOS.data);
           }

           return root;
     }

     public void printInRange(Node root,int X, int Y){
        //if root is null.
        if(root == null){
             return;
        }

          if(root.data >= X && root.data <= Y){
              printInRange(root.left, X, Y);
              System.out.print(root.data + " ");
              printInRange(root.right, X, Y);
          }
          else if(root.data < X){
              printInRange(root.right, X, Y);
          }
          else{ //root.data > Y
              printInRange(root.left, X, Y);
          }
     }
     
     public void rootToLeafPaths(Node root,ArrayList<Integer> paths){
          if(root == null){
               return;
          }

          //Start traversing.
          paths.add(root.data);
        
          //If we're at leaf node, don't traverse further and just print the path.
          if(root.left == null && root.right == null){
              System.out.println(paths); 
          }
          //For non-leaf nodes, traverse further.
          else{
            rootToLeafPaths(root.left,paths);
            rootToLeafPaths(root.right,paths);
          }
          paths.remove(paths.size()-1);

     }
}



public class BinarySearchTreeDS {
    public static void main(String[] args) {
        System.out.println("Hello everyone");
        //Initialize the root of tree as null at start.
        Node root = null;
        int[] values = {5,1,3,4,2,7};

        BinarySearchTree bst = new BinarySearchTree();
        
        for(int i=0; i<values.length;i++){
              root = bst.insertNode(root,values[i]);
        }

        //Do inOrder traversal to verify if our tree is built properly or not.
        bst.inorderTraversal(root);
        //System.out.println(bst.searchNode(root, 3));
       // bst.deleteNode(root, 4);
       System.out.println("");
       // bst.inorderTraversal(root);

       //bst.printInRange(root, 3, 5);
        //Create our arrayList to store paths.
        ArrayList<Integer> paths = new ArrayList<>();
       bst.rootToLeafPaths(root,paths);


    
    }
}
