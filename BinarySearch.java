import java.util.HashSet;

public class BinarySearch {
    public static int bsIterative(int[] arr, int target){
        int low = 0;
        int high = arr.length-1;
        //The space between low and high is our search space, we'll keep searaching until we've search space left
        //else we just return -1;

        while(low <= high){
            int mid = low + (high - low)/2;

            if(arr[mid] == target){
                return mid;
            }
            else if(arr[mid] > target){
                high = mid-1; //means our target must be in the left part.
            }
            else{
                low = mid+1;
            }
        }
        return -1;
    }

    public static int bsRecursive(int[] arr,int target, int low,int high){
        if(low > high){
            return -1;
        }

        int mid = low + (high - low)/2;

        if(arr[mid] == target){
            return mid;
        }
        else if(arr[mid] > target){
           return bsRecursive(arr,target, low, mid - 1);
        }
        else{
            return bsRecursive(arr,target, mid+1, high);
        }
    }
    
    public static int findLowerbound(int[] arr,int target){
        int n = arr.length;
        int low = 0;
        int high = n-1;
        int ans = n;

        while(low <= high){
            int mid = low + (high-low)/2;

            if(arr[mid] >= target){
                //This satisfies our condition, we're not sure that it's the smallest index satisfying the condition
                //but it's a value, that can probably be our final ans.
                ans = mid;
                high = mid - 1;
            }
            else{
                low = mid+1;
            }
        }
        return ans;
    }

     public static int findUpperbound(int[] arr,int target){
        int n = arr.length;
        int low = 0;
        int high = n-1;
        int ans = n;

        while(low <= high){
            int mid = low + (high-low)/2;

            if(arr[mid] > target){
                //This satisfies our condition, we're not sure that it's the smallest index satisfying the condition
                //but it's a value, that can probably be our final ans.
                ans = mid;
                high = mid - 1;
            }
            else{
                low = mid+1;
            }
        }
        return ans;
    }
     
    public static void floorAndCeil(int[] arr,HashSet<Integer> set, int target){
        int n = arr.length;
        int low = 0;
        int high = arr.length-1;
        int ans = -1;
        int ceil = findLowerbound(arr, target);
        set.add(ceil);

        //Finding floor, largest no. in array <= target.
        while(low<=high){
            int mid = low + (high-low)/2;
            
            if(arr[mid] <= target){
                ans = mid;
                low = mid+1;
            }
            else{
                high = mid -1;
            }
        }
        set.add(ans);
    }
    public static void main(String[] args) {
        System.out.println("Hello world");
        
        int[] array = {3,5,8,15,19,19};
        //int ans = findLowerbound(array, 8);
        //System.out.println(ans);
        int[] arr2 = {10,20,30,40,50};
        HashSet<Integer> set = new HashSet<>();
        floorAndCeil(arr2,set, 25);
        System.out.println(set);
    }
     
}
