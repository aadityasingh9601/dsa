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

    public static int findSqrt(int n){
        int low = 1;
        int high = n;
        int ans = 1;

        while(low <= high){
            int mid = low + (high-low)/2;

            if(mid * mid <= n){
                ans = Math.max(ans,mid);
                low = mid+1;
            }
            else{
                high = mid-1;
            }
        }
        return ans;
    }

    //T.C. = O(log n).
    public static int findNthPower(int mid, int n,int N){
        long ans = 1;
        for(int i=1;i<=N;i++){
            ans = ans * mid;
            if(ans > n){
                //We do this check to stop now, once ans > n we don't need to calculate further, as all other
                //futher calculation will be obviously greater than n, so stop, it'll prevent overflowing(in case)
                //value becomes too large.
                return 2; //Means this can't be look in area before it.
            }
        }
         if(ans == n) return 1; //Means we've found the perfect Nth root.
         return 0; //Means look in the area after it, the current value is smaller than n (ans < n).
    }

    public static int findNthRoot(int n,int N){
        int low = 1;
        int high = n;
        
        while (low <= high) {
            int mid = low + (high-low)/2;

            int midN = findNthPower(mid,n,N);
            //If we've find the perfect Nth root of the integer.
            if(midN == 1) return mid;
            else if(midN == 0){
                low = mid+1;
            }
            else {
                high = mid-1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("Hello world");
        
       // int[] array = {3,5,8,15,19,19};
        //int ans = findLowerbound(array, 8);
        //System.out.println(ans);
       // int[] arr2 = {10,20,30,40,50};
       // HashSet<Integer> set = new HashSet<>();
        //floorAndCeil(arr2,set, 25);
        //System.out.println(set);
        //int ans = findSqrt(1515545);
        int ans = findNthRoot(625, 6);
        System.out.println(ans);

        import java.util.Arrays;
        
        //This is the solution for the problem, Aggresive cows.
        class Solution {
    public boolean canWePlace(int[] nums,int k, int dist){
        //We'll place the first cow at the first stall always.
        //Last means the stall position at which the last cow is stored.
        int cows = 1; int last = nums[0];

        for(int i=1;i<nums.length;i++){
           //Ensure the min distance is maintained between two cows.
           if(nums[i] - last >= dist){
              cows++;
              last = nums[i];
           }
        }
        //If we've placed all the cows with the minimum possible distance, then return true, else false.
        if( cows >= k ) return true;
        return false;

    }
    public int aggressiveCows(int[] nums, int k) {
       //Sort the stalls array first, so that we can easily check if we can place cows or not.
       Arrays.sort(nums);

       //We know that there will b min of 2 cows and the max distance possible between them is (max-min)
       //and the least distance will be 1, i.e. when they're at consecutive stalls placed at distance of 1.

       int min = (int)1e9;
       int max = 0;

       //Find the max & min from the array first.
       for(int i=0;i<nums.length;i++){
         if(nums[i] > max){
            max = nums[i];
         }
         if(nums[i] < min){
           min = nums[i];
         }
       }

      int low = 1;
      int high = max - min;
      int ans = 0;

       while(low <= high){
          int mid = low + (high-low)/2;

          if(canWePlace(nums,k,mid) == true){
             //It can be our possible answer. Let's now go for a bigger value.
             ans = mid;
             low = mid+1;
           }
           else{
             //It can't be our answer, let's try to look for a smaller value now.
             high = mid - 1;
           }
       }
       return ans;
    }
}


    }
     
}
