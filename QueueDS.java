import java.util.*;

class QueueUsingArray{
     int[] arr;
     int size;
     int rear = -1; //Initialize with -1 as the queue is empty.

     public QueueUsingArray(int n){
         arr = new int[n];
         this.size = n;
     }

     public boolean isEmpty(){
         return rear == -1;
     }

     //Enque - O(1)
     public void add(int data){
         if(rear == size -1){
             System.out.println("Queue is already full!");
             return;
         }

         rear++;
         arr[rear] = data;
     }
    
    //Front - O(1)
     public int peek(){
         if(isEmpty()){
             System.out.println("Queue is empty!");
             return -1;
         }

         int front = arr[0];
         return front;
     }
    
     //Dequeue - O(n)
     public int remove(){
         if(isEmpty()){
             System.out.println("Queue is empty!");
             return -1;
         }

         int front = arr[0];
         for(int i=0;i<rear;i++){
            //Push each element one step forward.
            arr[i] = arr[i+1];

         }
         rear--;
         return front;
     }
}


class CircularQueueUsingArray{
     int[] arr;
     int size;
     int rear = -1; //Initialize with -1 as the queue is empty.
     int front = -1;

     public CircularQueueUsingArray(int n){
         arr = new int[n];
         this.size = n;
     }

     public boolean isEmpty(){
         return rear == -1 && front == -1;
     }

     public boolean isFull(){
         return (rear+1) % size == front;
     }

     //Enque -O(1)
     public void add(int data){
         if(isFull()){
             System.out.println("Queue is already full!");
             return;
         }
         
         if(front == -1){
             front = 0;
         }
         
            rear = (rear+1) % size;
            arr[rear] = data;
         
         
     }
    
    //Front-O(1)
     public int peek(){
         if(isEmpty()){
             System.out.println("Queue is empty!");
             return -1;
         }

         
         return arr[front];
     }
    
     //Dequeue-O(1)
     public int remove(){
         if(isEmpty()){
             System.out.println("Queue is empty!");
             return -1;
         }
         //If there's only one element left.
         int result = arr[front];

         if(front == rear){
             front = rear = -1;
         }
        else{
            front = (front+1) % size;
        }
         
         return result;
     }
}

class Node{
     int data;
     Node next;

     public Node(int data){
         this.data = data;
         this.next = null;
     }
}

class QueueUsingLL{
    static  Node head = null;
    static  Node tail = null;

     public boolean isEmpty(){
         return (head == null && tail == null);
     }
     
     //Enque - O(1)
     public void add(int data){
         Node newNode = new Node(data);

         //If the queue is empty.
         if(isEmpty()){
             head = tail = newNode;
             return;
         }
         tail.next = newNode;
         tail = newNode;

     }
     
     //Front - O(1)
     public int peek(){
          if(isEmpty()){
             System.out.println("Queue is empty");
             return -1;
          }

          int front = head.data;
          return front;
     }

     //Dequeue - O(1)
     public void remove(){
         if(isEmpty()){
             System.out.println("Queue is empty");
             return;
         }

         //If there's only a single element.
         if(head == tail){
            tail = null;
         }

         head = head.next;
     }
}

class QueueUsingStacks{
     Stack<Integer> s1 = new Stack<>();
     Stack<Integer> s2 = new Stack<>(); //Will be used as a helper.

    public boolean isEmpty(){
        return s1.empty();
    }

     public void add(int data){
         if(s1.empty()){
             s1.push(data);
             return;
         }
         
         while(!s1.empty()){
             int currEl = s1.pop();
             s2.push(currEl);
         }
         
         s1.push(data);

         while (!s2.empty()) {
            int currEl = s2.pop();
            s1.push(currEl);
         }
     }

     public int peek(){
         if(s1.empty()){
             System.out.println("Queue is empty");
             return -1;
         }

         return s1.peek();
     }

     public void remove(){
        if(s1.empty()){
            System.out.println("Queue is empty");
            return ;
        }

        s1.pop();
     }
}

public class QueueDS {
    public static void main(String[] args) {
        System.out.println("HEllo everyone!");

        QueueUsingArray q = new QueueUsingArray(5);
        q.add(1);
        q.add(2);
        q.add(3);
        

        while(!q.isEmpty()){
            System.out.println(q.peek());
             q.remove();
        }

        CircularQueueUsingArray cq = new CircularQueueUsingArray(5);
        cq.add(1);
        cq.add(2);
        cq.add(3);
        cq.add(4);
        cq.add(5);
        cq.remove();
        cq.remove();
        cq.add(6);
        cq.add(7);

       while(!cq.isEmpty()){
         System.out.println(cq.peek());
         cq.remove();
       }

    Queue using LL.
    QueueUsingLL q = new QueueUsingLL();
    
     

    Queue using collections fromework.
    Queue<Integer> q = new LinkedList<Integer>();
    Queue<Integer> q2 = new ArrayDeque<Integer>();
    q.add(0);
    q.add(1);
    q.add(2);
    q.remove();
    System.out.println(q.isEmpty());
    System.out.println(q.peek());

    Queue using two stacks.
     
    QueueUsingStacks q = new QueueUsingStacks();
    q.add(1);
    q.add(2);
    q.add(3);
    q.add(4);
    q.remove();

    while (!q.isEmpty()) {
        System.out.println(q.peek());
        q.remove();
    }

    

    }
}
