package reiff.gameoflife;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class GameOfLifeControllerTest {

    private static final String GLIDER_RLE = """
            #N Glider
            #O Richard K. Guy
            #C The smallest, most common, and first discovered spaceship. Diagonal, has period 4 and speed c/4.
            #C www.conwaylife.com/wiki/index.php?title=Glider
            x = 3, y = 3, rule = B3/S23
            bob$2bo$3o!
    """.trim().replace("\n", "\r\n") + "\r\n";


    @Test
    void toggleCell() {
        //given
        GameOfLife model = mock();
        GameOfLifeComponent view = mock();
        final GameOfLifeController controller = new GameOfLifeController(model, view);
        doReturn(10).when(view).getCellSize();
        doReturn(100).when(model).getWidth();
        doReturn(100).when(model).getHeight();

        //when
        controller.toggleCell(50, 100);

        //then
        verify(model).setCell(5, 10, 1);
        verify(view).repaint();
    }

    @Test
    void toggleCellOff() {
        //given
        GameOfLife model = mock();
        GameOfLifeComponent view = mock();
        final GameOfLifeController controller = new GameOfLifeController(model, view);
        doReturn(10).when(view).getCellSize();
        doReturn(100).when(model).getWidth();
        doReturn(100).when(model).getHeight();
        doReturn(1).when(model).getCell(5, 10);

        //when
        controller.toggleCell(50, 100);

        //then
        verify(model).setCell(5, 10, 0);
        verify(view).repaint();
    }

    @Test
    public void pasteRle() {
        //given
        GameOfLife model = mock();
        GameOfLifeComponent view = mock();
        GameOfLifeController controller = new GameOfLifeController(model, view);

        //when
        controller.paste(GLIDER_RLE);

        verify(model).loadRleFromString(GLIDER_RLE);
        verify(view).repaint();
    }

    @Test
    public void pasteUrl() {

        //given
        GameOfLife model = mock();
        GameOfLifeComponent view = mock();
        GameOfLifeController controller = new GameOfLifeController(model, view);
        String rle = "https://conwaylife.com/patterns/glider.rle";

        //when
        controller.paste(rle);

        //then
        verify(model).loadRleFromString(GLIDER_RLE);
        verify(view).repaint();
    }

    @Test
    public void pasteFile() {

        //given
        GameOfLife model = mock();
        GameOfLifeComponent view = mock();
        GameOfLifeController controller = new GameOfLifeController(model, view);
        String filename = "glider.rle";

        //when
        controller.paste(filename);

        //then
        verify(model).loadRleFromString(GLIDER_RLE);
        verify(view).repaint();
    }
}