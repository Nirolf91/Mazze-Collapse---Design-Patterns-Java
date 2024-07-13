package main.mazecollapse.view;

import main.mazecollapse.prototype.MazePrototype;
import main.mazecollapse.singleton.GameManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

public class MazeGameSwingView extends JFrame implements Observer {

    private GameManager gameManager; // Managerul de joc
    private JPanel mazeDisplayArea; // Zona de afișare a labirintului

    public MazeGameSwingView(GameManager gameManager) {
        this.gameManager = gameManager;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Maze Collapse Game");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panou pentru butoanele de dificultate
        JPanel difficultyPanel = new JPanel(new FlowLayout());
        addDifficultyButtons(difficultyPanel);

        // Zona de afișare a labirintului
        mazeDisplayArea = new JPanel();
        mazeDisplayArea.setBackground(Color.LIGHT_GRAY);
        add(mazeDisplayArea, BorderLayout.CENTER);

        // Adaugă panoul de dificultate la fereastra principală
        add(difficultyPanel, BorderLayout.NORTH);

        // Afișează fereastra
        pack();
        setLocationRelativeTo(null); // Centrarea ferestrei pe ecran
        setVisible(true);
        addKeyBindings();
    }

    private void addKeyBindings() {
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getRootPane().getActionMap();

        inputMap
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "moveUp");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "moveDown");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "moveLeft");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "moveRight");

        actionMap.put("moveUp", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean moveSuccessful = gameManager.movePlayerUp();
                if (moveSuccessful) {
                    updateMazeDisplay();
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Ai pierdut!",
                            "Game Over",
                            JOptionPane.ERROR_MESSAGE);
                    gameManager.resetGame();
                    // Aici poți adăuga orice altă logică necesară, cum ar fi resetarea jocului
                }
            }
        });
        actionMap.put("moveDown", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean moveSuccessful = gameManager.movePlayerDown();
                if (moveSuccessful) {
                    updateMazeDisplay();
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Ai pierdut!",
                            "Game Over",
                            JOptionPane.ERROR_MESSAGE);
                    gameManager.resetGame();

                    // Aici poți adăuga orice altă logică necesară, cum ar fi resetarea jocului
                }
            }
        });
        actionMap.put("moveLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean moveSuccessful = gameManager.movePlayerLeft();
                if (moveSuccessful) {
                    updateMazeDisplay();
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Ai pierdut!",
                            "Game Over",
                            JOptionPane.ERROR_MESSAGE);
                    gameManager.resetGame();

                    // Aici poți adăuga orice altă logică necesară, cum ar fi resetarea jocului
                }
            }
        });
        actionMap.put("moveRight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean moveSuccessful = gameManager.movePlayerRight();
                if (moveSuccessful) {
                    updateMazeDisplay();
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Ai pierdut!",
                            "Game Over",
                            JOptionPane.ERROR_MESSAGE);
                    gameManager.resetGame();

                    // Aici poți adăuga orice altă logică necesară, cum ar fi resetarea jocului
                }
            }
        });
    }


    private void addDifficultyButtons(JPanel panel) {
        // Buton pentru dificultatea "Easy"
        JButton easyButton = new JButton("Easy");
        easyButton.addActionListener(this::startEasyGame);
        panel.add(easyButton);

        // Buton pentru dificultatea "Medium"
        JButton mediumButton = new JButton("Medium");
        mediumButton.addActionListener(this::startMediumGame);
        panel.add(mediumButton);

        // Buton pentru dificultatea "Hard"
        JButton hardButton = new JButton("Hard");
        hardButton.addActionListener(this::startHardGame);
        panel.add(hardButton);

        // Adaugați alte elemente UI dacă este necesar
    }

    private void startEasyGame(ActionEvent e) {
        startGame("Easy");
    }

    private void startMediumGame(ActionEvent e) {
        startGame("Medium");
    }

    private void startHardGame(ActionEvent e) {
        startGame("Hard");
    }

    private void startGame(String difficulty) {
        gameManager.addObserver(this); // Înregistrează view-ul ca observator
        gameManager.startGame(difficulty); // Începe jocul cu dificultatea selectată
    }

    private void updateMazeDisplay() {
        mazeDisplayArea.removeAll(); // Îndepărtează toate componentele anterioare
        mazeDisplayArea.setLayout(new GridLayout(gameManager.getCurrentMaze().getHeight(), gameManager.getCurrentMaze().getWidth())); // Setează layout-ul

        MazePrototype maze = gameManager.getCurrentMaze();
        for (int y = 0; y < maze.getHeight(); y++) {
            for (int x = 0; x < maze.getWidth(); x++) {
                JLabel label = new JLabel();
                label.setOpaque(true);
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // Adaugă bordură la label

// Setează culori sau text în funcție de ce reprezintă celula
                switch (maze.getCellValue(x, y)) {
                    case MazePrototype.WALL -> label.setBackground(Color.BLACK);
                    case MazePrototype.PATH -> label.setBackground(Color.WHITE);
                    case MazePrototype.PLAYER -> {
                        label.setBackground(Color.BLUE);
                        label.setText("P");
                    }
                    case MazePrototype.FINISH -> {
                        label.setBackground(Color.GREEN);
                        label.setText("F");
                    }
                    case MazePrototype.KEY -> {
                        label.setBackground(Color.YELLOW);
                        label.setText("K"); // Reprezintă cheia
                    }
                    case MazePrototype.LOCK -> {
                        label.setBackground(Color.RED);
                        label.setText("L"); // Reprezintă lacătul
                    }
// Adăugați cazuri pentru cheie, lacăt și alte elemente dacă este necesar
                    default -> label.setBackground(Color.LIGHT_GRAY);
                }
                mazeDisplayArea.add(label);
            }
        }
        mazeDisplayArea.revalidate(); // Înștiințează layout manager-ul că s-a schimbat structura
        mazeDisplayArea.repaint(); // Redesenează panel-ul
    }


        // Metode suplimentare pentru manipularea interfeței utilizatorului pot fi adăugate aici

    public static void main(String[] args) {
        // Asigurați-vă că interfața este creată și actualizată pe Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            // Presupunem că GameManager poate fi inițializat aici sau înainte de această linie
            GameManager gameManager = GameManager.getInstance();
            new MazeGameSwingView(gameManager);
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof GameManager) {
            // Labirintul a fost actualizat, deci actualizăm UI-ul
            updateMazeDisplay();
        }
    }

}
