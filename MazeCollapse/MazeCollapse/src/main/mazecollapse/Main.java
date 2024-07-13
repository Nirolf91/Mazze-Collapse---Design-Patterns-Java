package main.mazecollapse;

import main.mazecollapse.singleton.GameManager;
import main.mazecollapse.view.MazeGameSwingView;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Inițializează GameManager și alte componente necesare ale jocului aici

        // Asigură-te că interfața este creată pe Event Dispatch Thread (EDT),
        // care este firul de execuție unde toate componentele Swing trebuie să fie manipulate.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Presupunem că GameManager este deja inițializat și pregătit pentru a începe jocul
                GameManager gameManager = GameManager.getInstance();

                // Creează și afișează interfața Swing
                MazeGameSwingView gameView = new MazeGameSwingView(gameManager);
                gameView.setVisible(true);
            }
        });
    }
}
