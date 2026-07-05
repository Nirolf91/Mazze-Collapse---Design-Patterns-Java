package mazecollapse;

import mazecollapse.model.Difficulty;
import mazecollapse.model.Direction;
import mazecollapse.model.MoveResult;
import mazecollapse.singleton.GameManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameManagerTest {
    @Test
    void keepsSingleSharedInstance() {
        assertSame(GameManager.getInstance(), GameManager.getInstance(), "GameManager should expose one shared singleton instance.");
    }

    @Test
    void startsAndResetsSelectedDifficulty() {
        GameManager gameManager = GameManager.getInstance();
        gameManager.startGame(Difficulty.MEDIUM);
        gameManager.movePlayer(Direction.RIGHT);
        gameManager.resetGame();

        assertEquals(Difficulty.MEDIUM, gameManager.currentDifficulty(), "Reset should keep the selected difficulty.");
    }

    @Test
    void delegatesMovementThroughCommandHandler() {
        GameManager gameManager = GameManager.getInstance();
        gameManager.startGame(Difficulty.HARD);
        MoveResult result = gameManager.movePlayer(Direction.RIGHT);

        assertTrue(result.valid(), "GameManager should execute movement through the command input handler.");
    }
}
