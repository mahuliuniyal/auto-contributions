public class RecursiveBacktracking {

    // Define a class for the puzzle
    static class Puzzle {
        int size;
        int[][] board;
        int empty;

        public Puzzle(int size) {
            this.size = size;
            this.board = new int[size][size];
            this.empty = 0;
            initializeBoard();
        }

        // Initialize the board with random numbers
        private void initializeBoard() {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    board[i][j] = i * size + j + 1;
                }
            }
        }

        // Print the current state of the board
        public void printBoard() {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }
        }

        // Check if the board is solved
        public boolean isSolved() {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (board[i][j] != i * size + j + 1) {
                        return false;
                    }
                }
            }
            return true;
        }

        // Check if a move is valid
        public boolean isValidMove(int row, int col, int num) {
            if (row < 0 || row >= size || col < 0 || col >= size) {
                return false;
            }
            if (board[row][col] != 0) {
                return false;
            }
            if (num == 1 && row == 0) {
                return false;
            }
            if (num == 2 && col == 0) {
                return false;
            }
            if (num == 3 && row == size - 1) {
                return false;
            }
            if (num == 4 && col == size - 1) {
                return false;
            }
            return true;
        }

        // Make a move
        public void makeMove(int row, int col, int num) {
            board[row][col] = num;
        }

        // Undo a move
        public void undoMove() {
            board[empty][empty] = 0;
        }
    }

    // Recursive backtracking function
    public static void recursiveBacktracking(Puzzle puzzle) {
        if (puzzle.isSolved()) {
            System.out.println("Solution found:");
            puzzle.printBoard();
            return;
        }

        for (int i = 0; i < puzzle.size; i++) {
            for (int j = 0; j < puzzle.size; j++) {
                if (puzzle.board[i][j] == 0) {
                    for (int num = 1; num <= puzzle.size; num++) {
                        if (puzzle.isValidMove(i, j, num)) {
                            puzzle.makeMove(i, j, num);
                            recursiveBacktracking(puzzle);
                            puzzle.undoMove();
                        }
                    }
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {
        Puzzle puzzle = new Puzzle(