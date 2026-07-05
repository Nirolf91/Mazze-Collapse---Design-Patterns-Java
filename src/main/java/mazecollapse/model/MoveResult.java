package mazecollapse.model;

public record MoveResult(boolean valid, boolean finished, boolean levelComplete, String message) {
    public static MoveResult moved() {
        return new MoveResult(true, false, false, "Move accepted.");
    }

    public static MoveResult invalid(String message) {
        return new MoveResult(false, false, false, message);
    }

    public static MoveResult finished(boolean levelComplete) {
        String message = levelComplete
                ? "Level completed correctly."
                : "Finish reached before visiting all required cells.";
        return new MoveResult(true, true, levelComplete, message);
    }
}
