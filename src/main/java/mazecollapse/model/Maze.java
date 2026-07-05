package mazecollapse.model;

import java.util.Arrays;

public final class Maze implements Cloneable {
    private final CellType[][] cells;
    private Position playerPosition;
    private Position finishPosition;

    public Maze(CellType[][] cells) {
        this.cells = copyCells(cells);
        locateSpecialCells();
    }

    private Maze(CellType[][] cells, Position playerPosition, Position finishPosition) {
        this.cells = copyCells(cells);
        this.playerPosition = playerPosition;
        this.finishPosition = finishPosition;
    }

    public int width() {
        return cells[0].length;
    }

    public int height() {
        return cells.length;
    }

    public CellType cellAt(Position position) {
        if (!isInside(position)) {
            return CellType.WALL;
        }
        return cells[position.y()][position.x()];
    }

    public void setCell(Position position, CellType type) {
        if (!isInside(position)) {
            throw new IllegalArgumentException("Position outside maze: " + position);
        }
        cells[position.y()][position.x()] = type;
        if (type == CellType.PLAYER) {
            playerPosition = position;
        }
        if (type == CellType.FINISH) {
            finishPosition = position;
        }
    }

    public MoveResult movePlayer(Direction direction) {
        Position target = playerPosition.move(direction);
        if (!isInside(target)) {
            return MoveResult.invalid("The player moved outside the maze.");
        }

        CellType targetCell = cellAt(target);
        if (targetCell == CellType.WALL || targetCell == CellType.LOCK || targetCell == CellType.VISITED) {
            return MoveResult.invalid("The player hit an invalid cell.");
        }

        if (targetCell == CellType.KEY) {
            unlockAllLocks();
        }

        cells[playerPosition.y()][playerPosition.x()] = CellType.VISITED;
        playerPosition = target;
        cells[target.y()][target.x()] = CellType.PLAYER;

        if (target.equals(finishPosition)) {
            return MoveResult.finished(isLevelComplete());
        }
        return MoveResult.moved();
    }

    public boolean isLevelComplete() {
        for (CellType[] row : cells) {
            for (CellType cell : row) {
                if (cell == CellType.PATH || cell == CellType.KEY || cell == CellType.LOCK) {
                    return false;
                }
            }
        }
        return true;
    }

    public Position playerPosition() {
        return playerPosition;
    }

    @Override
    public Maze clone() {
        return new Maze(cells, playerPosition, finishPosition);
    }

    private boolean isInside(Position position) {
        return position.y() >= 0
                && position.y() < height()
                && position.x() >= 0
                && position.x() < width();
    }

    private void unlockAllLocks() {
        for (int y = 0; y < height(); y++) {
            for (int x = 0; x < width(); x++) {
                if (cells[y][x] == CellType.LOCK) {
                    cells[y][x] = CellType.PATH;
                }
            }
        }
    }

    private void locateSpecialCells() {
        for (int y = 0; y < height(); y++) {
            for (int x = 0; x < width(); x++) {
                Position position = new Position(x, y);
                if (cells[y][x] == CellType.PLAYER) {
                    playerPosition = position;
                }
                if (cells[y][x] == CellType.FINISH) {
                    finishPosition = position;
                }
            }
        }
        if (playerPosition == null || finishPosition == null) {
            throw new IllegalArgumentException("A maze must contain one player and one finish cell.");
        }
    }

    private static CellType[][] copyCells(CellType[][] source) {
        CellType[][] copy = new CellType[source.length][];
        for (int y = 0; y < source.length; y++) {
            copy[y] = Arrays.copyOf(source[y], source[y].length);
        }
        return copy;
    }
}
