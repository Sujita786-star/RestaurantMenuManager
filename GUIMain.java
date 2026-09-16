package restaurantmenumanager;

public class GUIMain {

    public static void main(String[] args) {

        MenuManager menuManager =
                new MenuManager();

        EvaluationManager evaluationManager =
                new EvaluationManager();

        FileManager fileManager =
                new FileManager();

        // Load existing menu data
        for (MenuItem item : fileManager.loadMenu()) {
            menuManager.addItem(item);
        }

        // Start GUI
        javax.swing.SwingUtilities.invokeLater(() -> {

            MainGUI gui =
                    new MainGUI(
                            menuManager,
                            evaluationManager,
                            fileManager
                    );

            gui.setVisible(true);
        });
    }
}
