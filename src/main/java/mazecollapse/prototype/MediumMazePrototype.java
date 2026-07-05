package mazecollapse.prototype;

import mazecollapse.model.Difficulty;

public final class MediumMazePrototype extends AbstractMazePrototype {
    public MediumMazePrototype() {
        super(Difficulty.MEDIUM, new String[]{
                "#P....##",
                "##....##",
                "##...###",
                "#....###",
                "#....###",
                "###..###",
                "##..####",
                "##.....F"
        });
    }
}
