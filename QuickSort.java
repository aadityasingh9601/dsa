public class QuickSort {

    public static int partition(int[] arr,int low,int high){
        
        //Find the pivot,i.e. last element of the array.
        int pivot = arr[high];
        int i = low - 1; //Responsible to track and swap elements smaller than pivot before the pivot.

        //Run a loop on all the elements except the pivot to place them correctly.
        for(int j=low;j<high;j++){
             if(arr[j] < pivot){
                //Swap the elements.
                 i++; //Make space for the smaller element.
                 int temp = arr[i]; //store the existing el at that place in temp.
                 arr[i] = arr[j]; //store the smaller el there.
                 arr[j] = temp; //store the existing el at that smaller el place 
             }
        }
        //Once done, also put the pivot at the right place.
        i++;
        int temp = arr[i];
        arr[i] = pivot;
        arr[high] = temp;

        return i;
    }

    public static void quickSort(int[] arr,int low, int high){
        //Don't perform the task if the arr has length 1, means sorted already.
          if(low < high){
              int pidx = partition(arr,low,high); //Get the pivot index.
              
              //Recursively pivot and sort further.
              quickSort(arr, low, pidx-1);
              quickSort(arr, pidx+1, high);
          }
    }
    public static void main(String[] args) {
        System.out.println("Hello everyone!");
        
        int[] arr = {2,4,9,3,6,1,5};
        int n = arr.length;

        quickSort(arr,0,n-1);
        
        //Print the sorted array.
        for(int k = 0;k<arr.length;k++){
             System.out.print(arr[k] + " ");
        }
    }
}
