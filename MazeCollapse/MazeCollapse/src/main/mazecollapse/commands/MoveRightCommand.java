package main.mazecollapse.commands;

import main.mazecollapse.prototype.MazePrototype;

public class MoveRightCommand implements Command {
    private MazePrototype maze;

    public MoveRightCommand(MazePrototype maze) {
        this.maze = maze;
    }

    @Override
    public void execute() {
        maze.movePlayerRight();
    }
}