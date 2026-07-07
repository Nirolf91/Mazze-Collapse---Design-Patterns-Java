package mazecollapse.singleton;

import mazecollapse.command.InputHandler;
import mazecollapse.decorator.KeyAndLockMazeDecorator;
import mazecollapse.model.Difficulty;
import mazecollapse.model.Direction;
import mazecollapse.model.GameEvent;
import mazecollapse.model.Maze;
import mazecollapse.model.MoveResult;
import mazecollapse.model.Position;
import mazecollapse.observer.GameObserver;
import mazecollapse.prototype.EasyMazePrototype;
import mazecollapse.prototype.HardMazePrototype;
import mazecollapse.prototype.MazePrototype;
import mazecollapse.prototype.MediumMazePrototype;

import java.util.ArrayList;
import java.util.List;

public final class GameManager {
    private final List<GameObserver> observers = new ArrayList<>();
    private Maze currentMaze;
    private Difficulty currentDifficulty;
    private InputHandler inputHandler;

    private GameManager() {
    }

    public static GameManager getInstance() {
        return Holder.INSTANCE;
    }

    public void addObserver(GameObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void removeObserver(GameObserver observer) {
        observers.remove(observer);
    }

    public void startGame(Difficulty difficulty) {
        MazePrototype prototype = prototypeFor(difficulty);
        currentDifficulty = difficulty;
        currentMaze = prototype.createMaze();
        inputHandler = new InputHandler(currentMaze);
        notifyObservers(new GameEvent(GameEvent.Type.GAME_STARTED, difficulty, currentMaze, MoveResult.moved()));
    }

    public MoveResult movePlayer(Direction direction) {
        ensureGameStarted();
        MoveResult result = inputHandler.handle(direction);
        GameEvent.Type eventType = eventTypeFor(result);
        notifyObservers(new GameEvent(eventType, currentDifficulty, currentMaze, result));
        return result;
    }

    public void resetGame() {
        ensureGameStarted();
        startGame(currentDifficulty);
    }

    public Maze currentMaze() {
        ensureGameStarted();
        return currentMaze;
    }

    public Difficulty currentDifficulty() {
        return currentDifficulty;
    }

    private MazePrototype prototypeFor(Difficulty difficulty) {
        return switch (difficulty) {
            case EASY -> new EasyMazePrototype();
            case MEDIUM -> new MediumMazePrototype();
            case HARD -> new KeyAndLockMazeDecorator(
                    new HardMazePrototype(),
                    new Position(3, 3),
                    new Position(8, 6)
            );
        };
    }

    private GameEvent.Type eventTypeFor(MoveResult result) {
        if (!result.valid()) {
            return GameEvent.Type.GAME_OVER;
        }
        if (result.finished()) {
            return GameEvent.Type.LEVEL_FINISHED;
        }
        return GameEvent.Type.PLAYER_MOVED;
    }

    private void notifyObservers(GameEvent event) {
        for (GameObserver observer : List.copyOf(observers)) {
            observer.onGameEvent(event);
        }
    }

    private void ensureGameStarted() {
        if (currentMaze == null || inputHandler == null) {
            throw new IllegalStateException("Start a game before moving the player.");
        }
    }

    private static final class Holder {
        private static final GameManager INSTANCE = new GameManager();
    }
}
