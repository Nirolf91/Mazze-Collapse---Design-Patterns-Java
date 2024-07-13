package main.mazecollapse.decorators;

import main.mazecollapse.prototype.MazePrototype;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class KeyAndLockDecorator extends MazePrototype {
    private MazePrototype wrappedMaze;
    private Point keyPosition;
    private Point lockPosition;
    private boolean keyCollected = false;

    public KeyAndLockDecorator(MazePrototype wrappedMaze) {
        super(wrappedMaze.getWidth(), wrappedMaze.getHeight());
        this.wrappedMaze = wrappedMaze;
    }

    public void addKeyAndLock(int keyX, int keyY, int lockX, int lockY) {
        keyPosition = new Point(keyX, keyY);
        lockPosition = new Point(lockX, lockY);
        wrappedMaze.setCellValue(keyX, keyY, KEY); // Să presupunem că KEY este codul pentru cheie
        wrappedMaze.setCellValue(lockX, lockY, LOCK); // Să presupunem că LOCK este codul pentru lacăt
    }

    @Override
    public boolean movePlayerRight() {
        boolean moveSuccessful = wrappedMaze.movePlayerRight();
        if (moveSuccessful) {
            checkForKey();
        }
        return moveSuccessful;
    }

    @Override
    public boolean movePlayerLeft() {
        boolean moveSuccessful = wrappedMaze.movePlayerLeft();
        if (moveSuccessful) {
            checkForKey();
        }
        return moveSuccessful;
    }

    @Override
    public boolean movePlayerUp() {
        boolean moveSuccessful = wrappedMaze.movePlayerUp();
        if (moveSuccessful) {
            checkForKey();
        }
        return moveSuccessful;
    }

    @Override
    public boolean movePlayerDown() {
        boolean moveSuccessful = wrappedMaze.movePlayerDown();
        if (moveSuccessful) {
            checkForKey();
        }
        return moveSuccessful;
    }

    private void checkForKey() {
        Point playerPosition = findPlayer();
        if (keyPosition.equals(playerPosition) && !keyCollected) {
            keyCollected = true;
            unlockLock();
        }
    }

    private Point findPlayer() {
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                if (wrappedMaze.getCellValue(x, y) == 2) { // Presupunând că 2 reprezintă jucătorul
                    return new Point(x, y); // Returnează poziția jucătorului ca un obiect Point
                }
            }
        }
        return null; // Jucătorul nu a fost găsit
    }

    private void unlockLock() {
        if (keyCollected) {
            wrappedMaze.setCellValue(lockPosition.x, lockPosition.y, PATH); // Transformă lacătul într-o cale
        }
    }

    // Restul metodelor, inclusiv cele pentru acces la celule și alte funcționalități
}
