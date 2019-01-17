package dwg.othello.player;

import dwg.othello.Position;

public interface Player {
    Position GetNextStone(int[][] board, int myColor);
}
