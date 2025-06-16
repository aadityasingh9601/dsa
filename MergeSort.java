public class MergeSort {
    public static int[] conquer(int arr[],int si,int mid, int ei){
        
        //To merge arrays after sorting them properly,compare and sort two arrays at a time.
        int merger[] = new int[ei-si+1]; 
        
        int idx1 = si; //To track first array.
        int idx2 = mid+1; //To track second array.
        int x = 0; //To track the merged array.

        //Now keep comparing the elements of two arrays and sort them.

        while(idx1<= mid && idx2 <=ei){
             if(arr[idx1] <= arr[idx2]){
                 merger[x] = arr[idx1];
                 //Move to next index.
                 x++; //As element is stored at current postion.
                 idx1++; //Compare to the next element now.
             }else{
                 merger[x] = arr[idx2];
                  x++; idx2++;
             }
        }

        //If we've reached the end of the first array, then push the rest of elements as it is in the merger
        //array as they're already sorted.
        while(idx2 <= ei){
             merger[x] = arr[idx2];
             x++; idx2++;
        }
        
        //Do the same for second array.
        while(idx1 <= mid){
            merger[x] = arr[idx1];
            x++; idx1++;
       }

       //Now finally copy all the elements of merger into orignal array as they're all sorted now.
       for(int i=0,j=si;i<merger.length;i++,j++){
           arr[j] = merger[i];
       }

       return arr;

    }

    public static void divide(int arr[], int si, int ei){
        if(si >= ei){ 
            return;
        }

        int mid = si + (ei-si)/2; //Find the mid index.

        divide(arr, si, mid); //First half.
        divide(arr, mid+1, ei); //Second half.
        conquer(arr,si,mid,ei);
    }
    public static void main(String[] args) {
        System.out.println("Hello everyone! ");
        
        int[] array = {3,4,8,0,9,1};
        int n = array.length;

        divide(array,0,n-1);
        
        //Earlier the wrong array was getting printed as you were trying to print it inside the divide fn
        //itself, where it hasn't sorted yet and the unsorted array was getting printed multiple times
        //so next time remember to print only outside of recursive calls.
        
        for(int num:array){
             System.out.print(num + " ");
        }

    }
}
