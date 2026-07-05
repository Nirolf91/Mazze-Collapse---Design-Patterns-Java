package mazecollapse.view;

import mazecollapse.model.CellType;
import mazecollapse.model.Difficulty;
import mazecollapse.model.Direction;
import mazecollapse.model.GameEvent;
import mazecollapse.model.Maze;
import mazecollapse.model.Position;
import mazecollapse.observer.GameObserver;
import mazecollapse.singleton.GameManager;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

public final class MazeGameSwingView extends JFrame implements GameObserver {
    private final GameManager gameManager;
    private final JPanel mazePanel = new JPanel();
    private final JLabel statusLabel = new JLabel("Choose a difficulty to start.");

    public MazeGameSwingView(GameManager gameManager) {
        this.gameManager = gameManager;
        this.gameManager.addObserver(this);
        initializeUi();
    }

    @Override
    public void onGameEvent(GameEvent event) {
        renderMaze(event.maze());
        statusLabel.setText(event.difficulty().label() + " - " + event.moveResult().message());

        if (event.type() == GameEvent.Type.GAME_OVER) {
            JOptionPane.showMessageDialog(this, "Game over. The level will restart.", "Maze Collapse", JOptionPane.ERROR_MESSAGE);
            gameManager.resetGame();
        }

        if (event.type() == GameEvent.Type.LEVEL_FINISHED) {
            String message = event.moveResult().levelComplete()
                    ? "Congratulations. You visited all required cells before reaching the finish."
                    : "You reached the finish too early. Visit every path before finishing.";
            JOptionPane.showMessageDialog(this, message, "Level finished", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void initializeUi() {
        setTitle("Maze Collapse - Design Patterns Java");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(8, 8));

        JPanel difficultyPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 8));
        for (Difficulty difficulty : Difficulty.values()) {
            JButton button = new JButton(difficulty.label());
            button.addActionListener(event -> gameManager.startGame(difficulty));
            difficultyPanel.add(button);
        }

        statusLabel.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        mazePanel.setBackground(new Color(238, 238, 238));

        add(difficultyPanel, BorderLayout.NORTH);
        add(mazePanel, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);

        registerMovementKeys();
        setSize(760, 640);
        setLocationRelativeTo(null);
    }

    private void registerMovementKeys() {
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getRootPane().getActionMap();

        registerDirection(inputMap, actionMap, KeyEvent.VK_UP, Direction.UP);
        registerDirection(inputMap, actionMap, KeyEvent.VK_DOWN, Direction.DOWN);
        registerDirection(inputMap, actionMap, KeyEvent.VK_LEFT, Direction.LEFT);
        registerDirection(inputMap, actionMap, KeyEvent.VK_RIGHT, Direction.RIGHT);
    }

    private void registerDirection(InputMap inputMap, ActionMap actionMap, int keyCode, Direction direction) {
        String actionName = "move-" + direction.name();
        inputMap.put(KeyStroke.getKeyStroke(keyCode, 0), actionName);
        actionMap.put(actionName, new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent event) {
                gameManager.movePlayer(direction);
            }
        });
    }

    private void renderMaze(Maze maze) {
        mazePanel.removeAll();
        mazePanel.setLayout(new GridLayout(maze.height(), maze.width(), 2, 2));

        for (int y = 0; y < maze.height(); y++) {
            for (int x = 0; x < maze.width(); x++) {
                mazePanel.add(cellLabel(maze.cellAt(new Position(x, y))));
            }
        }

        mazePanel.revalidate();
        mazePanel.repaint();
    }

    private JLabel cellLabel(CellType cellType) {
        JLabel label = new JLabel(symbolFor(cellType), JLabel.CENTER);
        label.setOpaque(true);
        label.setBackground(colorFor(cellType));
        label.setForeground(Color.WHITE);
        label.setFont(label.getFont().deriveFont(Font.BOLD, 18f));
        label.setBorder(BorderFactory.createLineBorder(new Color(210, 210, 210)));
        return label;
    }

    private String symbolFor(CellType cellType) {
        return switch (cellType) {
            case PLAYER -> "P";
            case FINISH -> "F";
            case KEY -> "K";
            case LOCK -> "L";
            default -> "";
        };
    }

    private Color colorFor(CellType cellType) {
        return switch (cellType) {
            case WALL -> new Color(35, 39, 47);
            case PATH -> new Color(248, 249, 250);
            case VISITED -> new Color(172, 187, 198);
            case PLAYER -> new Color(47, 111, 237);
            case FINISH -> new Color(38, 166, 91);
            case KEY -> new Color(245, 183, 0);
            case LOCK -> new Color(204, 66, 66);
        };
    }
}
