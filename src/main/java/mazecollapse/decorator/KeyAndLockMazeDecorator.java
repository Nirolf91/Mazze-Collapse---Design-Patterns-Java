package mazecollapse.decorator;

import mazecollapse.model.CellType;
import mazecollapse.model.Difficulty;
import mazecollapse.model.Maze;
import mazecollapse.model.Position;
import mazecollapse.prototype.MazePrototype;

public final class KeyAndLockMazeDecorator implements MazePrototype {
    private final MazePrototype wrappedPrototype;
    private final Position keyPosition;
    private final Position lockPosition;

    public KeyAndLockMazeDecorator(MazePrototype wrappedPrototype, Position keyPosition, Position lockPosition) {
        this.wrappedPrototype = wrappedPrototype;
        this.keyPosition = keyPosition;
        this.lockPosition = lockPosition;
    }

    @Override
    public Difficulty difficulty() {
        return wrappedPrototype.difficulty();
    }

    @Override
    public Maze createMaze() {
        Maze maze = wrappedPrototype.createMaze();
        maze.setCell(keyPosition, CellType.KEY);
        maze.setCell(lockPosition, CellType.LOCK);
        return maze;
    }
}
