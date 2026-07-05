package mazecollapse;

import mazecollapse.model.Difficulty;
import mazecollapse.model.Direction;
import mazecollapse.model.Maze;
import mazecollapse.model.MoveResult;
import mazecollapse.model.Position;
import mazecollapse.prototype.EasyMazePrototype;
import mazecollapse.singleton.GameManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MazeMovementTest {
    @Test
    void movesPlayerToValidPath() {
        Maze maze = new EasyMazePrototype().createMaze();
        MoveResult result = maze.movePlayer(Direction.RIGHT);

        assertTrue(result.valid(), "Moving right from the Easy start should be valid.");
        assertEquals(new Position(2, 0), maze.playerPosition(), "Player position should update after a valid move.");
    }

    @Test
    void rejectsInvalidWallMove() {
        GameManager gameManager = GameManager.getInstance();
        gameManager.startGame(Difficulty.EASY);
        MoveResult result = gameManager.movePlayer(Direction.LEFT);

        assertFalse(result.valid(), "Moving left from the Easy start should hit a wall.");
    }
}
