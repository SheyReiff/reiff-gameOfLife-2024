package reiff.gameoflife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameOfLifeComponent extends JComponent {
    private final GameOfLife game;
    private final int CELL_SIZE = 20;

    private final Timer timer;

    public GameOfLifeComponent(GameOfLife game) {
        this.game = game;

         timer = new Timer(1000, e -> {
            game.nextGen();
            repaint();
        });
        addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX() / CELL_SIZE;
                int y = e.getY() / CELL_SIZE;
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
            g.drawLine(x * CELL_SIZE, 0, x * CELL_SIZE, game.getHeight() * CELL_SIZE);
        }
        for (int y = 0; y <= game.getHeight(); y++) {
            g.drawLine(0, y * CELL_SIZE, game.getWidth() * CELL_SIZE, y * CELL_SIZE);
        }

        for (int y = 0; y < game.getHeight(); y++) {
            for (int x = 0; x < game.getWidth(); x++) {
                if (game.getCell(x, y) == 1) {
                    g.setColor(Color.WHITE);
                    g.fillRect(x * CELL_SIZE + 1, y * CELL_SIZE + 1, CELL_SIZE - 1, CELL_SIZE - 1);
                }
            }
        }

    }
}
