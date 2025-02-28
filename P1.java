/*
Backtracking with recursive depth-first search (DFS).

Time Complexity (TC)
O(N!) – In the worst case, we place a queen in each row and check all possible column positions, leading to a factorial growth in placements.

Space Complexity (SC)
O(N²) (for board) + O(N) (recursion stack) ≈ O(N²) – The board uses O(N²) space, and the recursion stack takes O(N) in the worst case.

Explanation in Three Sentences
The algorithm places queens row by row while ensuring they don’t attack each other by checking column and diagonal constraints. It explores all possible placements recursively, backtracking whenever an invalid position is encountered. This ensures that all valid board configurations are found efficiently while pruning unnecessary computations.


 */

 import java.util.*;

 class Solution {
     public List<List<String>> solveNQueens(int n) {
         // Create an n x n chessboard initialized with 'false' (no queens placed)
         boolean[][] board = new boolean[n][n]; 
         // List to store all valid solutions
         List<List<String>> res = new ArrayList<>();
         // Start the backtracking process from row 0
         backtrack(board, 0, res);
         // Return the list of valid board configurations
         return res;
     }
 
     private void backtrack(boolean[][] board, int r, List<List<String>> res) {
         // Base case: If we placed queens in all rows, store the board configuration
         if (r == board.length) {
             List<String> li = new ArrayList<>();
             for (int i = 0; i < board.length; i++) {
                 StringBuilder sb = new StringBuilder();
                 for (int j = 0; j < board[0].length; j++) {
                     if (board[i][j]) {
                         sb.append('Q'); // Place queen
                     } else {
                         sb.append('.'); // Empty space
                     }
                 }
                 li.add(sb.toString()); // Add row representation to the solution
             }
             res.add(li); // Store valid board configuration
             return; // Backtrack
         }
 
         // Try placing a queen in each column of the current row
         for (int c = 0; c < board.length; c++) {
             if (isSafe(board, r, c)) { // Check if the position is safe
                 // Place the queen
                 board[r][c] = true;
                 // Recur to place queens in the next row
                 backtrack(board, r + 1, res);
                 // Backtrack: Remove the queen and try another position
                 board[r][c] = false;
             }
         }
     }
 
     private boolean isSafe(boolean[][] board, int r, int c) {
         // Check if any queen is already placed in the same column
         for (int i = 0; i < r; i++) {
             if (board[i][c]) {
                 return false; // Not safe
             }
         }
 
         // Check upper-left diagonal
         int i = r, j = c;
         while (i >= 0 && j >= 0) {
             if (board[i][j]) return false; // Not safe
             i--; j--; // Move diagonally up-left
         }
 
         // Check upper-right diagonal
         i = r; j = c;
         while (i >= 0 && j < board.length) {
             if (board[i][j]) return false; // Not safe
             i--; j++; // Move diagonally up-right
         }
 
         // If no conflicts, return true (safe position)
         return true;
     }
 }
 