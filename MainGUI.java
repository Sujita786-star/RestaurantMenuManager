package restaurantmenumanager;

import javax.swing.*;
import java.awt.*;

public class MainGUI extends JFrame {

    private final MenuManager menuManager;
    private final EvaluationManager evaluationManager;
    private final FileManager fileManager;

    private JLabel statusLabel;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public MainGUI(
            MenuManager menuManager,
            EvaluationManager evaluationManager,
            FileManager fileManager) {

        this.menuManager = menuManager;
        this.evaluationManager = evaluationManager;
        this.fileManager = fileManager;

        createGUI();
    }

    // =====================================================
    // CREATE GUI
    // =====================================================

    private void createGUI() {

        setTitle("Restaurant Menu Manager");

        setSize(850, 650);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        JPanel mainPanel =
                new JPanel(new BorderLayout(15, 15));

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        20,
                        20,
                        20
                )
        );

        // =================================================
        // TITLE
        // =================================================

        JLabel title =
                new JLabel(
                        "RESTAURANT MENU MANAGER",
                        SwingConstants.CENTER
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        mainPanel.add(
                title,
                BorderLayout.NORTH
        );

        // =================================================
        // BUTTON PANEL
        // =================================================

        JPanel buttonPanel =
                new JPanel(
                        new GridLayout(
                                6,
                                2,
                                12,
                                12
                        )
                );

        JButton addButton =
                new JButton("Add Menu Item");

        JButton viewButton =
                new JButton("View Menu");

        JButton searchButton =
                new JButton("Search Menu Item");

        JButton updateButton =
                new JButton("Update Menu Item");

        JButton deleteButton =
                new JButton("Delete Menu Item");

        JButton sortButton =
                new JButton("Sort Menu Items");

        JButton evaluationButton =
                new JButton("Evaluate Menu Item");

        JButton feedbackButton =
                new JButton("Feedback");

        JButton rewardButton =
                new JButton("Reward / Penalty");

        JButton saveButton =
                new JButton("Save Data");

        JButton exitButton =
                new JButton("Exit");

        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);

        buttonPanel.add(searchButton);
        buttonPanel.add(updateButton);

        buttonPanel.add(deleteButton);
        buttonPanel.add(sortButton);

        buttonPanel.add(evaluationButton);
        buttonPanel.add(feedbackButton);

        buttonPanel.add(rewardButton);
        buttonPanel.add(saveButton);

        buttonPanel.add(exitButton);
        buttonPanel.add(new JLabel(""));

        mainPanel.add(
                buttonPanel,
                BorderLayout.CENTER
        );

        // =================================================
        // STATUS
        // =================================================

        statusLabel =
                new JLabel(
                        "Ready",
                        SwingConstants.CENTER
                );

        statusLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        mainPanel.add(
                statusLabel,
                BorderLayout.SOUTH
        );

        // =================================================
        // BUTTON ACTIONS
        // =================================================

        addButton.addActionListener(
                e -> addMenuItem()
        );

        viewButton.addActionListener(
                e -> viewMenu()
        );

        searchButton.addActionListener(
                e -> searchMenuItem()
        );

        updateButton.addActionListener(
                e -> updateMenuItem()
        );

        deleteButton.addActionListener(
                e -> deleteMenuItem()
        );

        sortButton.addActionListener(
                e -> sortMenuItems()
        );

        evaluationButton.addActionListener(
                e -> evaluateMenuItem()
        );

        feedbackButton.addActionListener(
                e -> showFeedback()
        );

        rewardButton.addActionListener(
                e -> showRewardPenalty()
        );

        saveButton.addActionListener(
                e -> saveData()
        );

        exitButton.addActionListener(
                e -> exitApplication()
        );

        add(mainPanel);
    }

    // =====================================================
    // ADD MENU ITEM
    // =====================================================

    private void addMenuItem() {

        try {

            String[] types = {
                    "Food",
                    "Drink"
            };

            String type =
                    (String) JOptionPane.showInputDialog(
                            this,
                            "Select menu item type:",
                            "Add Menu Item",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            types,
                            types[0]
                    );

            if (type == null) {
                return;
            }

            int id =
                    readPositiveInt(
                            "Enter Menu Item ID:"
                    );

            if (menuManager.searchItem(id) != null) {

                showError(
                        "Menu item ID already exists."
                );

                return;
            }

            String name =
                    readNonEmptyString(
                            "Enter item name:"
                    );

            double price =
                    readNonNegativeDouble(
                            "Enter price:"
                    );

            String category =
                    readNonEmptyString(
                            "Enter category:"
                    );

            boolean available =
                    readBoolean(
                            "Is the item available?"
                    );

            if (type.equals("Food")) {

                String dietaryType =
                        readNonEmptyString(
                                "Enter dietary type:"
                        );

                FoodItem food =
                        new FoodItem(
                                id,
                                name,
                                price,
                                category,
                                available,
                                dietaryType
                        );

                menuManager.addItem(food);

            } else {

                String drinkSize =
                        readNonEmptyString(
                                "Enter drink size:"
                        );

                DrinkItem drink =
                        new DrinkItem(
                                id,
                                name,
                                price,
                                category,
                                available,
                                drinkSize
                        );

                menuManager.addItem(drink);
            }

            showMessage(
                    "Menu item added successfully."
            );

            statusLabel.setText(
                    "Menu item added successfully."
            );

        } catch (Exception e) {

            showError(
                    "Unable to add menu item: "
                            + e.getMessage()
            );
        }
    }

    // =====================================================
    // VIEW MENU
    // =====================================================

    private void viewMenu() {

        if (menuManager.isEmpty()) {

            showMessage(
                    "No menu items available."
            );

            return;
        }

        StringBuilder output =
                new StringBuilder();

        output.append(
                "========== RESTAURANT MENU ==========\n\n"
        );

        for (MenuItem item :
                menuManager.getMenuItems()) {

            output.append(
                    item.getDetails()
            );

            output.append("\n");

            output.append(
                    "-------------------------------------\n"
            );
        }

        showLargeText(
                output.toString(),
                "Restaurant Menu"
        );

        statusLabel.setText(
                "Menu displayed successfully."
        );
    }

    // =====================================================
    // SEARCH - BINARY SEARCH
    // =====================================================

    private void searchMenuItem() {

        try {

            int id =
                    readPositiveInt(
                            "Enter Menu Item ID:"
                    );

            SortingManager.mergeSortById(
                    menuManager.getMenuItems()
            );

            MenuItem item =
                    SearchingManager.binarySearchById(
                            menuManager.getMenuItems(),
                            id
                    );

            if (item == null) {

                showMessage(
                        "Menu item with ID "
                                + id
                                + " was not found."
                );

                statusLabel.setText(
                        "Menu item not found."
                );

            } else {

                showLargeText(
                        item.getDetails(),
                        "Search Result - Binary Search"
                );

                statusLabel.setText(
                        "Menu item found using Binary Search."
                );
            }

        } catch (Exception e) {

            showError(
                    "Search error: "
                            + e.getMessage()
            );
        }
    }

    // =====================================================
    // UPDATE MENU ITEM
    // =====================================================

    private void updateMenuItem() {

        try {

            int id =
                    readPositiveInt(
                            "Enter Menu Item ID:"
                    );

            MenuItem item =
                    menuManager.searchItem(id);

            if (item == null) {

                showError(
                        "Menu item not found."
                );

                return;
            }

            String name =
                    readNonEmptyString(
                            "Enter new name:"
                    );

            double price =
                    readNonNegativeDouble(
                            "Enter new price:"
                    );

            String category =
                    readNonEmptyString(
                            "Enter new category:"
                    );

            boolean available =
                    readBoolean(
                            "Is the item available?"
                    );

            boolean updated =
                    menuManager.updateItem(
                            id,
                            name,
                            price,
                            category,
                            available
                    );

            if (updated) {

                showMessage(
                        "Menu item updated successfully."
                );

                statusLabel.setText(
                        "Menu item updated successfully."
                );
            }

        } catch (Exception e) {

            showError(
                    "Update error: "
                            + e.getMessage()
            );
        }
    }

    // =====================================================
    // DELETE MENU ITEM
    // =====================================================

    private void deleteMenuItem() {

        try {

            int id =
                    readPositiveInt(
                            "Enter Menu Item ID:"
                    );

            MenuItem item =
                    menuManager.searchItem(id);

            if (item == null) {

                showError(
                        "Menu item not found."
                );

                return;
            }

            int confirmation =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Delete menu item:\n"
                                    + item.getName()
                                    + "?",
                            "Confirm Delete",
                            JOptionPane.YES_NO_OPTION
                    );

            if (confirmation ==
                    JOptionPane.YES_OPTION) {

                boolean deleted =
                        menuManager.deleteItem(id);

                if (deleted) {

                    showMessage(
                            "Menu item deleted successfully."
                    );

                    statusLabel.setText(
                            "Menu item deleted successfully."
                    );
                }

            } else {

                statusLabel.setText(
                        "Delete cancelled."
                );
            }

        } catch (Exception e) {

            showError(
                    "Delete error: "
                            + e.getMessage()
            );
        }
    }

    // =====================================================
    // SORT - MERGE SORT
    // =====================================================

    private void sortMenuItems() {

        if (menuManager.isEmpty()) {

            showMessage(
                    "No menu items available to sort."
            );

            return;
        }

        SortingManager.mergeSortByPrice(
                menuManager.getMenuItems()
        );

        StringBuilder output =
                new StringBuilder();

        output.append(
                "========== MENU SORTED BY PRICE ==========\n\n"
        );

        for (MenuItem item :
                menuManager.getMenuItems()) {

            output.append(
                    item.getDetails()
            );

            output.append("\n");

            output.append(
                    "-------------------------------------\n"
            );
        }

        showLargeText(
                output.toString(),
                "Sorted Menu - Merge Sort"
        );

        statusLabel.setText(
                "Menu sorted using Merge Sort."
        );
    }

    // =====================================================
    // EVALUATE MENU ITEM
    // =====================================================

    private void evaluateMenuItem() {

        try {

            int id =
                    readPositiveInt(
                            "Enter Menu Item ID:"
                    );

            MenuItem item =
                    menuManager.searchItem(id);

            if (item == null) {

                showError(
                        "Menu item not found."
                );

                return;
            }

            int rating;

            while (true) {

                String input =
                        JOptionPane.showInputDialog(
                                this,
                                "Enter rating (1-5):"
                        );

                if (input == null) {
                    return;
                }

                try {

                    rating =
                            Integer.parseInt(
                                    input.trim()
                            );

                    if (rating >= 1 &&
                            rating <= 5) {

                        break;
                    }

                    showError(
                            "Rating must be between 1 and 5."
                    );

                } catch (
                        NumberFormatException e) {

                    showError(
                            "Please enter a whole number."
                    );
                }
            }

            String period =
                    readNonEmptyString(
                            "Enter evaluation period:"
                    );

            boolean added =
                    evaluationManager.addEvaluation(
                            item,
                            rating,
                            period
                    );

            if (added) {

                showMessage(
                        "Evaluation recorded successfully."
                );

                statusLabel.setText(
                        "Evaluation recorded."
                );
            }

        } catch (Exception e) {

            showError(
                    "Evaluation error: "
                            + e.getMessage()
            );
        }
    }

    // =====================================================
    // FEEDBACK - GUI
    // =====================================================

    private void showFeedback() {

        try {

            int id =
                    readPositiveInt(
                            "Enter Menu Item ID:"
                    );

            String result =
                    evaluationManager.getFeedbackText(id);

            showLargeText(
                    result,
                    "Feedback"
            );

            statusLabel.setText(
                    "Feedback displayed."
            );

        } catch (Exception e) {

            showError(
                    "Feedback error: "
                            + e.getMessage()
            );
        }
    }

    // =====================================================
    // REWARD / PENALTY - GUI
    // =====================================================

    private void showRewardPenalty() {

        try {

            int id =
                    readPositiveInt(
                            "Enter Menu Item ID:"
                    );

            String rewardPenalty =
                    evaluationManager
                            .getRewardPenaltyText(id);

            String performance =
                    evaluationManager
                            .getPerformanceText(id);

            showLargeText(
                    rewardPenalty
                            + "\n\n"
                            + performance,
                    "Reward / Penalty & Performance"
            );

            statusLabel.setText(
                    "Reward/Penalty information displayed."
            );

        } catch (Exception e) {

            showError(
                    "Reward/Penalty error: "
                            + e.getMessage()
            );
        }
    }

    // =====================================================
    // SAVE DATA
    // =====================================================

    private void saveData() {

        try {

            fileManager.saveMenu(
                    menuManager.getMenuItems()
            );

            fileManager.saveEvaluations(
                    evaluationManager.getEvaluations()
            );

            showMessage(
                    "Menu and evaluation data saved successfully."
            );

            statusLabel.setText(
                    "Data saved successfully."
            );

        } catch (Exception e) {

            showError(
                    "Unable to save data: "
                            + e.getMessage()
            );
        }
    }

    // =====================================================
    // EXIT
    // =====================================================

    private void exitApplication() {

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "Do you want to save before exiting?",
                        "Exit Application",
                        JOptionPane.YES_NO_CANCEL_OPTION
                );

        if (result ==
                JOptionPane.CANCEL_OPTION) {

            return;
        }

        if (result ==
                JOptionPane.YES_OPTION) {

            saveData();
        }

        dispose();

        System.exit(0);
    }

    // =====================================================
    // READ POSITIVE INTEGER
    // =====================================================

    private int readPositiveInt(
            String message) {

        while (true) {

            String input =
                    JOptionPane.showInputDialog(
                            this,
                            message
                    );

            if (input == null) {

                throw new RuntimeException(
                        "Operation cancelled."
                );
            }

            try {

                int value =
                        Integer.parseInt(
                                input.trim()
                        );

                if (value > 0) {
                    return value;
                }

                showError(
                        "Value must be greater than zero."
                );

            } catch (
                    NumberFormatException e) {

                showError(
                        "Please enter a valid whole number."
                );
            }
        }
    }

    // =====================================================
    // READ NON-NEGATIVE DOUBLE
    // =====================================================

    private double readNonNegativeDouble(
            String message) {

        while (true) {

            String input =
                    JOptionPane.showInputDialog(
                            this,
                            message
                    );

            if (input == null) {

                throw new RuntimeException(
                        "Operation cancelled."
                );
            }

            try {

                double value =
                        Double.parseDouble(
                                input.trim()
                        );

                if (value >= 0) {
                    return value;
                }

                showError(
                        "Price cannot be negative."
                );

            } catch (
                    NumberFormatException e) {

                showError(
                        "Please enter a valid number."
                );
            }
        }
    }

    // =====================================================
    // READ NON-EMPTY STRING
    // =====================================================

    private String readNonEmptyString(
            String message) {

        while (true) {

            String input =
                    JOptionPane.showInputDialog(
                            this,
                            message
                    );

            if (input == null) {

                throw new RuntimeException(
                        "Operation cancelled."
                );
            }

            input =
                    input.trim();

            if (!input.isEmpty()) {
                return input;
            }

            showError(
                    "Input cannot be empty."
            );
        }
    }

    // =====================================================
    // READ BOOLEAN
    // =====================================================

    private boolean readBoolean(
            String message) {

        String[] options = {
                "Yes",
                "No"
        };

        int result =
                JOptionPane.showOptionDialog(
                        this,
                        message,
                        "Select Option",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]
                );

        if (result == 0) {
            return true;
        }

        if (result == 1) {
            return false;
        }

        throw new RuntimeException(
                "Operation cancelled."
        );
    }

    // =====================================================
    // SHOW MESSAGE
    // =====================================================

    private void showMessage(
            String message) {

        JOptionPane.showMessageDialog(
                this,
                message,
                "Restaurant Menu Manager",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // =====================================================
    // SHOW ERROR
    // =====================================================

    private void showError(
            String message) {

        JOptionPane.showMessageDialog(
                this,
                message,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

    // =====================================================
    // SHOW LARGE TEXT
    // =====================================================

    private void showLargeText(
            String text,
            String title) {

        JTextArea textArea =
                new JTextArea(text);

        textArea.setEditable(false);

        textArea.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        14
                )
        );

        textArea.setLineWrap(false);

        JScrollPane scrollPane =
                new JScrollPane(textArea);

        scrollPane.setPreferredSize(
                new Dimension(
                        700,
                        450
                )
        );

        JOptionPane.showMessageDialog(
                this,
                scrollPane,
                title,
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
