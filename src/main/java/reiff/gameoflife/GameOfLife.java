package reiff.gameoflife;

public class GameOfLife {
    private int[][] field;
    private final int height;
    private final int width;

    public GameOfLife(int height, int width) {
        this.height = Math.max(height, 100);
        this.width = Math.max(width, 100);
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

    public void loadRleFromString(String rle) {

        String[] lines = rle.split("\n");
        int currentX = 0;
        int currentY = 0;
        boolean headerRead = false;
        int offsetX = 0;
        int offsetY = 0;


        for (String line : lines) {
            line = line.trim();

            if (!headerRead && line.startsWith("x")) {
                String[] parts = line.split(",");
                int patternWidth = Integer.parseInt(parts[0].split("=")[1].trim());
                int patternHeight = Integer.parseInt(parts[1].split("=")[1].trim());

                 offsetX = (width - patternWidth) / 2;
                 offsetY = (height - patternHeight) / 2;

                currentX = offsetX;
                currentY = offsetY;

                headerRead = true;
                continue;
            }

            int runCount = 0;
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);

                if (Character.isDigit(c)) {
                    runCount = runCount * 10 + (c - '0');
                } else if (c == 'b') {
                    if (runCount == 0) {
                        runCount = 1;
                    }
                    for (int j = 0; j < runCount; j++) {
                        setCell(currentX++, currentY, 0);
                        if (currentX >= width) {
                            break;
                        }
                    }
                    runCount = 0;
                } else if (c == 'o') {
                    if (runCount == 0) {
                        runCount = 1;
                    }
                    for (int j = 0; j < runCount; j++) {
                        setCell(currentX++, currentY, 1);
                        if (currentX >= width) {
                            break;
                        }
                    }
                    runCount = 0;
                } else if (c == '$') {
                    currentX = offsetX;
                    currentY++;
                    if (currentY >= height) {
                        break;
                    }
                } else if (c == '!') {
                    break;
                }
            }
        }
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
