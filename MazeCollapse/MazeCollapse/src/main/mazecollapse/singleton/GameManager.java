package main.mazecollapse.singleton;

import main.mazecollapse.commands.InputHandler;
import main.mazecollapse.decorators.KeyAndLockDecorator;
import main.mazecollapse.observer.LevelCompletionObserver;
import main.mazecollapse.prototype.EasyMaze;
import main.mazecollapse.prototype.HardMaze;
import main.mazecollapse.prototype.MazePrototype;
import main.mazecollapse.prototype.MediumMaze;

import java.util.Observable;

public class GameManager extends Observable {
    private static GameManager instance;
    private MazePrototype currentMaze;
    private InputHandler inputHandler;
    private String currentDifficulty;



    private GameManager() {
    }

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public void startGame(String difficulty) {
        this.currentDifficulty = difficulty;
        System.out.println("Starting game with difficulty: " + difficulty); // Logare pentru debug
        switch (difficulty) {
            case "Easy" -> currentMaze = createEasyMaze();
            case "Medium" -> currentMaze = createMediumMaze();
            case "Hard" -> currentMaze = createHardMaze();
            default -> throw new IllegalArgumentException("Unknown difficulty level: " + difficulty);
        }
        if (currentMaze != null) {
            LevelCompletionObserver levelCompletionObserver = new LevelCompletionObserver(currentMaze, difficulty);
            currentMaze.addObserver(levelCompletionObserver);
            setChanged(); // Marchează că starea s-a schimbat
            notifyObservers(currentMaze); // Notifică observatorii cu noul labirint
            System.out.println("Game started with a " + difficulty + " maze."); // Logare pentru confirmare
            inputHandler = new InputHandler(currentMaze);
        } else {
            System.out.println("Failed to create a " + difficulty + " maze."); // Logare în caz de eroare
        }
    }
    private MazePrototype createEasyMaze() {
        MazePrototype easyPrototype = new MazePrototype(5, 5); // Dimensiune 5x5 pentru nivelul Easy
        // Personalizează labirintul Easy aici...
        return new EasyMaze(easyPrototype);
    }

    private MazePrototype createMediumMaze() {
        MazePrototype mediumPrototype = new MazePrototype(8, 8); // Dimensiune 8x8 pentru nivelul Medium
        // Personalizează labirintul Medium aici...
        return new MediumMaze(mediumPrototype);
    }

    private MazePrototype createHardMaze() {
        MazePrototype hardPrototype = new MazePrototype(10, 10); // Dimensiune 8x8 pentru nivelul Medium
        // Personalizează labirintul Medium aici...
        KeyAndLockDecorator decorator = new KeyAndLockDecorator(hardPrototype);
        decorator.addKeyAndLock(3,4,7,3);
        return new HardMaze(hardPrototype);
    }


    public MazePrototype getCurrentMaze() {
        return currentMaze;
    }

    public boolean movePlayerUp() {
        inputHandler.handleCommand("MOVE_UP");
        return currentMaze.lastMoveWasValid();
    }

    public boolean movePlayerDown() {
        inputHandler.handleCommand("MOVE_DOWN");
        return currentMaze.lastMoveWasValid();
    }

    public boolean movePlayerLeft() {
        inputHandler.handleCommand("MOVE_LEFT");
        return currentMaze.lastMoveWasValid();
    }

    public boolean movePlayerRight() {
        inputHandler.handleCommand("MOVE_RIGHT");
        return currentMaze.lastMoveWasValid();
    }

    public void resetGame() {
        // Aici poți decide să reîncepi jocul cu același nivel sau cu unul nou
        startGame(currentDifficulty); // de exemplu, reîncepe cu nivelul Easy
    }
}



    // Alte metode necesare pentru gestionarea jocului

// Aici poți adăuga și alte clase pentru EasyMaze, MediumMaze și HardMaze, dacă este necesar.
