package mazecollapse.prototype;

import mazecollapse.model.Difficulty;

public final class HardMazePrototype extends AbstractMazePrototype {
    public HardMazePrototype() {
        super(Difficulty.HARD, new String[]{
                "##########",
                "#P.......#",
                "########.#",
                "#........#",
                "#.########",
                "#........#",
                "########.#",
                "#........#",
                "#F########",
                "##########"
        });
    }
}
