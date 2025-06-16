public class CyclicSort {

    public static void cyclicSort(int[] arr){
         for(int i=0;i<arr.length;i++){
             //Keep swapping until the current el is at it's correct place.
             while(arr[i] != i+1){
                 //Swap it to the correct index.
                 int temp = arr[arr[i] -1];
                 arr[arr[i] -1] = arr[i];
                 arr[i] = temp;
             }
         }
    }
    public static void main(String[] args) {
        System.out.println("Hello everyone!");

        int arr[] = {3,5,2,1,4};
        
        cyclicSort(arr);

        for(int num : arr){
             System.out.print(num + " ");
        }
    }
}
