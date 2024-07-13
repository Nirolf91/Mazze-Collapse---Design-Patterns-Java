package main.mazecollapse.commands;

import main.mazecollapse.prototype.MazePrototype;

public class MoveLeftCommand implements Command {
    private MazePrototype maze;


    public MoveLeftCommand(MazePrototype maze) {
        this.maze = maze;
    }

    @Override
    public void execute() {
        maze.movePlayerLeft();
    }
}