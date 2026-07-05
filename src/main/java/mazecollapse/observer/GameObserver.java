package mazecollapse.observer;

import mazecollapse.model.GameEvent;

public interface GameObserver {
    void onGameEvent(GameEvent event);
}
