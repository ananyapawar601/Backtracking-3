/*
Backtracking with DFS (Depth-First Search)

Time Complexity (TC)
O(N * M * 4^L), where:

N = number of rows in the board
M = number of columns in the board
L = length of the word
In the worst case, for each cell, we try 4 possible directions, leading to an exponential factor 
4 ^L
 
Space Complexity (SC)
O(L) (Recursive Call Stack)

The recursion depth is at most L (length of the word), so the extra space is O(L).
The board is modified in-place (backtracking), so no extra space is used.

Explanation in Three Sentences
The algorithm iterates through each cell in the board and starts a depth-first search (DFS) if the first character matches the target word. 
At each step, it explores all four possible directions while marking the current cell as visited to prevent reuse, then backtracks by restoring the original character. 
Since each recursive call explores four branches, the worst-case time complexity is O(N * M * 4^L), while the space complexity is O(L) due to the recursion stack.
 */


 class Solution {
    // Helper function to check if the word exists starting from cell (i, j)
    private boolean func(char[][] board, int i, int j, String word, int k) {
        // If all characters of the word are found
        if (k == word.length()) {
            return true;
        }
        // Boundary conditions and character mismatch check
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || word.charAt(k) != board[i][j]) {
            return false;
        }

        // Temporarily mark the cell as visited
        char temp = board[i][j];
        board[i][j] = ' ';

        // Check all four possible directions (down, up, right, left)
        boolean ans = func(board, i + 1, j, word, k + 1) ||
                      func(board, i - 1, j, word, k + 1) ||
                      func(board, i, j + 1, word, k + 1) ||
                      func(board, i, j - 1, word, k + 1);

        // Restore the original character in the cell
        board[i][j] = temp;

        return ans;
    }

    // Main function to check if the word exists in the board
    public boolean exist(char[][] board, String word) {
        // Iterate through each cell in the board
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // If the first character matches, start the search
                if (board[i][j] == word.charAt(0)) {
                    // If the word is found, return true
                    if (func(board, i, j, word, 0)) {
                        return true;
                    }
                }
            }
        }
        // If the word is not found, return false
        return false;
    }
}
