package mazecollapse;

import mazecollapse.decorator.KeyAndLockMazeDecorator;
import mazecollapse.model.CellType;
import mazecollapse.model.Direction;
import mazecollapse.model.Maze;
import mazecollapse.model.Position;
import mazecollapse.prototype.HardMazePrototype;
import mazecollapse.prototype.MazePrototype;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KeyAndLockDecoratorTest {
    @Test
    void addsKeyAndLockToWrappedPrototype() {
        Maze maze = decoratedHardMaze().createMaze();

        assertEquals(CellType.KEY, maze.cellAt(new Position(3, 4)), "Decorator should add a key.");
        assertEquals(CellType.LOCK, maze.cellAt(new Position(7, 3)), "Decorator should add a lock.");
    }

    @Test
    void collectingKeyUnlocksLock() {
        Maze maze = decoratedHardMaze().createMaze();

        assertTrue(maze.movePlayer(Direction.DOWN).valid(), "Move down should be valid.");
        assertTrue(maze.movePlayer(Direction.DOWN).valid(), "Move down should be valid.");
        assertTrue(maze.movePlayer(Direction.DOWN).valid(), "Move down should be valid.");
        assertTrue(maze.movePlayer(Direction.RIGHT).valid(), "Move right should be valid.");
        assertTrue(maze.movePlayer(Direction.RIGHT).valid(), "Moving onto the key should be valid.");

        assertEquals(CellType.PATH, maze.cellAt(new Position(7, 3)), "Collecting the key should unlock all locks.");
    }

    private static MazePrototype decoratedHardMaze() {
        return new KeyAndLockMazeDecorator(
                new HardMazePrototype(),
                new Position(3, 4),
                new Position(7, 3)
        );
    }
}
