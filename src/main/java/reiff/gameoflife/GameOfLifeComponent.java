package reiff.gameoflife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameOfLifeComponent extends JComponent {
    private final GameOfLife game;
    private final int cellSize;

    private final Timer timer;

    public GameOfLifeComponent(GameOfLife game, int cellSize) {
        this.game = game;
        this.cellSize = cellSize;

         timer = new Timer(1000, e -> {
            game.nextGen();
            repaint();
        });
        addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX() / cellSize;
                int y = e.getY() / cellSize;
                if (x < game.getWidth() && y < game.getHeight()) {
                    int currentState = game.getCell(x, y);
                    game.setCell(x, y, currentState == 1 ? 0 : 1);
                    repaint();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

    }

    public void startTimer() {
        if (!timer.isRunning()) {
            timer.start();
        }
    }

    public void stopTimer() {
        if (timer.isRunning()) {
            timer.stop();
        }
    }

    protected void paintComponent(Graphics g) {

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());


        g.setColor(Color.GRAY);

        for (int x = 0; x <= game.getWidth(); x++) {
            g.drawLine(x * cellSize, 0, x * cellSize, game.getHeight() * cellSize);
        }
        for (int y = 0; y <= game.getHeight(); y++) {
            g.drawLine(0, y * cellSize, game.getWidth() * cellSize, y * cellSize);
        }

        g.setColor(Color.WHITE);
        for (int y = 0; y < game.getHeight(); y++) {
            for (int x = 0; x < game.getWidth(); x++) {
                if (game.getCell(x, y) == 1) {
                    g.fillRect(x * cellSize + 1, y * cellSize + 1, cellSize - 1, cellSize - 1);
                }
            }
        }

    }
}
