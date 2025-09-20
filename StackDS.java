import java.util.*;

public class StackDS {
    static class Node{
         int data;
         Node next;

         //create your constructor.
         Node(int data){
             this.data = data;
             this.next = null;
         }
    }

    static class Stack{
        static Node head;

         static boolean isEmpty(){
             return head == null;
         }

         public void push(int data){
             //Create your new node.
             Node newNode = new Node(data);

             if(isEmpty()){
                 head = newNode;
                 return;
             }

             newNode.next = head;
             head = newNode;
         }

         public int pop(){
             if(isEmpty()){
                 return -1; //the stack is empty.
             }
             
             int top = head.data;
            
             head = head.next; //Update the head.
             return top;
         }

         public int peek(){
             if(isEmpty()){
                return -1;
             }

             int top = head.data;
             return top;
         }
    }

    public static void pushAtStackBottom(Stack<Integer> s,int e){
         if(s.empty()){
             s.push(e);
             return;
           }
             
         int currTop = s.peek();
         s.pop();
         pushAtStackBottom(s, e);
         s.push(currTop);
    }

    public static void reverseStack(Stack<Integer> s){
        if(s.empty()){
             return;
        }

        int currEl =  s.pop();
        reverseStack(s);
        pushAtStackBottom(s, currEl);
    }

    public static void main(String[] args) {
        System.out.println("Hello everyone");
        //Similarly you can implement stack using arrayList also yourself, DIY, if needed.
        
        //Now we'll implement Stack using collections framework.
        Stack<Integer> s = new Stack<>();
        s.push(1);
        s.push(2);
        s.push(3);
        
        //pushAtStackBottom(s, 4);
        reverseStack(s);

        while(!s.empty()){
             System.out.println(s.peek());
             s.pop();
        }

       
       
       
    }
}
