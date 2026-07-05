package mazecollapse;

import mazecollapse.singleton.GameManager;
import mazecollapse.view.MazeGameSwingView;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MazeGameSwingView view = new MazeGameSwingView(GameManager.getInstance());
            view.setVisible(true);
        });
    }
}
