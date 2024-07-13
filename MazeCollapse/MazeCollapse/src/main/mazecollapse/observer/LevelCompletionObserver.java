package main.mazecollapse.observer;

import main.mazecollapse.prototype.MazePrototype;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class LevelCompletionObserver implements Observer {
    private MazePrototype maze;
    private String difficultyLevel; // Adăugați acest câmp pentru a stoca nivelul de dificultate

    public LevelCompletionObserver(MazePrototype maze, String difficulty) {
        this.maze = maze;
        this.difficultyLevel = difficulty;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof MazePrototype updatedMaze) {
            if (updatedMaze.isFinished()) {
                if (updatedMaze.isLevelComplete()) {
                    JOptionPane.showMessageDialog(null,
                            "Felicitări! Ai terminat nivelul " + difficultyLevel +
                                    ". Pentru a juca un alt nivel de joc, apasa pe unul din butoanele de deasupra maze-ului.",
                            "Nivel Completat",
                            JOptionPane.INFORMATION_MESSAGE);
                    maze.printMaze();
                    // Alte acțiuni pentru finalizarea nivelului
                }
                else{
                    JOptionPane.showMessageDialog(null,
                            "Nu ai reusit sa parcurgi tot labirintul, astfel, nu ai respectat regulile jocului. Mai incearca!",
                            "Nivel Completat incorect.",
                            JOptionPane.INFORMATION_MESSAGE);
                    maze.printMaze();
                    // Alte acțiuni pentru finalizarea nivelului
                }
            }
        }
    }
}