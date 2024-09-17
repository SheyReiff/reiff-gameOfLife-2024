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

    public int getHeight() {
        return field.length;
    }

    public int getWidth() {
        return field[0].length;
    }

    public int getCell(int x, int y) {
        return field[y][x];
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

                if (field[y][x] == 1 && (liveNeighbors == 2 || liveNeighbors == 3)) {
                    nextField[y][x] = 1;
                } else if (field[y][x] == 0 && liveNeighbors == 3) {
                    nextField[y][x] = 1;
                }
            }
        }

        field = nextField;
    }

    private int countLiveNeighbors(int x, int y) {
        int liveCount = 0;

        for (int offsetY = -1; offsetY <= 1; offsetY++) {
            for (int offsetX = -1; offsetX <= 1; offsetX++) {
                if (offsetX == 0 && offsetY == 0) {
                    continue;  // Skip the cell itself
                }

                int neighborX = x + offsetX;
                int neighborY = y + offsetY;


                if (neighborX >= 0 && neighborX < width && neighborY >= 0 && neighborY < height) {
                    if (field[neighborY][neighborX] == 1) {
                        liveCount++;
                    }
                }
            }
        }

        return liveCount;
    }

}
