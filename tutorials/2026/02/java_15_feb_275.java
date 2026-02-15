// Recursive Backtracking Algorithm in Java
public class RecursiveBacktracking {
    // Global variables to store the board and the solution found so far
    private static char[][] board = new char[8][8];
    private static int size;
    private static boolean solved;

    public static void main(String[] args) {
        initializeBoard();
        solveNQueens(0);
        printSolution();

        // Test case for 4x4 board
        size = 4;
        initializeBoard();
        solveNQueens(0);
        printSolution();
    }

    // Initialize the board with empty spaces
    private static void initializeBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = '-';
            }
        }
        solved = false;
    }

    // Method to solve N-Queens problem using recursive backtracking
    private static void solveNQueens(int row) {
        if (row == size) {
            printSolution();
            return;
        }

        for (int col = 0; col < size; col++) {
            if (isValidMove(row, col)) {
                board[row][col] = 'Q';
                solveNQueens(row + 1);
                // Backtrack: remove the queen from the current position
                board[row][col] = '-';
            }
        }
    }

    // Check if a move is valid by checking for conflicts with previously placed queens
    private static boolean isValidMove(int row, int col) {
        // Check horizontal and vertical conflicts
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
            if (board[row - i + size][col] == 'Q') {
                return false;
            }
        }

        // Check diagonal conflicts
        for (int i = 1, j = 1; i <= row && j <= col; i++, j++) {
            if (board[row - i + size][col - j] == 'Q') {
                return false;
            }
        }

        for (int i = 1, j = 1; i <= row && j >= col; i++, j--) {
            if (board[row - i + size][col + j] == 'Q') {
                return false;
            }
        }

        return true;
    }

    // Print the solution found
    private static void printSolution() {
        System.out.println("Solved board:");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}