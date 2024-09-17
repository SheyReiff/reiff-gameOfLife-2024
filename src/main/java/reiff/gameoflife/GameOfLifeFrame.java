package reiff.gameoflife;
import javax.swing.*;
import java.awt.*;


public class GameOfLifeFrame extends JFrame {

    public GameOfLifeFrame() {
        setSize(800, 600);
        setTitle("Game Of Life");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        GameOfLife game = new GameOfLife(300, 300);
        GameOfLifeComponent gameOfLifeComponent = new GameOfLifeComponent(game);
        add(gameOfLifeComponent, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        JButton playButton = new JButton("Play");
        JButton pauseButton = new JButton("Pause");

        playButton.addActionListener(e -> {
            gameOfLifeComponent.startTimer();
        });

        pauseButton.addActionListener(e -> {
            gameOfLifeComponent.stopTimer();
        });

        controlPanel.add(playButton);
        controlPanel.add(pauseButton);
        add(controlPanel, BorderLayout.SOUTH);
    }

}

