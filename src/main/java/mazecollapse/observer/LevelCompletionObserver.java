package mazecollapse.observer;

import mazecollapse.model.GameEvent;

public final class LevelCompletionObserver implements GameObserver {
    private String lastCompletionMessage = "";

    @Override
    public void onGameEvent(GameEvent event) {
        if (event.type() != GameEvent.Type.LEVEL_FINISHED) {
            return;
        }

        lastCompletionMessage = event.moveResult().levelComplete()
                ? "Level " + event.difficulty().label() + " completed correctly."
                : "Level " + event.difficulty().label() + " reached finish before all cells were visited.";
    }

    public String lastCompletionMessage() {
        return lastCompletionMessage;
    }
}
