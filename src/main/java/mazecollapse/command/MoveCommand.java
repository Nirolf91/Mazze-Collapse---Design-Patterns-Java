package mazecollapse.command;

import mazecollapse.model.Direction;
import mazecollapse.model.Maze;
import mazecollapse.model.MoveResult;

public final class MoveCommand implements Command {
    private final Maze maze;
    private final Direction direction;

    public MoveCommand(Maze maze, Direction direction) {
        this.maze = maze;
        this.direction = direction;
    }

    @Override
    public MoveResult execute() {
        return maze.movePlayer(direction);
    }
}
