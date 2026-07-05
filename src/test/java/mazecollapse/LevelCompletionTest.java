package mazecollapse;

import mazecollapse.model.CellType;
import mazecollapse.model.Maze;
import mazecollapse.model.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LevelCompletionTest {
    @Test
    void reportsIncompleteWhenPathsRemain() {
        Maze maze = new Maze(new CellType[][]{
                {CellType.PLAYER, CellType.PATH, CellType.FINISH}
        });

        assertFalse(maze.isLevelComplete(), "A maze with remaining path cells should not be complete.");
    }

    @Test
    void reportsCompleteWhenOnlyVisitedAndFinishRemain() {
        Maze maze = new Maze(new CellType[][]{
                {CellType.PLAYER, CellType.FINISH}
        });

        maze.setCell(new Position(0, 0), CellType.VISITED);

        assertTrue(maze.isLevelComplete(), "A maze with no path/key/lock cells should be complete.");
    }
}
