
public class Patterns{
     public static void main(String[] args) {
        System.out.println("PATTERN QUESTIONS IN JAVA");

        //SOLID RECTANGLE.
        //Change the values of n and m to print different dimensions of rectangle.
        //int n = 5;
        //int m = 8;

        // for(int i=1;i<=n;i++){
        //      for(int j=1;j<=m ;j++){
        //          System.out.print("*");
        //      }
        //      System.out.println();
        // }


        //HOLLOW RECTANGLE.
        
        //My way.
        /* 
        for(int i=1;i<=n;i++){
             if(i == 1 || i == n){
                 for(int j=1;j<=m;j++){
                     System.out.print("*");
                 }
             }else{
                 for(int k=1;k<=m;k++){
                     if(k == 1 || k == m){
                         System.out.print("*");
                     }
                     else{
                         System.out.print(" ");
                     }
                 }
             }
             System.out.println();
        }

       

        //Mam's solution.
        //Whenever pattern questions come , try to look for patterns, try to solve these question like a 
        //matrix, here in this question , the "*" are present only at places where either i=1;j=1;i=n;j=m;else
        //a empty space is there,MAKE A MATRIX AND TRY TO LOOK FOR PATTERNS.

        for(int i=1;i<=n;i++){
             for(int j=1;j<=m;j++){
                 if(i==1 || j==1 || i==n || j==m){
                     System.out.print("*");
                 }else{
                     System.out.print(" ");
                 }
             }
             System.out.println("");
        }
         



      //HALF PYRAMID PATTERN.

      int a = 5;

      for(int i=1;i<=a;i++){
         for(int j=1;j<=i;j++){
             System.out.print("*");
         }
         System.out.println("");
      }

      //INVERTED HALF PYRAMID PATTERN.

      for(int i=a;i>=1;i--){
         for(int j=1;j<=i;j++){
             System.out.print("*");
         }
         System.out.println("");
      }

      


      //INVERTED HALF PYRAMID(ROTATED BY 180 DEG)
      //Observe the pattern how many spaces to print and how many starts to print, observe carefully!
      int a = 8;
     
      for(int i=1;i<=a;i++){

        //Printing spaces.

         for(int j=a-i;j>0;j--){
             System.out.print(" ");
         }

         //Printing stars.

         for(int j=1;j<=i;j++){
             System.out.print("*");
         }
         System.out.println("");
      }
         
        
    
    //HALF PYRAMID WITH NUMBERS.

    int b = 5;

    for(int i=1;i<=b;i++){
         for(int j=1;j<=i;j++){
             System.out.print(j);
         }
         System.out.println("");
    }

  

    //INVERTED HALF PYRAMID WITH NUMBERS.

    int b=5;

    for(int i=b;i>=1;i--){
        for(int j=i;j>=1;j--){
            System.out.print(j);
        }
        System.out.println("");
   }

    
   //FLOYD's TRIANGLE.
   //Try to analyze how our parameter n is related to what's getting printed,try to 
   //observe the common logic.

   int a = 5;
   int num = 1;
   for(int i = 1;i<=a;i++){
      for(int j = 1;j<=i;j++){
         System.out.print(num+" ");
         num++;
      }
      System.out.println("");
   }



    //0-1 Triangle.
    
    int a = 50;

    // for(int i=1;i<=a;i++){
    
    // int num;

    // if(i%2 == 0){
    //      num = 0;
    //  }else{
    //     num = 1;
    //  }

    //     for(int j=1;j<=i;j++){
    //         if(num == 0){
    //             System.out.print(num);
    //             num++;
    //        }
    //        else if(num == 1){
    //             System.out.print(num);
    //             num--;
    //        }
    //     }
    //     System.out.println("");
    // }

    //MAM'S SOLUTION.
    //Point was again to observe the pattern , first make a matrix , then try to observe pattern and solution
    //will be very easy.

    for(int i=1;i<=a;i++){
         for(int j=1;j<=i;j++){
             if( (i+j) % 2 == 0){
                 System.out.print("1");
             }
             else{
                 System.out.print("0");
             }
         }
         System.out.println("");
    }



    //BUTTERFLY PATTERN.
    
    int a=50;
    
    //Upper half pattern.

    for(int i=1;i<=a;i++){
        //Print stars.
         for(int j=1;j<=i;j++){
             System.out.print("*");
         }

         //Print spaces.

         for(int j = 2*(a-i);j>=1;j--){
             System.out.print(" ");
         }
         
         //Print stars.
         for(int j=1;j<=i;j++){
            System.out.print("*");
        }

        System.out.println("");
    }

    //Lower half pattern.

    for(int i=a;i>=1;i--){
        //Print stars.
        for(int j=1;j<=i;j++){
            System.out.print("*");
        }

        //Print spaces.

        for(int j = 2*(a-i);j>=1;j--){
            System.out.print(" ");
        }
        
        //Print stars.
        for(int j=1;j<=i;j++){
           System.out.print("*");
       }

       System.out.println("");
   }


    
    //SOLID RHOMBUS.

    int n=10;


    for(int i=1;i<=n;i++){

        //Print spaces.
        for(int j=n-i;j>=1;j--){
             System.out.print(" ");
        }

         for(int j=1;j<=n;j++){
             System.out.print("*");
         }
         System.out.println("");
    }


    //NUMBER PYRAMID.

     int a = 5;

     for(int i=1;i<=a;i++){
         //Print spaces
         for(int j=a-i;j>=1;j--){
             System.out.print(" ");
         }

         //Print numbers along with spaces.

         for(int j=1;j<=i;j++){
             System.out.print (i+" ");
         }

        //  for(int j=1;j<=2*i-1;j++){
        //     if((i+j) % 2 == 0){
        //         System.out.print(i + " ");
        //     }else{
        //         System.out.print("");
        //     }
             
        //  }

         System.out.println("");
     }



    //PALINDROMIC(means something that looks same from both sides) PYRAMID.
     
    int a = 5;

    for(int i=1;i<=a;i++){
         //Print spaces
         for(int j=a-i;j>=1;j--){
             System.out.print(" ");
         }

         //Print numbers.

         for(int j=i;j>1;j--){
             System.out.print(j);
         }

         for(int j=1;j<=i;j++){
             System.out.print(j);
         }
         System.out.println("");
    }



    //DIAMOND PATTERN.

    int a = 10;
    
    //Upper half.
    for(int i=1;i<=a;i++){
        //Print spaces.

        for(int j=a-i;j>=1;j--){
             System.out.print(" ");
        }

         for(int j=1;j<=2*i-1;j++){
             System.out.print("*");
         }
         System.out.println("");
    }

    //Lower half.
    for(int i=a;i>=1;i--){
        //Print spaces.

        for(int j=a-i;j>=1;j--){
             System.out.print(" ");
        }

         for(int j=1;j<=2*i-1;j++){
             System.out.print("*");
         }
         System.out.println("");
    }


    //HOLLOW RHOMBUS.
     
    int a=5;
    int b=4;

    for(int i=1;i<=a;i++){
        //Printing spaces.
        for(int j=a-i;j>=1;j--){
             System.out.print(" ");
        }

         for(int j=1;j<=b;j++){
             if(i==1 || i==a || j==1 || j==b){
                 System.out.print("*");
             }else{
                 System.out.print(" ");
             }
         }
         System.out.println("");
    }


    //PASCAL'S TRIANGLE.

    //Each number in a Pascal's triangle is the binomial cofficient, so we'll use it's formula.
    //Or we'll use the modified formula, where new num = old num x (r-c)/(c+1).

    int a = 25;
     
    for (int r = 0; r < a; r++) {
        int num = 1;
        for (int j=a-r;j>=1;j--) {
            System.out.print(" ");
        }
        for (int c = 0; c <= r; c++) {
            System.out.print(num + " ");
            num = num * (r - c) / (c + 1); // Compute next number
        }
        System.out.println();
    }


    //HOLLOW BUTTERFLY.
    
    int a=5;
    
    //Upper half.
    for(int i=1;i<=a;i++){
         //Print stars.
         for(int j=1;j<=i;j++){
             if(j==1 || j==i){
                 System.out.print("*");
             }else{
                 System.out.print(" ");
             }
         }

         //Print spaces.
         for(int j=2*(a-i);j>=1;j--){
             System.out.print(" ");
         }

         //Print stars.
         for(int j=1;j<=i;j++){
            if(j==1 || j==i){
                System.out.print("*");
            }else{
                System.out.print(" ");
            }
        }
        System.out.println("");

    }

    //Lower half.
    for(int i=a;i>=1;i--){
        //Print stars.
        for(int j=1;j<=i;j++){
            if(j==1 || j==i){
                System.out.print("*");
            }else{
                System.out.print(" ");
            }
        }

        //Print spaces.
        for(int j=2*(a-i);j>=1;j--){
            System.out.print(" ");
        }

        //Print stars.
        for(int j=1;j<=i;j++){
           if(j==1 || j==i){
               System.out.print("*");
           }else{
               System.out.print(" ");
           }
       }
       System.out.println("");

   }
*/



     }
}