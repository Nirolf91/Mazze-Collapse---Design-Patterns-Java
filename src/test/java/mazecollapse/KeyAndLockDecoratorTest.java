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
        assertEquals(CellType.LOCK, maze.cellAt(new Position(7, 5)), "Decorator should add a lock.");
    }

    @Test
    void collectingKeyUnlocksLock() {
        Maze maze = decoratedHardMaze().createMaze();

        move(maze, Direction.RIGHT, 7);
        move(maze, Direction.DOWN, 1);
        move(maze, Direction.LEFT, 7);
        move(maze, Direction.DOWN, 1);
        move(maze, Direction.RIGHT, 7);
        move(maze, Direction.DOWN, 1);
        move(maze, Direction.LEFT, 5);

        assertEquals(CellType.PATH, maze.cellAt(new Position(7, 5)), "Collecting the key should unlock all locks.");
    }

    @Test
    void hardLevelCanBeCompletedByVisitingEveryCellBeforeFinish() {
        Maze maze = decoratedHardMaze().createMaze();

        move(maze, Direction.RIGHT, 7);
        move(maze, Direction.DOWN, 1);
        move(maze, Direction.LEFT, 7);
        move(maze, Direction.DOWN, 1);
        move(maze, Direction.RIGHT, 7);
        move(maze, Direction.DOWN, 1);
        move(maze, Direction.LEFT, 7);
        move(maze, Direction.DOWN, 1);
        move(maze, Direction.RIGHT, 7);
        move(maze, Direction.DOWN, 1);
        move(maze, Direction.LEFT, 7);
        move(maze, Direction.DOWN, 1);
        move(maze, Direction.RIGHT, 7);
        move(maze, Direction.DOWN, 1);
        move(maze, Direction.LEFT, 7);

        assertTrue(maze.isLevelComplete(), "Hard level should be complete after the full snake route.");
    }

    private static MazePrototype decoratedHardMaze() {
        return new KeyAndLockMazeDecorator(
                new HardMazePrototype(),
                new Position(3, 4),
                new Position(7, 5)
        );
    }

    private static void move(Maze maze, Direction direction, int times) {
        for (int i = 0; i < times; i++) {
            assertTrue(maze.movePlayer(direction).valid(), "Expected valid move: " + direction);
        }
    }
}
