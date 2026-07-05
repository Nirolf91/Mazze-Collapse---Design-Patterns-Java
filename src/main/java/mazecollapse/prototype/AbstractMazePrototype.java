package mazecollapse.prototype;

import mazecollapse.model.CellType;
import mazecollapse.model.Difficulty;
import mazecollapse.model.Maze;

abstract class AbstractMazePrototype implements MazePrototype {
    private final Difficulty difficulty;
    private final Maze template;

    protected AbstractMazePrototype(Difficulty difficulty, String[] layout) {
        this.difficulty = difficulty;
        this.template = new Maze(parseLayout(layout));
    }

    @Override
    public Difficulty difficulty() {
        return difficulty;
    }

    @Override
    public Maze createMaze() {
        return template.clone();
    }

    private static CellType[][] parseLayout(String[] layout) {
        int height = layout.length;
        int width = layout[0].length();
        CellType[][] cells = new CellType[height][width];

        for (int y = 0; y < height; y++) {
            if (layout[y].length() != width) {
                throw new IllegalArgumentException("All maze rows must have the same width.");
            }
            for (int x = 0; x < width; x++) {
                cells[y][x] = parseCell(layout[y].charAt(x));
            }
        }
        return cells;
    }

    private static CellType parseCell(char cell) {
        return switch (cell) {
            case '#' -> CellType.WALL;
            case '.' -> CellType.PATH;
            case 'P' -> CellType.PLAYER;
            case 'F' -> CellType.FINISH;
            default -> throw new IllegalArgumentException("Unknown maze cell: " + cell);
        };
    }
}
