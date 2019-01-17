package dwg.othello;

public class Position {
    public int x;
    public int y;

    public Position(int positionX, int positionY) {
        x = positionX;
        y = positionY;
    }

    public Position(Position from) {
        x = from.x;
        y = from.y;
    }
}
