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

}
