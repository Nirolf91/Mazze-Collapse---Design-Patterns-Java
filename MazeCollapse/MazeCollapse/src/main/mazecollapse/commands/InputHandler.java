package main.mazecollapse.commands;

import main.mazecollapse.prototype.MazePrototype;

// Clasa care gestionează inputul și execută comenzi
public class InputHandler {
    private final Command moveRightCommand;
    private final Command moveLeftCommand;
    private final Command moveUpCommand;
    private final Command moveDownCommand;


    public InputHandler(MazePrototype maze) {
        this.moveRightCommand = new MoveRightCommand(maze);
        this.moveLeftCommand = new MoveLeftCommand(maze);
        this.moveDownCommand = new MoveDownCommand(maze);
        this.moveUpCommand = new MoveUpCommand(maze);


    }

    public void handleCommand(String input) {
        if ("MOVE_RIGHT".equalsIgnoreCase(input)) {
            moveRightCommand.execute();
        }
        if ("MOVE_UP".equalsIgnoreCase(input)) {
            moveUpCommand.execute();
        }
        if ("MOVE_LEFT".equalsIgnoreCase(input)) {
            moveLeftCommand.execute();
        }
        if ("MOVE_DOWN".equalsIgnoreCase(input)) {
            moveDownCommand.execute();
        }

        // ... similar pentru alte direcții ...
    }
}