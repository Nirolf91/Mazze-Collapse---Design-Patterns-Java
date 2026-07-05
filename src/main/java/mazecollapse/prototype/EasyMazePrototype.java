package mazecollapse.prototype;

import mazecollapse.model.Difficulty;

public final class EasyMazePrototype extends AbstractMazePrototype {
    public EasyMazePrototype() {
        super(Difficulty.EASY, new String[]{
                "#P..#",
                "##..#",
                "#..##",
                "#..##",
                "##.F#"
        });
    }
}
