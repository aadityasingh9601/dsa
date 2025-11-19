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

    //Remove max el from heap.
    public int getMax(){
        
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

    }
}