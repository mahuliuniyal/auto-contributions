public class RecursiveBacktracking {

    // Board size
    private static final int BOARD_SIZE = 8;

    // Function to check if a move is valid
    public static boolean isValid(int[][] board, int row, int col) {
        return (row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE);
    }

    // Function to check if the game is over
    public static boolean isGameOver(int[][] board) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == 0 && isValid(board, i + 1, j)) return false;
                if (board[i][j] == 1 && isValid(board, i - 1, j)) return false;
                if (board[i][j] == 2 && isValid(board, i, j + 1)) return false;
                if (board[i][j] == 3 && isValid(board, i, j - 1)) return false;
            }
        }
        return true;
    }

    // Function for recursive backtracking
    public static boolean solve(int[][] board, int turn) {
        // If the game is over, return true
        if (isGameOver(board)) return true;

        // Try to place a piece in each empty position on the board
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == 0) {
                    // Place a piece of current turn
                    board[i][j] = turn;
                    // Recursively try to find a winning move
                    if (solve(board, (turn + 1) % 4)) return true;
                    // If no winning move is found, reset the position and try the next one
                    board[i][j] = 0;
                }
            }
        }

        // If no winning move is found after trying all positions, return false
        return false;
    }

    public static void main(String[] args) {
        int[][] board = new int[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = 0;
            }
        }

        if (!solve(board, 1)) System.out.println("No solution exists");
    }
}