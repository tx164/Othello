package dwg.othello;

public class Game {
    public Game() {
        Reset();
    }

    void Reset() {
        board = new int[8][8];

        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                board[i][j] = -1;
            }
        }
        board[3][3] = board[4][4] = 0;
        board[3][4] = board[4][3] = 1;

        turn = 0;
        stones = new int[2];
        stones[0] = stones[1] = 2;
    }

    int GetTurn() {
        return turn;
    }

    void PrintStatus() {
        PrintStatusWithPreviousBoard(null);
    }
    // Print current status for debugging
    void PrintStatusWithPreviousBoard(int[][] prevBoard) {
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                char c = (board[i][j] == -1) ? '.' : (board[i][j] == 0) ? 'R' : 'B';
                if (prevBoard != null) {
                    if (c == 'R' && prevBoard[i][j] == 0) {
                        c = 'r';
                    } else if (c == 'B' && prevBoard[i][j] == 1) {
                        c = 'b';
                    }
                }
                System.out.print(c);
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println("Red: " + stones[0] + ", Blue: " + stones[1]);
        if (IsGameFinished()) {
            if (stones[0] > stones[1]) {
                System.out.println("Red wins!");
            } else if (stones[0] < stones[1]) {
                System.out.println("Blue wins!");
            } else {
                System.out.println("Draw!");
            }
        } else {
            System.out.println("Turn: " + ((turn == 0) ? "Red" : "Blue"));
        }
        System.out.println();
    }

    int[][] GetBoard() {
        int[][] retBoard = new int[8][8];
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                retBoard[i][j] = board[i][j];
            }
        }
        return retBoard;
    }

    // Returns how many stones will be gained when put my stone in the position.
    int TestPutStone(Position position) {
        return GameUtil.TestPutStone(board, turn, position);
    }

    boolean PutStone(Position position) {
        if (!GameUtil.PutStone(board, turn, position)) {
            return false;
        }
        stones[0] = stones[1] = 0;
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (board[i][j] != -1) {
                    ++stones[board[i][j]];
                }
            }
        }
        NextTurn();
        return true;
    }

    void NextTurn() {
        turn = GameUtil.OppositeColor(turn);
        // If next turn doesn't have puttable cells, skip the turn.
        if (!HasPuttableCells(turn)) {
            turn = GameUtil.OppositeColor(turn);
        }
    }


    private boolean HasPuttableCells(int turn) {
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                Position p = new Position(i, j);
                if (GameUtil.TestPutStone(board, turn, p) > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean IsGameFinished() {
        // No more empty cell, or any side doesn't have stone.
        if (stones[0] + stones[1] == 64 || stones[0] == 0 || stones[1] == 0) {
            return true;
        }
        // Check any side put-able cells in turn.
        if (HasPuttableCells(0) || HasPuttableCells(1)) {
            return false;
        }
        return true;
    }

    private int[][] board;
    private int turn;
    private int[] stones;
}
