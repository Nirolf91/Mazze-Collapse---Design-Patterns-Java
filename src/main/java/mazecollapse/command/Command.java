package mazecollapse.command;

import mazecollapse.model.MoveResult;

public interface Command {
    MoveResult execute();
}
