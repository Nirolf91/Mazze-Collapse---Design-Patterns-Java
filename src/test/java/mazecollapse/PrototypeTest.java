package mazecollapse;

import mazecollapse.model.Direction;
import mazecollapse.model.Maze;
import mazecollapse.model.Position;
import mazecollapse.prototype.EasyMazePrototype;
import mazecollapse.prototype.MazePrototype;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PrototypeTest {
    @Test
    void createsIndependentMazeInstances() {
        MazePrototype prototype = new EasyMazePrototype();
        Maze firstMaze = prototype.createMaze();
        Maze secondMaze = prototype.createMaze();

        firstMaze.movePlayer(Direction.RIGHT);

        assertEquals(new Position(2, 0), firstMaze.playerPosition(), "First maze should move independently.");
        assertEquals(new Position(1, 0), secondMaze.playerPosition(), "Second maze should preserve the prototype start state.");
    }
}
