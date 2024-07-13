package main.mazecollapse.commands;

import main.mazecollapse.prototype.MazePrototype;

public class MoveDownCommand implements Command {
    private MazePrototype maze;

    public MoveDownCommand(MazePrototype maze) {
        this.maze = maze;
    }

    @Override
    public void execute() {
        maze.movePlayerDown();
    }
}