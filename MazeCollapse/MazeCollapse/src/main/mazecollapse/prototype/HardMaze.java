package main.mazecollapse.prototype;

public class HardMaze extends MazePrototype {
    public HardMaze(MazePrototype prototype) {
        super(prototype.getWidth(), prototype.getHeight());

        // Clonăm matricea prototipului care este inițial complet plină cu 0
        for (int y = 0; y < prototype.getHeight(); y++) {
            for (int x = 0; x < prototype.getWidth(); x++) {
                this.setCellValue(x, y, prototype.getCellValue(x, y));
            }
        }

        // Personalizăm clona pentru a defini un labirint dificil
        customizeMaze();
    }

    private void customizeMaze() {
        setCellValue(1,1, 2);
        for (int i = 0; i <= 9; i++) {
            setCellValue(i, 0, 0);
        }
        for (int i = 2; i <= 6; i++) {
            setCellValue(i, 1, 1);
        }
        for (int i = 1; i <= 6; i++) {
            setCellValue(i, 2, 1);
        }
        setCellValue(8, 2, 1);
        setCellValue(1, 3, 1);

        for (int i = 5; i <= 6; i++) {
            setCellValue(i, 3, 1);
        }

            setCellValue(8, 3, 1);
        for (int i = 1; i <= 2; i++) {
            setCellValue(i, 4, 1);
        }

        setCellValue(7, 4, 1);

        for (int i = 1; i <= 3; i++) {
            setCellValue(i, 5, 1);
        }

        for (int i = 5; i <= 7; i++) {
            setCellValue(i, 5, 1);
        }

        for (int i = 3; i <= 5; i++) {
            setCellValue(i, 6, 1);
        }

        for (int i = 3; i <= 7; i++) {
            setCellValue(i, 7, 1);
        }

        for (int i = 3; i <= 7; i++) {
            setCellValue(i, 8, 1);
        }

        for (int i = 0; i <= 9; i++) {
            setCellValue(i, 9, 0);
        }
        setCellValue(8, 1, 3);
    }
}

