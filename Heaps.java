import java.util.ArrayList;

//We're implementing a Max heap here.
class MaxHeap{
    //We'll use an arraylist to imlement our heap DS.
    private ArrayList<Integer> heap;

    //Constructor to initialize the heap
    public MaxHeap() {
        heap = new ArrayList<>();
    }

    private int parent(int i){
        return (i-1)/2;
    }

    private int left(int i){
        return 2*i + 1;
    }

    private int right(int i){
        return 2*i + 2;
    }

    private void swap(int i, int j){
        int temp = heap.get(i);
        heap.set(i,heap.get(j));
        heap.set(j,temp);
    }
    
    //Inset in heap.
    public void insert(int el){
        //Inset the new el at the last position.
        heap.add(el);

        //To restore the heap property, we need to heapify up.
        int current = heap.size()-1;
        while(current > 0 && heap.get(current) > heap.get(parent(current))){
            //Swap them.
            swap(current, parent(current));
            //Update the index of current.
            current = parent(current);
        }
    }

    //Heapify down.
    public void heapifyDown(int i){
        int largest = i;
        int leftChild = left(i);
        int rightChild = right(i);

        //Compare the current node with both of its children and see which is largest.

        if(leftChild < heap.size() && heap.get(leftChild) > heap.get(largest)){
            largest = leftChild;
        }

        if(rightChild < heap.size() && heap.get(rightChild) > heap.get(largest)){
            largest = rightChild;
        }

        if(largest != i){
            //Now whichever is largest, swap it to the parent position.
            swap(i,largest);
            //Heapify further for the children.
            heapifyDown(largest);
        }
    }

    //Remove max el from heap.
    public int getMax(){
        //Swap the max el with the last element.
        int max = heap.get(0);
        swap(0,heap.size()-1);
        //Remove the last element now.
        heap.remove(heap.size()-1);

        //To restore the heap property, heapify down.
        heapifyDown(0);

        return max;

    }

    //Peek max el of heap.
    public int peekMax(){
        if(heap.isEmpty()) return -1;

        return heap.get(0);
    }

    //Check if heap's empty or not.
    public boolean isEmpty(){
        return heap.isEmpty();
    }

}

public class Heaps{

    public static void main(String[] args) {
        System.out.println("Hello world!");
        
        MaxHeap maxHeap = new MaxHeap();
        maxHeap.insert(5);
        maxHeap.insert(10);
        maxHeap.insert(9);

        System.out.println(maxHeap.getMax());

        System.out.println(maxHeap.peekMax());

        
    }
}