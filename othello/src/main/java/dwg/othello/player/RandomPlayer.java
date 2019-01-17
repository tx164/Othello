package dwg.othello.player;

import java.util.Random;

import dwg.othello.GameUtil;
import dwg.othello.Position;

public class RandomPlayer implements Player {
    public Position GetNextStone(int[][] board, int myColor) {
        Position next = new Position(0, 0);
        Random r = new Random();
        while (true) {
            next.x = r.nextInt(8);
            next.y = r.nextInt(8);
            if (GameUtil.TestPutStone(board, myColor, next) > 0) {
                return next;
            }
        }
    }
}
