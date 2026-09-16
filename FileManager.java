package restaurantmenumanager;

import java.io.*;
import java.util.ArrayList;

public class FileManager {

    private static final String MENU_FILE = "menu.txt";
    private static final String EVALUATION_FILE = "evaluation.txt";

    // =====================================================
    // SAVE MENU
    // =====================================================

    public void saveMenu(ArrayList<MenuItem> menuItems) {

        try (PrintWriter writer =
                     new PrintWriter(
                             new FileWriter(MENU_FILE))) {

            for (MenuItem item : menuItems) {

                if (item instanceof FoodItem) {

                    FoodItem food = (FoodItem) item;

                    writer.println(
                            "FOOD|"
                                    + food.getId() + "|"
                                    + clean(food.getName()) + "|"
                                    + food.getPrice() + "|"
                                    + clean(food.getCategory()) + "|"
                                    + food.isAvailable() + "|"
                                    + clean(food.getDietaryType())
                    );

                } else if (item instanceof DrinkItem) {

                    DrinkItem drink = (DrinkItem) item;

                    writer.println(
                            "DRINK|"
                                    + drink.getId() + "|"
                                    + clean(drink.getName()) + "|"
                                    + drink.getPrice() + "|"
                                    + clean(drink.getCategory()) + "|"
                                    + drink.isAvailable() + "|"
                                    + clean(drink.getDrinkSize())
                    );
                }
            }

            System.out.println(
                    "Menu data saved successfully."
            );

        } catch (IOException e) {

            System.out.println(
                    "Error saving menu data: "
                            + e.getMessage()
            );
        }
    }

    // =====================================================
    // LOAD MENU
    // =====================================================

    public ArrayList<MenuItem> loadMenu() {

        ArrayList<MenuItem> menuItems =
                new ArrayList<>();

        File file = new File(MENU_FILE);

        if (!file.exists()) {

            System.out.println(
                    "No saved menu file found. Starting with empty menu."
            );

            return menuItems;
        }

        try (BufferedReader reader =
                     new BufferedReader(
                             new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split("\\|", -1);

                if (data.length != 7) {

                    System.out.println(
                            "Skipping invalid menu record."
                    );

                    continue;
                }

                try {

                    String type = data[0];

                    int id = Integer.parseInt(data[1]);

                    String name = data[2];

                    double price =
                            Double.parseDouble(data[3]);

                    String category = data[4];

                    boolean available =
                            Boolean.parseBoolean(data[5]);

                    String specificType = data[6];

                    if (price < 0) {
                        continue;
                    }

                    if (type.equalsIgnoreCase("FOOD")) {

                        menuItems.add(
                                new FoodItem(
                                        id,
                                        name,
                                        price,
                                        category,
                                        available,
                                        specificType
                                )
                        );

                    } else if (
                            type.equalsIgnoreCase("DRINK")) {

                        menuItems.add(
                                new DrinkItem(
                                        id,
                                        name,
                                        price,
                                        category,
                                        available,
                                        specificType
                                )
                        );
                    }

                } catch (NumberFormatException e) {

                    System.out.println(
                            "Skipping invalid menu record: "
                                    + line
                    );
                }
            }

            System.out.println(
                    "Menu data loaded successfully."
            );

        } catch (FileNotFoundException e) {

            System.out.println(
                    "Menu file not found."
            );

        } catch (IOException e) {

            System.out.println(
                    "Error reading menu file: "
                            + e.getMessage()
            );
        }

        return menuItems;
    }

    // =====================================================
    // SAVE EVALUATIONS
    // =====================================================

    public void saveEvaluations(
            ArrayList<Evaluation> evaluations) {

        try (PrintWriter writer =
                     new PrintWriter(
                             new FileWriter(EVALUATION_FILE))) {

            for (Evaluation evaluation : evaluations) {

                writer.println(
                        evaluation.getMenuItem().getId()
                                + "|"
                                + evaluation.getRating()
                                + "|"
                                + clean(
                                evaluation.getEvaluationPeriod()
                        )
                );
            }

            System.out.println(
                    "Evaluation data saved successfully."
            );

        } catch (IOException e) {

            System.out.println(
                    "Error saving evaluation data: "
                            + e.getMessage()
            );
        }
    }

    // =====================================================
    // LOAD EVALUATIONS
    // =====================================================

    public void loadEvaluations(
            EvaluationManager evaluationManager,
            MenuManager menuManager) {

        File file = new File(EVALUATION_FILE);

        if (!file.exists()) {

            System.out.println(
                    "No saved evaluation file found."
            );

            return;
        }

        try (BufferedReader reader =
                     new BufferedReader(
                             new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split("\\|", -1);

                if (data.length != 3) {
                    continue;
                }

                try {

                    int menuItemId =
                            Integer.parseInt(data[0]);

                    int rating =
                            Integer.parseInt(data[1]);

                    String period = data[2];

                    MenuItem item =
                            menuManager.searchItem(menuItemId);

                    if (item != null) {

                        evaluationManager.addEvaluation(
                                item,
                                rating,
                                period
                        );
                    }

                } catch (NumberFormatException e) {

                    System.out.println(
                            "Skipping invalid evaluation record."
                    );
                }
            }

            System.out.println(
                    "Evaluation data loaded successfully."
            );

        } catch (FileNotFoundException e) {

            System.out.println(
                    "Evaluation file not found."
            );

        } catch (IOException e) {

            System.out.println(
                    "Error reading evaluation file: "
                            + e.getMessage()
            );
        }
    }

    // =====================================================
    // CLEAN FILE VALUES
    // =====================================================

    private String clean(String value) {

        if (value == null) {
            return "";
        }

        // Prevent | from breaking the file format
        return value.replace("|", "/");
    }
}
