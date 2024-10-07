package reiff.gameoflife;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameOfLifeTest {
        @Test
        public void string() {
            //given
            GameOfLife game = new GameOfLife(3, 3);

            //when
            String actual = game.toString();

            //then
            assertEquals("000\n000\n000\n", actual);
        }

        @Test
         public void setCell() {
            //given
            GameOfLife game = new GameOfLife(3, 3);

            //when
            game.setCell(1, 2, 1);

            //then
            assertEquals("000\n000\n010\n", game.toString());
        }


        @Test
        public void nextGen() {
        //given
        GameOfLife game = new GameOfLife(3, 3);
        game.setCell(0, 1, 1);
        game.setCell(1, 1, 1);
        game.setCell(2, 1, 1);

        //when
        game.nextGen();

        //then
        assertEquals("010\n010\n010\n", game.toString());
    }

    @Test
    public void loadRleFromStringSimplePattern() {
        // given
        GameOfLife game = new GameOfLife(10, 10);

        String rle = "x = 5, y = 3\n"
                + "bo$2bo$3o!";



        // when
        game.loadRleFromString(rle);

        // then
        assertEquals("01000\n00100\n11100\n", game.toString());
    }

    @Test
    public void rleParsingSingleRow() {
        // Given
        GameOfLife game = new GameOfLife(0, 0);
        String rle = "x = 5, y = 1\nbo$";

        // When
        game.loadRleFromString(rle);

        // Then
        assertEquals("01000\n", game.toString());
    }


    @Test
    public void onlyLiveCells() {
        // Given
        GameOfLife game = new GameOfLife(0, 0);
        String rle = "x = 5, y = 1\n5o!";

        // When
        game.loadRleFromString(rle);

        // Then
        assertEquals("11111\n", game.toString());
    }

    @Test
    public void longDeadCellRuns() {
        // Given
        GameOfLife game = new GameOfLife(0, 0);
        String rle = "x = 5, y = 2\n5b$5b!";

        // When
        game.loadRleFromString(rle);

        // Then
        assertEquals("00000\n00000\n", game.toString());
    }

}
