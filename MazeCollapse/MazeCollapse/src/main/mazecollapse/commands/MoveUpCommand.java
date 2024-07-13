package main.mazecollapse.commands;

import main.mazecollapse.prototype.MazePrototype;

public class MoveUpCommand implements Command {
    private MazePrototype maze;

    public MoveUpCommand(MazePrototype maze) {
        this.maze = maze;
    }

    @Override
    public void execute() {
        if(maze.movePlayerUp())
            return;
    }
}