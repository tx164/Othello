package dwg.othello;

public class GameUtil {
    private static int TestPutStoneWithDirection(int[][] board, int turn, Position put, int direction) {
        if (board[put.x][put.y] != -1) {
            return 0;
        }
        Position now = new Position(put);
        int oppositeStones = 0;
        int opposite = (turn == 0) ? 1 : 0;
        while (true) {
            now.x += directions[direction][0];
            now.y += directions[direction][1];
            if (now.x < 0 || now.x >= 8 || now.y < 0 || now.y >= 8) {
                break;
            }
            if (board[now.x][now.y] == -1) {
                break;
            }
            if (board[now.x][now.y] == turn) {
                if (oppositeStones > 0) {
                    return oppositeStones;
                }
                break;
            }
            if (board[now.x][now.y] == opposite) {
                ++oppositeStones;
            }
        }
        return 0;
    }

    public static int OppositeColor(int myColor) {
        return (myColor == 0) ? 1 : 0;
    }

    // Returns how many stones will be gained when put my stone on the position.
    // Does not includes "new" stone.
    public static int TestPutStone(int[][] board, int turn, Position put) {
        if (board[put.x][put.y] != -1) {
            return 0;
        }
        int gainStones = 0;
        for (int i = 0; i < directions.length; ++i) {
            gainStones += TestPutStoneWithDirection(board, turn, put, i);
        }
        return gainStones;
    }

    public static boolean PutStone(int[][] board, int turn, Position put) {
        if (put.x < 0 || put.x >= 8 || put.y < 0 || put.y >= 8) {
            return false;
        }
        if (board[put.x][put.y] != -1) {
            return false;
        }
        if (TestPutStone(board, turn, put) == 0) {
            return false;
        }
        // Change colors
        int opposite = (turn == 0) ? 1 : 0;
        for (int i = 0; i < directions.length; ++i) {
            if (TestPutStoneWithDirection(board, turn, put, i) > 0) {
                Position now = new Position(put);
                while(true) {
                    now.x += directions[i][0];
                    now.y += directions[i][1];
                    if (board[now.x][now.y] == turn) {
                        break;
                    }
                    board[now.x][now.y] = turn;
                }
            }
        }

        board[put.x][put.y] = turn;
        return true;
    }
    static int[][] directions = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
}
