package dwg.othello;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class GUIView extends JFrame {
    JPanel panel;
    JLabel labels[][];
    JLabel status;

    final static int useableSize = 512;
    static int borderSize;
    static int upperSize;
    static int windowSizeX;
    static int windowSizeY;
    static int statusSize = 96;

    public void resetWindowSize() {
        // get inner "USEABLE" size.
        Dimension d;
        d = panel.getSize();
        // reset window size
        borderSize = (useableSize - d.width) / 2;
        upperSize = useableSize - borderSize - d.height;
        windowSizeX = useableSize + borderSize * 2;
        windowSizeY = useableSize + borderSize + upperSize + statusSize;

        setSize(windowSizeX, windowSizeY);
    }

    public void updateBoard(int[][] board, String redName, String blueName) {
        int[] count = new int[2];
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                JLabel label = labels[i][j];
                if (board[i][j] == -1) {
                    label.setText("");
                } else {
                    label.setText("●");
                    label.setForeground(board[i][j] == 0 ? Color.RED : Color.BLUE);
                    count[board[i][j]]++;
                }
            }
        }
        status.setText("<html><font color='red' size=20>" + redName + ": " + count[0] + "</font><br><font color='blue' size=20>" + blueName + ": " + count[1] + "</font></html>");
    }

    public GUIView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(useableSize, useableSize);
        setResizable(false);

        setVisible(true);

        panel = new JPanel();
        add(panel);
        panel.setLayout(null);

        labels = new JLabel[8][8];
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                JLabel label = new JLabel();
                label.setSize(64, 64);
                label.setLocation(i * 64, j * 64);
                label.setFont(new Font(label.getFont().getName(), label.getFont().getStyle(), 96));
                label.setText("");
                label.setForeground(Color.RED);
                label.setVerticalAlignment(SwingConstants.CENTER);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                Border border = BorderFactory.createLineBorder(Color.BLACK);
                label.setBorder(border);
                panel.add(label);
                labels[i][j] = label;
            }
        }
        status = new JLabel();
        status.setSize(useableSize, statusSize);
        status.setLocation(0, 8 * 64);
        panel.add(status);
    }
}
