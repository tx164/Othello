package dwg.othello;

import javax.swing.*;

import dwg.othello.player.*;

public class Othello extends JFrame {
    public static void main(String[] args) {
        Game game = new Game();
        Player[] players = new Player[2];
        players[0] = new HeatMapPlayer();
        players[1] = new GameTreePlayer();

        GUIView guiView = new GUIView();
        guiView.setVisible(true);
        guiView.resetWindowSize();

        int[][] prevBoard = null;
        while (!game.IsGameFinished()) {
            guiView.updateBoard(game.GetBoard(), players[0].getClass().getSimpleName(), players[1].getClass().getSimpleName());
            try {
                Thread.sleep(500);
            } catch (Exception e) {}
            game.PrintStatusWithPreviousBoard(prevBoard);
            Position next = players[game.GetTurn()].GetNextStone(game.GetBoard(), game.GetTurn());
            if (game.TestPutStone(next) == 0) {
                System.out.println("Invalid position: " + next.x + ", " + next.y + ". Skip turn!");
                game.NextTurn();
            }
            prevBoard = game.GetBoard();
            System.out.println("Next position: " + next.x + ", " + next.y);
            game.PutStone(next);
        }
        game.PrintStatusWithPreviousBoard(prevBoard);
        guiView.updateBoard(game.GetBoard(), players[0].getClass().getSimpleName(), players[1].getClass().getSimpleName());
    }
}
