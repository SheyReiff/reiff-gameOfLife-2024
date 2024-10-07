package reiff.gameoflife;

import org.apache.commons.io.IOUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;


public class GameOfLifeFrame extends JFrame {

    public GameOfLifeFrame() {
        setSize(800, 600);
        setTitle("Game Of Life");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        GameOfLife game = new GameOfLife(300, 300);
        GameOfLifeComponent gameOfLifeComponent = new GameOfLifeComponent(game, 20);
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

                if (clipboardContents.startsWith("http")) {
                    InputStream in = new URL(clipboardContents).openStream();
                    String rleContents = IOUtils.toString(in, StandardCharsets.UTF_8);
                    game.loadRleFromString(rleContents);
                } else if (new File(clipboardContents).exists()) {
                    FileInputStream fisTargetFile = new FileInputStream(new File(clipboardContents));
                    String rleContents = IOUtils.toString(fisTargetFile, StandardCharsets.UTF_8);
                    game.loadRleFromString(rleContents);
                } else {
                    game.loadRleFromString(clipboardContents);
                }

                gameOfLifeComponent.repaint();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        controlPanel.add(pasteButton);

    }

}

