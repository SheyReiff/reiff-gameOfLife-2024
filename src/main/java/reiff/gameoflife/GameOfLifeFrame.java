package reiff.gameoflife;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;



public class GameOfLifeFrame extends JFrame {

    public GameOfLifeFrame() {
        setSize(800, 600);
        setTitle("Game Of Life");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        GameOfLife game = new GameOfLife(300, 300);
        GameOfLifeComponent gameOfLifeComponent = new GameOfLifeComponent(game, 20);
        GameOfLifeController controller = new GameOfLifeController(game, gameOfLifeComponent);

        gameOfLifeComponent.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.toggleCell(e.getX(), e.getY());
            }
        });

        gameOfLifeComponent.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                controller.toggleCell(e.getX(), e.getY());
            }
        });

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

        JButton pasteButton = new JButton("Paste");

        pasteButton.addActionListener(e -> {
            try {
                String clipboardContents = (String) Toolkit.getDefaultToolkit()
                        .getSystemClipboard().getData(DataFlavor.stringFlavor);
                controller.paste(clipboardContents);

            } catch (IOException | UnsupportedFlavorException ex) {
                throw new RuntimeException(ex);
            }


        });
        controlPanel.add(pasteButton);
    }
}



