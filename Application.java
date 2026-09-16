package restaurantmenumanager;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        while (running) {

            System.out.println();
            System.out.println("========================================");
            System.out.println("       RESTAURANT MENU MANAGER");
            System.out.println("========================================");
            System.out.println("1. Text-Based Interface (TBI)");
            System.out.println("2. Graphical User Interface (GUI)");
            System.out.println("3. Exit");
            System.out.println("========================================");

            System.out.print("Choose interface: ");

            String input = scanner.nextLine().trim();

            switch (input) {

                // =========================================
                // TEXT-BASED INTERFACE
                // =========================================

                case "1":

                    System.out.println();
                    System.out.println(
                            "Starting Text-Based Interface..."
                    );

                    Main.main(new String[]{});

                    break;

                // =========================================
                // GRAPHICAL USER INTERFACE
                // =========================================

                case "2":

                    System.out.println();
                    System.out.println(
                            "Starting Graphical User Interface..."
                    );

                    MenuManager menuManager =
                            new MenuManager();

                    EvaluationManager evaluationManager =
                            new EvaluationManager();

                    FileManager fileManager =
                            new FileManager();

                    // Load existing menu data
                    for (MenuItem item :
                            fileManager.loadMenu()) {

                        menuManager.addItem(item);
                    }

                    // Load existing evaluation data
                    fileManager.loadEvaluations(
                            evaluationManager,
                            menuManager
                    );

                    javax.swing.SwingUtilities.invokeLater(
                            () -> {

                                MainGUI gui =
                                        new MainGUI(
                                                menuManager,
                                                evaluationManager,
                                                fileManager
                                        );

                                gui.setVisible(true);
                            }
                    );

                    running = false;

                    break;

                // =========================================
                // EXIT
                // =========================================

                case "3":

                    System.out.println(
                            "Thank you for using Restaurant Menu Manager."
                    );

                    running = false;

                    break;

                // =========================================
                // INVALID CHOICE
                // =========================================

                default:

                    System.out.println(
                            "Invalid choice. "
                                    + "Please choose 1, 2 or 3."
                    );
            }
        }

        scanner.close();
    }
}
