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
        GameOfLife game = new GameOfLife(100, 100);
        String rle = "x = 4, y = 4\n"
                +
                "4o$"
                +
                "4b$"
                +
                "!\n";

        // when
        game.loadRleFromString(rle);

        int offSetX = (100 - 4) / 2;
        int offSetY = (100 - 4) / 2;

        // then
        assertEquals(1, game.getCell(offSetX, offSetY));     // Top-left of the block
        assertEquals(1, game.getCell(offSetX + 1, offSetY)); // Top-middle of the block
        assertEquals(1, game.getCell(offSetX + 2, offSetY)); // Top-right of the block
        assertEquals(1, game.getCell(offSetX + 3, offSetY)); // Top-right of the block

        assertEquals(0, game.getCell(offSetX, offSetY + 1)); // Bottom-left of the block
        assertEquals(0, game.getCell(offSetX + 1, offSetY + 1)); // Bottom-middle of the block
        assertEquals(0, game.getCell(offSetX + 2, offSetY + 1)); // Bottom-middle of the block
        assertEquals(0, game.getCell(offSetX + 3, offSetY + 1)); // Bottom-right of the block
    }

}
