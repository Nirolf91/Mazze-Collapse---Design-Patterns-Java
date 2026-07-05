package mazecollapse.command;

import mazecollapse.model.Direction;
import mazecollapse.model.Maze;
import mazecollapse.model.MoveResult;

import java.util.EnumMap;
import java.util.Map;

public final class InputHandler {
    private final Map<Direction, Command> commands = new EnumMap<>(Direction.class);

    public InputHandler(Maze maze) {
        for (Direction direction : Direction.values()) {
            commands.put(direction, new MoveCommand(maze, direction));
        }
    }

    public MoveResult handle(Direction direction) {
        return commands.get(direction).execute();
    }
}
