
public class LinkedListDS {
        Node head;

        private int size;
        
        //Contructor of the class.
        LinkedListDS(){
             this.size = 0;
        }
    
        class Node{
             String data;
             Node next;
    
             //Create ur constructor.
             Node(String data){
                this.data = data;
                this.next = null; //by default next of all the nodes is null.
                size++;
             }
        }
    
        //addFirst 
    
        public void addFirst(String data){
            //Create the new node.
            Node newNode = new Node(data);
    
            if(head == null){
                head = newNode;
                return;
            }
            
            newNode.next = head;
            head = newNode;
            
        }
       
        //addLast
        public void addLast(String data){
            Node newNode = new Node(data);
    
            if(head == null){
                head = newNode;
                return;
            }
    
            Node currNode = head;
            //We can't just directly access the last index of linkedlist, that's why we've to go there by traversing over it.
            while(currNode.next != null){
                currNode = currNode.next;
            }
            
            //When it's the last node.
            currNode.next = newNode;
        }
    
        //print.
    
        public void printList(){
            if(head == null){
                System.out.println("Linked list is empty");
                return;
            }
    
            Node currNode = head;
            
            //Traverse on the whole list.
            while(currNode != null){
                System.out.print(currNode.data + "->");
                currNode = currNode.next;
            }
    
            System.out.println("NULL");
        }

        //deleteFirst.

        public void deleteFirst(){
             if(head == null){
                 System.out.println("Linked List is empty");
                 return;
             }
             
             size--;
             head = head.next;
        }
        
        //deleteLast.

        public void deleteLast(){
            if(head == null){
                System.out.println("Linked List is empty");
                return;
            }
            
            size --;
            //If there's only one element in linked list.
            if(head.next == null){
                 head = null;
                 return;
            }
            
            //Traverse and keep checking.
            Node secondLast = head;
            Node lastNode = head.next;
            while(lastNode.next != null){
                 lastNode = lastNode.next;
                 secondLast = secondLast.next;
            }

            secondLast.next = null;
        }

        public int getSize(){
             return size;
        }

        public Node reverseIterate(Node head){
            if(head == null || head.next == null){
                 return head;
            }
             
            Node prevNode = head;
            Node currNode = head.next;

            while(currNode !=null){
                 Node nextNode = currNode.next;
                 //Change the pointer.
                 currNode.next = prevNode;

                 //Move forward in the linked list.
                 prevNode = currNode;
                 currNode = nextNode;
            }
            
            //See the last condition in diagram when the currNode has become null,prevNode will be the head.
            head.next = null;
            head = prevNode;
            return head;
        }

        public Node reverseRecursive(Node head){
            //First call will start with the head of the un-reversed linked list.
             //System.out.println(head.data); 
             if(head == null || head.next == null){
                 return head;
             }
            
             //First it'll go to linkedlist end and the last node of linked list will be returned that will
             //be stored in the newHead as list is being reversed, now as recursive calls will resolve one by
             //one on each step, the pointers will keep changing.
             //head represent the current node.
             Node newHead = reverseRecursive(head.next);
             head.next.next = head;
             head.next = null;

             return newHead;
        }

        public void getPalindromic(){
             System.out.println(size);
        }

        public Node nthNodeFromLast(int n){
             if(head == null){
                 return null;
             }

             //Find the size of linkedList.
             int size = 0;
             Node currNode = head;
             while(currNode != null){
                 currNode = currNode.next;
                 size++;
             }
            
             Node currentNode = head;
             
             //Find the nth node from last/size-n+1 node from start.
             int i = 1; //Track the nodes of the linked list.
             int indexToSearch = size - n + 1;
             
             while(i<indexToSearch){
                currentNode = currentNode.next;
                i++;
             }
             return currentNode;
        }

        public Node deleteNthFromLast(int n){
             if(head.next == null){
                return null;
             }

             //Find the size of the linked list.
             int size = 0;
             Node currNode = head;
             while(currNode != null){
                 currNode = currNode.next;
                 size++;
             }

             if(n == size){
                 return head.next;
             }
             
             Node prev = head;
             int i = 1;
             int indexToSearch = size - n;
             while(i<indexToSearch){
                 prev = prev.next;
                 i++;
             }
             
             //Change the pointer
             prev.next = prev.next.next;
             return head;
             
        }
        
        //My solution.
        public boolean checkPalindromic(){
            if(head == null || head.next == null){
                 return false;
            }
             int nodesToTraverse = 0;
             boolean isPalindromic = false;

             if(size % 2 == 0){
                 nodesToTraverse = size/2;
             }
             if(size % 2 != 0){
                 nodesToTraverse = (size/2) + 1;
             }
            System.out.println(nodesToTraverse);

             int i = 1; //To track node.
             Node startNode = head;

             while(i <= nodesToTraverse){
                 //Get the node from end.
                 Node endNode = nthNodeFromLast(i);
                 if(startNode.data == endNode.data){
                    startNode = startNode.next;
                    isPalindromic = true;
                    i++;
                 }else{
                    isPalindromic = false;
                     break;
                 }
             }
             return isPalindromic;
        }

        public Node getMiddle(){
            Node turtle = head;
            Node hare = head;

            while(hare.next != null && hare.next.next != null){
                 turtle = turtle.next; //turtle takes 1 step.
                 hare = hare.next.next; //hare takes 2 steps.
            }

            return turtle;
        }

        //Mam's solution.
        public boolean isPalindromic(){
             if(head == null || head.next == null){
                 return true;
             }

             //Find the middle.
             Node middle = getMiddle(); //end of the first half.
             Node secondHalfStart = reverseIterate(middle.next); //Reverse the second half.
             Node firstHalfStart = head;
             //Start comparing now.
             while(secondHalfStart != null){
                 if(firstHalfStart.data != secondHalfStart.data){
                     return false;
                 }
                 firstHalfStart = firstHalfStart.next;
                 secondHalfStart = secondHalfStart.next;
             }

             return true;
        }

        public boolean detectCycle(){
             if(head == null){
                 return false;
             }

             Node turtle = head; //slow,takes 1 step at a time.
             Node hare = head; //fast,takes 2 steps at a time.

             while(hare != null && hare.next != null){
                 turtle = turtle.next;
                 hare = hare.next.next;
                 
                 //If hare and turtle meet at some point, means cycle exists.
                 if(hare == turtle){
                     return true;
                 }
             }

             return false;
        }

        //Removing cycle from the linked list.
        public Node meetPoint(Node head){
             Node slow = head;
             Node fast = head;
             Node prev = null; //It'll keep a track of the prev node of fast or slow pointer & we'll use this to remove cycle.
             while(fast != null && fast.next != null){
                 slow = slow.next;
                 prev = fast;
                 fast = fast.next.next;

                 if(slow == fast){ 
                    return slow;
                 }
             }

             return new Node[]{null,prev};
        }

        public void removeCycle(){
            if(head == null || head.next == null){
                 return null;
            }
            //Previous node of cycleStartNode, so that we can break the cycle.
            //Just find a way to define it properly in a singular linked list and u can break the cycle.
             //Node prev = null;
             Node meet = meetPoint(head);
             Node start = head;
            //Start node of the cycle.
            Node cycleStartNode = null;
             while(start != meet){
                
                 //prev = prev.next;
                 start = start.next;
                 meet = meet.next;
                 
             }
             cycleStartNode = start;
             //When they have met
             prev.next = null;
             return cycleStartNode;
        }

    public static void main(String[] args) {
        System.out.println("We're studying Linked Lists today.");

        LinkedListDS list = new LinkedListDS();
        list.addFirst("a");
        list.addFirst("is");
        //list.addFirst("list");
        list.addFirst("is");
        list.addFirst("a");
        list.printList();
       
        //list.reverseIterate();
        //update the head, so that now list is printed in reverse order.
        //list.head = list.reverseRecursive(list.head);
        //list.printList();

        //System.out.println(list.nthNodeFromLast(2).data);
        //list.printList();
        //list.deleteNthFromLast(2);
        //list.printList();
        //System.out.println(list.checkPalindromic());
        //System.out.println(list.isPalindromic());
        System.out.println(list.detectCycle());
    }

    
}
