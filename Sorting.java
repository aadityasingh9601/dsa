public class Sorting {
    public static void printArr(int arr[]){
         for(int e:arr){
             System.out.print(e + " ");
         }
    }
    public static void main(String[] args) {
        System.out.println("Hello everyone!");

        int arr[] = {43,22,11,99,0};

        //BUBBLE SORTING.

        // for(int i=0;i<arr.length -1;i++){
        //      for(int j=0;j< arr.length-1-i;j++){
        //          if(arr[j] > arr[j+1]){
        //             //Swap the elemetns with each other.
        //              int temp = arr[j];
        //              arr[j] = arr[j+1];
        //              arr[j+1] = temp;
        //          }
        //      }
        // }

        
        //SELECTION SORT.

        // for(int i=0;i<arr.length - 1;i++){
        //      int smallest = i;

        //      for(int j=i+1;j<arr.length;j++){
        //           if(arr[smallest] > arr[j]){
        //             //Update the smallest index.
        //              smallest = j;
        //           }
        //      }
        //      //Update the array to put swap the current ith element with the current smallest in array.
        //      int temp = arr[smallest];
        //      arr[smallest] = arr[i];
        //      arr[i] = temp;
        // }


        //INSERTION SORT.

        //Applying loop on the unsorted part,taking the first element as the sorted part.

        for(int i=1;i<arr.length;i++){
             int current = arr[i]; //Element of unsorted to be put into sorted part.

             int j=i-1; //Taking the last index of the sorted part to compare to.

             while(j>=0 && arr[j] > current){
                 //Push the element of sorted part one place back to make place for the coming element.
                 arr[j+1] = arr[j];
                 j--;
             }

             //Placement of the "current" in the sorted part.
             arr[j+1] = current;
        }

        printArr(arr);

    }
}
