package dwg.othello.player;

import java.util.Random;

import dwg.othello.GameUtil;
import dwg.othello.Position;

public class HeatMapPlayer implements Player {
    public Position GetNextStone(int[][] board, int myColor) {
        Position[] candidates = new Position[64];
        int numCandidates = 0;
        Random r = new Random();
        int selected_weight = -1;
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                int gains = GameUtil.TestPutStone(board, myColor, new Position(i, j));
                if (gains > 0) {
                    int weight = gains * heatMap[i][j];
                    if (weight > selected_weight) {
                        numCandidates = 0;
                    }
                    if (weight >= selected_weight) {
                        candidates[numCandidates++] = new Position(i, j);
                        selected_weight = weight;
                    }
                }
            }
        }
        return candidates[r.nextInt(numCandidates)];
    }

    private int heatMap[][] = {
            {900,   1,  20,  10,  10,  20,    1, 900},
            {  1,   1,  30,  15,  15,  30,    1,   1},
            { 20,  30,  20,  15,  15,  20,   30,  20},
            { 10,  15,  15,   0,   0,  15,   15,  10},
            { 10,  15,  15,   0,   0,  15,   15,  10},
            { 20,  30,  20,  15,  15,  20,   30,  20},
            {  1,   1,  30,  15,  15,  30,    1,   1},
            {900,   1,  20,  10,  10,  20,    1, 900},
    };
}
