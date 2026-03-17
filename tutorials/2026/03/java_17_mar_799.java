import java.util.*;

public class RecursiveBacktracking {
    // Define the size of the grid
    private static final int SIZE = 8;

    // Function to print the board
    public static void printBoard(char[][] board) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Function to check if it's safe to place a piece at a given position
    public static boolean isSafe(char[][] board, int i, int j, char piece) {
        // Check rows and columns for conflicts
        for (int k = 0; k < SIZE; k++) {
            if (board[i][k] == piece || board[k][j] == piece) {
                return false;
            }
        }

        // Check diagonals for conflicts
        int rowDirection[] = {1, -1};
        int colDirection[] = {-1, 1};

        for (int l = 0; l < 2; l++) {
            boolean conflict = true;
            for (int m = 0; m < SIZE; m++) {
                if (l == 0) {
                    if (i + rowDirection[l] * m >= 0 && i + rowDirection[l] * m < SIZE
                            && board[i + rowDirection[l] * m][j + colDirection[l] * m] == piece) {
                        conflict = false;
                        break;
                    }
                } else {
                    if (j + colDirection[l] * m >= 0 && j + colDirection[l] * m < SIZE
                            && board[i + rowDirection[l] * m][j + colDirection[l] * m] == piece) {
                        conflict = false;
                        break;
                    }
                }
            }
            if (conflict) {
                return false;
            }
        }

        // If no conflicts found, it's safe to place the piece
        return true;
    }

    // Recursive function for recursive backtracking
    public static void solve(char[][] board) {
        printBoard(board);

        int i = 0;
        while (i < SIZE && board[i][SIZE - 1] == 'X') {
            i++;
        }
        if (i == SIZE) {
            System.out.println("No solution exists");
            return;
        }

        for (char piece = 'N'; piece <= 'Z'; piece++) {
            if (isSafe(board, i, 0, piece)) {
                board[i][0] = piece;

                // Recursively call the function to fill up the rest of the board
                solve(board);

                // Backtrack: Remove the piece from the current position
                board[i][0] = 'X';
            }
        }

        System.out.println();
    }

    public static void main(String[] args) {
        char[][] board = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            Arrays.fill(board[i], 'X');
        }
        solve(board);
    }
}