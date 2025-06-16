public class Recursion {

    public static void findSum(int n,int sum){
        if(n==0){
            System.out.println(sum);
             return;
        }
        sum = sum + n;
        findSum(n-1,sum);
    }

    public static int printFactorial(int n){
         if(n == 0 || n == 1){
             return 1;
         }
         
         int fact = n * printFactorial(n-1);
         return fact;
    }
    
    public static void fibonacci(int a,int b, int n){
         if(n==0){
             return;
         }

         //next term = sum of previous two.
         int c = a + b;
         System.out.print(c + " ");
         fibonacci(b, c, n-1);
    }
    
    //Stack height = n.
    public static int printExp(int x, int n){
         if(x==0){ 
             return 0;
         }
         if(n==0){
             return 1;
         }

         int answer = x * printExp(x, n-1);
         return answer;
    }

    //Stack height = log n base 2.
    public static int printExp2(int x,int n){
         if(x==0){
             return 0;
         }
         if(n==0){
             return 1;
         }

         //if n is even.
         if(n%2 == 0){
             int answer = printExp2(x,n/2) * printExp2(x,n/2);
             return answer;
         }
         //if n is odd.
         else{
             int answer = printExp2(x,n/2) * printExp2(x,n/2) * x;
             return answer;
         }
    }

    public static void main(String[] args) {
        System.out.println("Hello everyone!");

        //RECURSION.

        //Sum of first n natural numbers.
       //findSum(5,0);

       //Factorial of a number n.
       //System.out.println(printFactorial(5));;

       //FIBONACCI SERIES.
    //    int a = 0;
    //    int b = 1;
    //    int n = 7; //Variable to detect the number of terms to be printed.
    //    System.out.print(a + " ");
    //    System.out.print(b + " ");
    //    fibonacci(a, b, n-2);
    
    //Print x^n.

    //System.out.println(printExp(2,10 ));;
    //System.out.println(printExp2(2, 7));
       
    }
}
