package reiff.gameoflife;

import org.apache.commons.io.IOUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class GameOfLifeController {

    private final GameOfLife model;
    private final GameOfLifeComponent view;


    public GameOfLifeController(GameOfLife model, GameOfLifeComponent view) {
        this.model = model;
        this.view = view;
    }

    public void toggleCell(int screenX, int screenY) {

        int x = screenX / view.getCellSize();
        int y = screenY / view.getCellSize();
        if (x < model.getWidth() && y < model.getHeight()) {
            int currentState = model.getCell(x, y);
            model.setCell(x, y, currentState == 1 ? 0 : 1);
            view.repaint();
        }
    }

    public void paste(String clipboardContents) {
        try {

            if (clipboardContents.startsWith("http")) {
                InputStream in = new URL(clipboardContents).openStream();
                String rleContents = IOUtils.toString(in, StandardCharsets.UTF_8);
                model.loadRleFromString(rleContents);
            } else if (new File(clipboardContents).exists()) {
                FileInputStream fisTargetFile = new FileInputStream(clipboardContents);
                String rleContents = IOUtils.toString(fisTargetFile, StandardCharsets.UTF_8);
                model.loadRleFromString(rleContents);
            } else {
                model.loadRleFromString(clipboardContents);
            }

            view.repaint();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void startTimer(){

    }

    public void stopTimer(){

    }
}

