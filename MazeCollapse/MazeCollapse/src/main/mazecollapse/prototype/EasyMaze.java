package main.mazecollapse.prototype;

public class EasyMaze extends MazePrototype {
    public EasyMaze(MazePrototype prototype) {
        super(prototype.getWidth(), prototype.getHeight());

        // Clonăm matricea prototipului care este inițial complet plină cu 0
        for (int y = 0; y < prototype.getHeight(); y++) {
            for (int x = 0; x < prototype.getWidth(); x++) {
                this.setCellValue(x, y, prototype.getCellValue(x, y));
            }
        }

        // Personalizăm clona pentru a defini un labirint ușor
        customizeMaze();
    }

    private void customizeMaze() {
        setCellValue(1, 0, 2); // Locatia de start
        setCellValue(2, 0, 1); // x=coloane,y=randul
        setCellValue(3, 0, 1); // Locatia de start
        setCellValue(2, 1, 1);
        setCellValue(3, 1, 1);
        setCellValue(2, 2, 1);
        setCellValue(1, 2, 1);
        setCellValue(1, 3, 1);
        setCellValue(2, 3, 1);
        setCellValue(2, 4, 1); // Locatia de start
        setCellValue(3, 4, 3); // Locatia de sfarsit
    }




}
