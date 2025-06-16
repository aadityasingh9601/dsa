import java.util.ArrayList;
import java.util.List;


public class Backtracking {

    public static void printPerm(String str,String perm,int idx){
        if(str.length() == 0){
             System.out.println(perm);
             return;
        }
          
        for(int i=0;i<str.length();i++){
             char currChar = str.charAt(i);
             String newString = str.substring(0,i) + str.substring(i+1);
            
             printPerm(newString, perm+currChar, idx+1);
        }
    }
     
    public static void saveBoard(char[][] board,List<List<String>> allBoards){
        //We have to take our board that's a 2-D char array and convert it into a List<String> and finally
        //store that into our allBoards.
        String row = ""; //After filling it'll look like "..Q.".
        List<String> newBoard = new ArrayList<>();
        //Iterate over every row in the board.
        for(int i=0;i<board.length;i++){
              row = ""; //We need a empty row to add our values to it.

              //Iterate over every column.
              for(int j=0;j<board.length;j++){
                   if(board[i][j] == 'Q'){
                       row += 'Q';
                   }
                   else{
                      row += '.';
                   }
              }
              //Add the row to the newBoard now.
              newBoard.add(row);
        }
        //Finally add our board to allBoards.
        allBoards.add(newBoard);
    }

    public static boolean isSafe(int row,int col, char[][] board){
         //For the current position on the board , we've to check in all the 8 direections that is it safe
         //to place the queen or not.

         //horizontally.
         for(int j=0;j<board.length;j++){
             if(board[row][j] == 'Q'){
                 return false;
             }
         }

         //vertically.
         for(int i=0;i<board.length;i++){
             if(board[i][col] == 'Q'){
                 return false;
             }
         }

         //upper-left direction.
         int r = row; //Store in another variable as we can't just change the row, as it's used by everyone.
         for(int c=col; c>=0 && r>=0; c--,r--){
             if(board[r][c] == 'Q'){
                 return false;
             }
         }

         //upper-right direction.Restore r value as it's value is altered because of previous loop.
         r = row;
         for(int c=col;r>=0 && c<board.length; r--,c++){
             if(board[r][c] == 'Q'){
                 return false;
             }
         }

         //lower-left direction.
         r = row;
         for(int c=col; c>=0 && r<board.length; c--,r++){
             if(board[r][c] == 'Q'){
                 return false;
             }
         }

         //lower-right direction.
         r = row;
         for(int c=col; c<board.length && r<board.length; r++,c++){
             if(board[r][c] == 'Q'){
                 return false;
             }
         }

         //if all checks are done, then only return true.
         return true;
    }

    public static void helper(char[][] board, List<List<String>> allBoards, int col){
        //If all queens are placed properly, stop the iteration and save the board into allBoards.
        if(col == board.length){
             saveBoard(board,allBoards);
             return;
        }
         
        //Iterate over each row of each column,check if it's a safe position or not and place the queen.
        for(int row = 0;row<board.length;row++){
            if(isSafe(row,col,board)){
                 board[row][col] = 'Q';
                 //Once queen is placed in one column, move to another one.
                 helper(board, allBoards, col+1);
                 //Once all colums are iterated and queens are placed, no matter right or wrong,empty the 
                 //board to find another board configuration that solves the problem.
                 board[row][col] = '.';
            }
        }
    }



    //N-QUEENS PROBLEM(IMP.)
    public static List<List<String>> solveNQueens(int n){
        
        //It'll store our all possible solutions for the problem.
        List<List<String>> allBoards = new ArrayList<>();
        
        //We're making it a 2D char array to keep things simple, we'll convert it into a list later.
        char[][] board = new char[n][n];

        helper(board,allBoards,0);
        
        return allBoards;
        
    }
    
    public static boolean isValid(char[][] sudoku,int row,int col, int num){
         //check the row and column.
         for(int i=0;i<sudoku.length;i++){
              //check row.
              if(sudoku[row][i] == (char)(num + '0')){
                   return false;
              }
              if(sudoku[i][col] == (char)(num + '0')){
                 return false;
              }
         }

         //Check the grid.
         //This technique is used to find the starting row and column. See in video for other ways to find
         //the starting cell, search on internet too, how this works.
         int sr = (row/3) * 3;
         int sc = (col/3) * 3;

         for(int i=sr ; i<sr+3; i++){
              for(int j=sc; j<sc+3;j++){ 
                  if(sudoku[i][j] == (char)(num + '0')){
                     return false;
                  }
              }
         }

         return true;
    }


   public static boolean helper2(char[][] sudoku,int row,int col ){
       if(row == sudoku.length){
        //Print the solution

        for (int i = 0; i < sudoku.length; i++) {
            System.out.print("{");
            for (int j = 0; j < sudoku[i].length; j++) {
                System.out.print("'" + sudoku[i][j] + "'");
                if (j < sudoku[i].length - 1) {
                    System.out.print(",");
                }
            }
            System.out.print("}");
            if (i < sudoku.length - 1) {
                System.out.println(",");
            } else {
                System.out.println();
            }
        }
        
        
         return true;
       }

    //Checking what's the next row and column.
    int ncol = 0;
    int nrow = 0;
    if(col == sudoku.length -1 ){
        ncol = 0;
        nrow = row + 1;
        
    }
    else{
        nrow = row;
        ncol = col +1;
    }
    
  //Check if the current cell is even empty or not.
   if(sudoku[row][col] != '.'){
    //Next call will return true only when base condition is reached means we've reached sudoku's end and found
    //a solution.
      if(helper2(sudoku,nrow,ncol)){
          return true;
      }
   }
   else{
       //Place numbers from 1-9 on the cell and check if they're safe to put there or not.
       for(int i=1;i<=9;i++){
            if(isValid(sudoku,row,col,i)){
                 //Place the number on the cell
                 //Type cast into char as that's what the datatype of the sudoku is.
                 sudoku[row][col] = (char)(i + '0');
                 //Call function for next cell,
                 //If a solution is found , I’m done — return true and stop all further processing."
                 if(helper2(sudoku, nrow, ncol)){
                     return true;
                 }
                 //If true is not returned we'll come back to the curr cell, empty it & try a new number.
                 else{
                     sudoku[row][col] = '.';
                 }
            }
       }
   }//If no valid number is found,backtract to the previous cell and try our luck there by changing the number
   //to a new number.
   return false;
   }

   

    public static void solveSudoku(char[][] sudoku){
         helper2(sudoku,0,0);
    }
    public static void main(String[] args) {
            System.out.println("Hello everyone");
            //String str = "ABC";
            //String newStr = "";
            //printPerm(str, newStr, 0);
           //System.out.println( solveNQueens(4));

           char[][] sudoku = {
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };

           solveSudoku(sudoku);


    }
}

