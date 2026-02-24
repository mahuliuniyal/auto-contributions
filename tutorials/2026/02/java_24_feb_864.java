import java.util.Random;

public class RecursiveBacktracking {
    public static void main(String[] args) {
        int[] board = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] solution = solve(board);
        if (solution != null) {
            System.out.println("Solution found: ");
            for (int i : solution) {
                System.out.print(i + " ");
            }
        } else {
            System.out.println("No solution found.");
        }
    }

    /**
     * Solve the 8-puzzle problem using recursive backtracking.
     * @param board the current state of the board
     * @return the solution as an array of numbers
     */
    public static int[] solve(int[] board) {
        int size = (int) Math.sqrt(board.length);
        // Check if the board is a perfect square
        if (size * size != board.length) {
            return null;
        }

        // Get the goal state
        int[] goal = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i * size + j] != goal[i * size + j]) {
                    return null;
                }
            }
        }

        // Find the first position that needs to be moved
        int empty = -1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i * size + j] == 0) {
                    empty = i * size + j;
                    break;
                }
            }
            if (empty != -1) {
                break;
            }
        }

        // If no empty space is found, the puzzle is solved
        if (empty == -1) {
            return board;
        }

        // Generate all possible moves
        int[] moves = {empty - size, empty + size};
        for (int move : moves) {
            // Check if the move is valid
            if (move >= 0 && move < size * size) {
                // Swap the empty space with the first space in the sequence
                int[] temp = board[move];
                board[move] = board[empty];
                board[empty] = temp;

                // Recursively try to find a solution
                int[] solution = solve(board);
                if (solution != null) {
                    return solution;
                }

                // If no solution is found, backtrack and restore the board
                board[empty] = temp;
                board[move] = temp;
            }
        }

        // If no solution is found, return null
        return null;
    }
}