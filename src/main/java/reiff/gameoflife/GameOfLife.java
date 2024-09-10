package reiff.gameoflife;

public class GameOfLife {
    private int[][] field;
    private final int height;
    private final int width;

    public GameOfLife(int height, int width) {
        this.height = height;
        this.width = width;
        field = new int[height][width];
    }

    public void setCell(int x, int y, int state) {
        field[y][x] = state;
    }

    public String toString() {

        StringBuilder builder = new StringBuilder();

        for (int[] ints : field) {
            for (int anInt : ints) {
                builder.append(anInt);
            }
            builder.append("\n");
        }

        return builder.toString();
    }

    public void nextGen() {
        int[][] nextField = new int[height][width];


        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int liveNeighbors = countLiveNeighbors(x, y);

                if (field[y][x] == 1) {
                    if (liveNeighbors < 2 || liveNeighbors > 3) {
                        nextField[y][x] = 0;
                    } else {
                        nextField[y][x] = 1;
                    }
                } else {
                    if (liveNeighbors == 3) {
                        nextField[y][x] = 1;
                    } else {
                        nextField[y][x] = 0;
                    }
                }
            }
        }


        field = nextField;
    }

    private int countLiveNeighbors(int x, int y) {
        int liveCount = 0;


        for (int neighborOffsetY = -1; neighborOffsetY <= 1; neighborOffsetY++) {
            for (int neighborOffsetX = -1; neighborOffsetX <= 1; neighborOffsetX++) {
                if (neighborOffsetX == 0 && neighborOffsetY == 0) {
                    continue;
                }

                int neighborX = x + neighborOffsetX;
                int neighborY = y + neighborOffsetY;


                if (neighborX >= 0 && neighborX < width && neighborY >= 0 && neighborY < height) {
                    liveCount += field[neighborY][neighborX];
                }
            }
        }

        return liveCount;
    }
}
