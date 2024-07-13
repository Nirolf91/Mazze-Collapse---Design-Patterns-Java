package main.mazecollapse.prototype;

public class MediumMaze extends MazePrototype {
    public MediumMaze(MazePrototype prototype) {
        super(prototype.getWidth(), prototype.getHeight());

        // Clonăm matricea prototipului care este inițial complet plină cu 0
        for (int y = 0; y < prototype.getHeight(); y++) {
            for (int x = 0; x < prototype.getWidth(); x++) {
                this.setCellValue(x, y, prototype.getCellValue(x, y));
            }
        }

        // Personalizăm clona pentru a defini un labirint mediu
        customizeMaze();
    }

    private void customizeMaze() {
        setCellValue(1, 0, 2);
        for (int i = 2; i <= 5; i++) {
            setCellValue(i, 0, 1);
        }
        for (int i = 2; i <= 5; i++) {
            setCellValue(i, 1, 1);
        }
        for (int i = 2; i <= 4; i++) {
            setCellValue(i, 2, 1);
        }
        for (int i = 1; i <= 4; i++) {
            setCellValue(i, 3, 1);
        }
        for (int i = 1; i <= 4; i++) {
            setCellValue(i, 4, 1);
        }
        for (int i = 3; i <= 4; i++) {
            setCellValue(i, 5, 1);
        }
        for (int i = 2; i <= 3; i++) {
            setCellValue(i, 6, 1);
        }

        for (int i = 2; i <= 6; i++) {
            setCellValue(i, 7, 1);
        }

        setCellValue(7, 7, 3); // Locatia de sfarsit
    }
}
//for (int i = 2; i <= 5; i++) {
//            setCellValue(i, 0, 1);
//        }
//        for (int i = 2; i <= 5; i++) {
//            setCellValue(i, 1, 1);
//        }
//        for (int i = 2; i <= 4; i++) {
//            setCellValue(i, 2, 1);
//        }
//        for (int i = 1; i <= 4; i++) {
//            setCellValue(i, 3, 1);
//        }
//        for (int i = 1; i <= 4; i++) {
//            setCellValue(i, 4, 1);
//        }
//        for (int i = 3; i <= 4; i++) {
//            setCellValue(i, 5, 1);
//        }
//        for (int i = 2; i <= 3; i++) {
//            setCellValue(i, 6, 1);
//        }
//
//        for (int i = 2; i <= 6; i++) {
//            setCellValue(i, 7, 1);
//        }
//
//        setCellValue(7, 7, 3); // Locatia de sfarsit