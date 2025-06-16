
import java.util.LinkedList;
import java.util.Queue;

class Node {
     int data;
     Node left;
     Node right;

     Node(int data){
         this.data = data;
         this.left = null;
         this.right = null;
     }
}

class TreeInfo{
     int ht;
     int diam;

     TreeInfo(int ht,int diam){
         this.diam = diam;
         this.ht = ht;
     }
}


class BinaryTree{
      int idx = -1;

      public Node buildTree(int[] nodes){
         idx++;

         if(nodes[idx] == -1){
             return null;
         }
         
         Node newNode = new Node(nodes[idx]);
         newNode.left = buildTree(nodes);
         newNode.right = buildTree(nodes);

         return newNode; //Returns our root node if tree is contructed properly.
      }

      public void preorderTraversal(Node root){
          if(root == null){
             System.out.print(-1 + " ");
             return;
          }

          System.out.print(root.data + " ");
          preorderTraversal(root.left);
          preorderTraversal(root.right);
      }

      public void inorderTraversal(Node root){
           if(root == null){
             return;
           }

           inorderTraversal(root.left);
           System.out.print(root.data + " ");
           inorderTraversal(root.right);
      }

      public void postOrderTraversal(Node root){
         if(root == null){
             return;
         }

         postOrderTraversal(root.left);
         postOrderTraversal(root.right);
         System.out.print(root.data + " ");
      }

      public void levelOrderTraversal(Node root){
          if(root == null){
             System.out.println("The tree is empty!");
             return;
          }
          //Create our queue.
          Queue<Node> q = new LinkedList<Node>();

          //Starting with the root node of tree.
          q.add(root);
          q.add(null);

          //Iterate over the tree and the queue till the q is completely empty.
          while(!q.isEmpty()){
              Node currNode = q.remove();

              if(currNode == null){
                //Print a line.
                 System.out.println("");
                 if(q.isEmpty()){
                      return;
                 }
                 q.add(null);
                 
              }else{
                System.out.print(currNode.data + " ");
                if(currNode.left != null){
                    q.add(currNode.left);
                }
                if(currNode.right != null){
                    q.add(currNode.right);
                }
              }
          }
      }

      public int countNodesOfTree(Node root){
          if(root == null){
              return 0;
          }

         int leftCount =  countNodesOfTree(root.left);
         int rightCount = countNodesOfTree(root.right);

         return leftCount + rightCount +1;

      }

      public int sumOfNodes(Node root){
         if(root == null){
             return 0;
         }

         int leftSum = sumOfNodes(root.left);
         int rightSum = sumOfNodes(root.right);

         return leftSum + rightSum + root.data;
      }

      public int heightOfTree(Node root){
          if(root == null){
             return 0;
          }

          int leftHeight = heightOfTree(root.left);
          int rightHeight = heightOfTree(root.right);
          
          int myHeight = Math.max(leftHeight,rightHeight) + 1;
          return myHeight;
      }
      
     //T.C. = O(N^2), as we're calling external function for calculation our height.
      public int diameterOfTree1(Node root){
          if(root == null){
              return 0;
          }

          //If the diameter exists in left subtree.
          int diam1 = diameterOfTree1(root.left);
          //If the diameter exists in right subtree.
          int diam2 = diameterOfTree1(root.right);
          //If the diameter passes through the node.
          int diam3 = heightOfTree(root.left) + heightOfTree(root.right) + 1;

          //Return the max of the three.
          int greatest = Math.max(diam1,Math.max(diam2,diam3));

          return greatest;
      }
     
      //T-C. = O(N), as we're calculating both height and diameter for each node manually, so repetitive calls
      //are not here.
      public TreeInfo diameterOfTree2(Node root){
          if(root == null){
             return new TreeInfo(0, 0);
          }
          
          //Like we used to calculate the height and diameter for both left and right subtree, here we're 
          //calculating treeInfo means both height and diameter at once.
          TreeInfo left = diameterOfTree2(root.left);
          TreeInfo right = diameterOfTree2(root.right);

          int myHeight = Math.max(left.ht,right.ht) + 1;

          int diam1 = left.diam;
          int diam2 = right.diam;
          int diam3 = left.ht + right.ht + 1;

          int myDiameter = Math.max(diam1,Math.max(diam2,diam3));

          TreeInfo tf = new TreeInfo(myHeight, myDiameter);

          return tf;
      }

      public boolean isIdentical(Node root1,Node root2){
        //If we've reached the leaf node, or to check if the nodes are both null or not.
            if(root1 == null && root2 == null){
                  return true;
            }
        //If one is ended but the other isn't so return false.
            if(root1 == null || root2 == null){
                 return false;
            }

            if(root1.data == root2.data){
                //If roots match, check their left and right subtree.
                return  (isIdentical(root1.left, root2.left) && isIdentical(root1.right, root2.right));
            }
            else{
                return false;
            }
            
            

      }

      public boolean subTree(Node root1,Node root2){
        //root1 = main tree root, root2 = subtree root.
        //We'll reach the end of subtree only when all nodes of it are compared already and matched.
        //Or even if it's empty at start , then it'll match too as null roots are present in a tree.
           if(root2 == null){
                return true;
           }
           //If the big tree is null means it doesn't contains the subtree as it's already null and 
           //the subtree isn't matched yet.
           if(root1 == null){
               return false;
           }

           if(root1.data == root2.data){
               //If roots match then check if the left and right subtrees of both are identical or not.
               //We'll not call subTree function here as subTree function checks if the given tree is inside 
               //the big tree or not, IT'S DOESN'T CHECKS THAT THE GIVEN TREES ARE IDENTICAL OR NOT.
               return (isIdentical(root1.left, root2.left) && isIdentical(root1.right, root2.right));
           }
           else{
              //We've used logical OR because we've to find the subtree, it doens't matter if we find it in
              //the left part or the right part of the main tree, if we find the subtree in either of the main
              //tree parts(left or right) return true.
              return (subTree(root1.left,root2) || subTree(root1.right, root2));
           }
      }
 
    //   public void sumOfNodesAtKthLevel(Node root,int k){
    //       if(root == null){
    //           System.out.println("This tree is empty!");
    //           return;
    //       }

    //       if(k > heightOfTree(root)){
    //         System.out.println("Level " + k + " doesn't exists in this tree.");
    //            return;
    //       }

    //       int level = 1;
    //       int sumOfNodes = 0;

    //       //Create your queue.
    //       Queue<Node> q = new LinkedList<Node>();

    //       //Start your queue.
    //       q.add(root);
    //       q.add(null);

    //       while(k >= level){
    //           //Remove one element from the queue.
    //           Node currNode = q.remove();
              
    //           //if the currNode is null.
    //           if(currNode == null){
                
    //                if(q.isEmpty()){
    //                     return;
    //                }
    //                q.add(null);
    //           }else{
    //             System.out.println("At level" + level);
    //               //Add the currNode children to the queue.
    //               //this won't work because this is an infinite while loop as level isn't increasing anywere
    //               //inside this loop, also level was getting incrementing the wrong way before, it was
    //               //getting incremeneted for each node, that causes the control to never reach here.
    //               while(level == k){
    //                    sumOfNodes = sumOfNodes + currNode.data;
    //                    System.out.println(sumOfNodes);
    //                    continue;
    //               }

    //               if(currNode.left != null){
    //                 q.add(currNode.left);
    //             }
    //             if(currNode.right != null){
    //                 q.add(currNode.right);
    //             }
                 
    //           }
    //       }
    //       System.out.println(sumOfNodes);
    //       level++;
          
    //   }

     public void sumOfNodesAtKthLevel(Node root,int k){
        if(root == null){
              System.out.println("The tree is empty!");
              return;
        }
          if(k > heightOfTree(root)){
              System.out.println("Level " + k + " doesn't exists in the tree");
              return;
          }

          int level = 1;
          int sumOfNodes = 0;

          //Create our queue.
          Queue<Node> q = new LinkedList<Node>();

          q.add(root);

          while(!q.isEmpty()){
             int size = q.size(); //get the size of current level.

             //Process all the nodes of the current level.
             for(int i=1;i <= size; i++){
                 Node currNode = q.remove();
                 
                 //If we're at the desired level, then add all the nodes of that level.
                 if(level == k){
                    sumOfNodes += currNode.data;
                 }

                 if(currNode.left != null) q.add(currNode.left);
                 if(currNode.right != null) q.add(currNode.right);

             }

             if(level == k){
                 break;
             }
             level++;
          }

          System.out.println(sumOfNodes);
     }


    }


public class TreesDS {
    public static void main(String[] args) {
        System.out.println("Hello everyone");
        
        BinaryTree bt = new BinaryTree();
        int[] nodes = {1,2,4,-1,-1,5,-1,-1,3,-1,6,-1,-1};
        Node rootNode = bt.buildTree(nodes);

        BinaryTree bt2 = new BinaryTree();
       
        int[] nodes2 = {2,4,-1,-1,5,-1,-1};
        Node rootNode2 = bt2.buildTree(nodes2);
        //System.out.println(rootNode.data);

        //bt.preorderTraversal(rootNode);
        //bt.inorderTraversal(rootNode);
        //bt.postOrderTraversal(rootNode);
        //bt.levelOrderTraversal(rootNode);

        //int totalCount = bt.countNodesOfTree(rootNode);
        //System.out.println(totalCount);

        //int totalSum = bt.sumOfNodes(rootNode);
        //System.out.println(totalSum);

        //System.out.println(bt.heightOfTree(rootNode));

        //System.out.println(bt.diameterOfTree1(rootNode));

        //System.out.println(bt.diameterOfTree2(rootNode).diam);

        //System.out.println(bt.subTree(rootNode, rootNode2));;

        bt.sumOfNodesAtKthLevel(rootNode,5);
    }
}
