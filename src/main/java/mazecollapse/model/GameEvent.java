package mazecollapse.model;

public record GameEvent(Type type, Difficulty difficulty, Maze maze, MoveResult moveResult) {
    public enum Type {
        GAME_STARTED,
        PLAYER_MOVED,
        GAME_OVER,
        LEVEL_FINISHED
    }
}
