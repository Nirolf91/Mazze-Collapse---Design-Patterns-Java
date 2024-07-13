package main.mazecollapse.prototype;

import java.util.Observable;

public class MazePrototype extends Observable implements Cloneable {
    public static final int PLAYER = 2;
    public static final int PATH = 1;
    public static final int WALL = 0;
    public static final int FINISH = 3;
    public static final int KEY = 99;
    public static final int LOCK = 100;
    public static final int VISITED_PATH = -1;
    private int width;
    private int height;
    private int[][] maze;

    private boolean lastMoveWasValid = true;


    public MazePrototype(int width, int height) {
        this.width = width;
        this.height = height;
        this.maze = new int[height][width]; // Inițializează toate celulele cu 0
    }

    public void setCellValue(int x, int y, int value) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            maze[y][x] = value;
        }
    }

    public int getCellValue(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return maze[y][x];
        }
        return -1; // Valoare invalidă pentru celule în afara matricei
    }

    @Override
    public MazePrototype clone() {
        try {
            MazePrototype clone = (MazePrototype) super.clone();
            clone.maze = new int[height][width];
            for (int i = 0; i < height; i++) {
                System.arraycopy(this.maze[i], 0, clone.maze[i], 0, width);
            }
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Clonarea MazePrototype nu este suportată", e);
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean movePlayerRight() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width - 1; x++) { // Verifică până la width - 1
                if (maze[y][x] == MazePrototype.PLAYER) {
                    if (maze[y][x + 1] == MazePrototype.PATH || maze[y][x + 1] == MazePrototype.FINISH || maze[y][x+1] == MazePrototype.KEY) {
                        if(maze[y][x+1] == MazePrototype.KEY){
                            for (int i = 0; i < height; i++) {
                                for (int j = 0; j < width - 1; j++) {
                                    if (maze[i][j] == LOCK)
                                        maze[i][j] = PATH;
                                }
                            }
                        }
                        maze[y][x] = MazePrototype.VISITED_PATH;
                        maze[y][x + 1] = MazePrototype.PLAYER;
                        lastMoveWasValid = true;
                        setChanged();
                        notifyObservers();
                        return true;
                    } else {
                        lastMoveWasValid = false;
                        setChanged();
                        notifyObservers("lost");
                        return false;
                    }
                }
            }
        }
        lastMoveWasValid = false; // Jucătorul nu a fost găsit sau nu se poate muta
        return false;
    }


    public boolean movePlayerLeft() {
        for (int y = 0; y < height; y++) {
            for (int x = 1; x < width; x++) { // Începe de la 1 pentru a evita IndexOutOfBoundsException
                if (maze[y][x] == MazePrototype.PLAYER) {
                    if (maze[y][x - 1] == MazePrototype.PATH || maze[y][x - 1] == MazePrototype.FINISH || maze[y][x-1] == MazePrototype.KEY) {
                        if(maze[y][x - 1] == MazePrototype.KEY){
                            for (int i = 0; i < height; i++) {
                                for (int j = 0; j < width - 1; j++) {
                                    if (maze[i][j] == LOCK)
                                        maze[i][j] = PATH;
                                }
                            }
                        }
                        maze[y][x] = MazePrototype.VISITED_PATH;
                        maze[y][x - 1] = MazePrototype.PLAYER;
                        lastMoveWasValid = true;
                        setChanged();
                        notifyObservers();
                        return true;
                    } else {
                        lastMoveWasValid = false;
                        setChanged();
                        notifyObservers("lost");
                        return false;
                    }
                }
            }
        }
        lastMoveWasValid = false; // Jucătorul nu a fost găsit sau nu se poate muta
        return false;
    }

    public boolean movePlayerUp() {
        for (int y = 1; y < height; y++) { // Începe de la 1 pentru a evita IndexOutOfBoundsException
            for (int x = 0; x < width; x++) {
                if (maze[y][x] == 2) { // Verifică dacă găsește jucătorul
                    if (maze[y - 1][x] == 1 || maze[y - 1][x] == 3 || maze[y - 1][x] == MazePrototype.KEY) { // Verifică dacă celula de sus este liberă sau final
                        if(maze[y - 1][x] == MazePrototype.KEY){
                            for (int i = 0; i < height; i++) {
                                for (int j = 0; j < width - 1; j++) {
                                    if (maze[i][j] == LOCK)
                                        maze[i][j] = PATH;
                                }
                            }
                        }
                        maze[y][x] = -1; // Marchează celula actuală ca vizitată
                        maze[y - 1][x] = 2; // Mută jucătorul în sus
                        lastMoveWasValid = true;
                        setChanged();
                        notifyObservers();
                        return true; // Mișcarea a fost efectuată cu succes
                    } else {
                        lastMoveWasValid = false;
                        setChanged();
                        notifyObservers("lost"); // Notifică observatorii despre eșec
                        return false; // Mișcarea nu a fost efectuată, jucătorul a pierdut
                    }
                }
            }
        }
        lastMoveWasValid = false;
        return false; // Nu s-a găsit jucătorul pe tablă, sau altă eroare
    }

    public boolean movePlayerDown() {
        for (int y = 0; y < height - 1; y++) { // Verifică până la height - 1
            for (int x = 0; x < width; x++) {
                if (maze[y][x] == MazePrototype.PLAYER) {
                    // Verifică dacă celula de dedesubt este liberă sau final
                    if (maze[y + 1][x] == MazePrototype.PATH || maze[y + 1][x] == MazePrototype.FINISH || maze[y + 1][x] == MazePrototype.KEY) {
                        if(maze[y + 1][x] == MazePrototype.KEY){
                            for (int i = 0; i < height; i++) {
                                for (int j = 0; j < width - 1; j++) {
                                    if (maze[i][j] == LOCK)
                                        maze[i][j] = PATH;
                                }
                            }
                        }
                        maze[y][x] = MazePrototype.VISITED_PATH; // Marchează celula actuală ca vizitată
                        maze[y + 1][x] = MazePrototype.PLAYER; // Mută jucătorul în jos
                        lastMoveWasValid = true;
                        setChanged();
                        notifyObservers();
                        return true; // Mișcarea a fost efectuată cu succes
                    } else {
                        lastMoveWasValid = false;
                        setChanged();
                        notifyObservers("lost"); // Notifică observatorii despre eșec
                        return false; // Mișcarea nu a fost efectuată, jucătorul a pierdut
                    }
                }
            }
        }
        lastMoveWasValid = false;
        return false; // Nu s-a găsit jucătorul pe tablă, sau altă eroare
    }

    public boolean isLevelComplete() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (maze[y][x] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isFinished() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (maze[y][x] == 3) {
                    return false;
                }
            }
        }
        return true;
    }


    public void printMaze() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print(maze[y][x] + " ");
            }
            System.out.println();
        }
        System.out.println(); // Linie goală pentru separarea în consolă
    }

    public boolean lastMoveWasValid() {
        return lastMoveWasValid;
    }
}
