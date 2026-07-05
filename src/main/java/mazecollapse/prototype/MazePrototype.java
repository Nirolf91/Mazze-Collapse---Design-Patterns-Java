package mazecollapse.prototype;

import mazecollapse.model.Difficulty;
import mazecollapse.model.Maze;

public interface MazePrototype {
    Difficulty difficulty();

    Maze createMaze();
}
