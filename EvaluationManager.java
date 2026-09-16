package restaurantmenumanager;

import java.util.ArrayList;

public class EvaluationManager {

    private final ArrayList<Evaluation> evaluations;

    // Constructor
    public EvaluationManager() {
        evaluations = new ArrayList<>();
    }

    // =====================================================
    // ADD EVALUATION
    // =====================================================

    public boolean addEvaluation(
            MenuItem menuItem,
            int rating,
            String evaluationPeriod) {

        if (menuItem == null) {
            System.out.println(
                    "Error: Menu item cannot be null."
            );
            return false;
        }

        if (rating < 1 || rating > 5) {
            System.out.println(
                    "Error: Rating must be between 1 and 5."
            );
            return false;
        }

        if (evaluationPeriod == null
                || evaluationPeriod.trim().isEmpty()) {

            System.out.println(
                    "Error: Evaluation period cannot be empty."
            );
            return false;
        }

        Evaluation evaluation =
                new Evaluation(
                        menuItem,
                        rating,
                        evaluationPeriod.trim()
                );

        evaluations.add(evaluation);

        System.out.println(
                "Evaluation added successfully."
        );

        return true;
    }

    // =====================================================
    // VIEW ALL EVALUATIONS
    // =====================================================

    public void viewEvaluations() {

        if (evaluations.isEmpty()) {

            System.out.println(
                    "No evaluations available."
            );

            return;
        }

        System.out.println(
                "\n========== EVALUATIONS =========="
        );

        for (Evaluation evaluation :
                evaluations) {

            evaluation.displayEvaluation();
        }

        System.out.println(
                "-------------------------------------"
        );
    }

    // =====================================================
    // FIND EVALUATION BY MENU ITEM ID
    // =====================================================

    public Evaluation findEvaluation(
            int menuItemId) {

        // Return latest evaluation for the item
        for (int i = evaluations.size() - 1;
             i >= 0;
             i--) {

            Evaluation evaluation =
                    evaluations.get(i);

            if (evaluation
                    .getMenuItem()
                    .getId()
                    == menuItemId) {

                return evaluation;
            }
        }

        return null;
    }

    // =====================================================
    // GET FEEDBACK - TBI
    // =====================================================

    public void showFeedback(
            int menuItemId) {

        Evaluation evaluation =
                findEvaluation(menuItemId);

        if (evaluation == null) {

            System.out.println(
                    "No evaluation found for menu item ID "
                            + menuItemId
            );

            return;
        }

        System.out.println(
                "\n========== FEEDBACK =========="
        );

        System.out.println(
                "Menu Item: "
                        + evaluation
                        .getMenuItem()
                        .getName()
        );

        System.out.println(
                "Rating: "
                        + evaluation.getRating()
                        + "/5"
        );

        System.out.println(
                "Feedback: "
                        + evaluation.getFeedback()
        );
    }

    // =====================================================
    // GET FEEDBACK - GUI
    // =====================================================

    public String getFeedbackText(
            int menuItemId) {

        Evaluation evaluation =
                findEvaluation(menuItemId);

        if (evaluation == null) {

            return "No evaluation found for menu item ID "
                    + menuItemId;
        }

        StringBuilder output =
                new StringBuilder();

        output.append(
                "========== FEEDBACK ==========\n\n"
        );

        output.append(
                "Menu Item: "
        );

        output.append(
                evaluation
                        .getMenuItem()
                        .getName()
        );

        output.append("\n");

        output.append(
                "Rating: "
        );

        output.append(
                evaluation.getRating()
        );

        output.append("/5\n");

        output.append(
                "Feedback: "
        );

        output.append(
                evaluation.getFeedback()
        );

        return output.toString();
    }

    // =====================================================
    // REWARD / PENALTY - TBI
    // =====================================================

    public void showRewardPenalty(
            int menuItemId) {

        Evaluation evaluation =
                findEvaluation(menuItemId);

        if (evaluation == null) {

            System.out.println(
                    "No evaluation found for menu item ID "
                            + menuItemId
            );

            return;
        }

        System.out.println(
                "\n========== REWARD / PENALTY =========="
        );

        System.out.println(
                "Menu Item: "
                        + evaluation
                        .getMenuItem()
                        .getName()
        );

        System.out.println(
                "Rating: "
                        + evaluation.getRating()
                        + "/5"
        );

        System.out.println(
                "Action: "
                        + evaluation.getAction()
        );
    }

    // =====================================================
    // REWARD / PENALTY - GUI
    // =====================================================

    public String getRewardPenaltyText(
            int menuItemId) {

        Evaluation evaluation =
                findEvaluation(menuItemId);

        if (evaluation == null) {

            return "No evaluation found for menu item ID "
                    + menuItemId;
        }

        StringBuilder output =
                new StringBuilder();

        output.append(
                "========== REWARD / PENALTY ==========\n\n"
        );

        output.append(
                "Menu Item: "
        );

        output.append(
                evaluation
                        .getMenuItem()
                        .getName()
        );

        output.append("\n");

        output.append(
                "Rating: "
        );

        output.append(
                evaluation.getRating()
        );

        output.append("/5\n");

        output.append(
                "Action: "
        );

        output.append(
                evaluation.getAction()
        );

        return output.toString();
    }

    // =====================================================
    // PERFORMANCE CHECK - TBI
    // =====================================================

    public void checkPerformance(
            int menuItemId) {

        Evaluation evaluation =
                findEvaluation(menuItemId);

        if (evaluation == null) {

            System.out.println(
                    "No evaluation found for menu item ID "
                            + menuItemId
            );

            return;
        }

        System.out.println(
                "\n========== PERFORMANCE CHECK =========="
        );

        System.out.println(
                "Menu Item: "
                        + evaluation
                        .getMenuItem()
                        .getName()
        );

        System.out.println(
                "Period: "
                        + evaluation
                        .getEvaluationPeriod()
        );

        System.out.println(
                "Rating: "
                        + evaluation.getRating()
                        + "/5"
        );

        System.out.println(
                "Status: "
                        + evaluation.getStatus()
        );

        System.out.println(
                "Feedback: "
                        + evaluation.getFeedback()
        );

        System.out.println(
                "Action: "
                        + evaluation.getAction()
        );
    }

    // =====================================================
    // PERFORMANCE CHECK - GUI
    // =====================================================

    public String getPerformanceText(
            int menuItemId) {

        Evaluation evaluation =
                findEvaluation(menuItemId);

        if (evaluation == null) {

            return "No evaluation found for menu item ID "
                    + menuItemId;
        }

        StringBuilder output =
                new StringBuilder();

        output.append(
                "========== PERFORMANCE CHECK ==========\n\n"
        );

        output.append(
                "Menu Item: "
        );

        output.append(
                evaluation
                        .getMenuItem()
                        .getName()
        );

        output.append("\n");

        output.append(
                "Period: "
        );

        output.append(
                evaluation
                        .getEvaluationPeriod()
        );

        output.append("\n");

        output.append(
                "Rating: "
        );

        output.append(
                evaluation.getRating()
        );

        output.append("/5\n");

        output.append(
                "Status: "
        );

        output.append(
                evaluation.getStatus()
        );

        output.append("\n");

        output.append(
                "Feedback: "
        );

        output.append(
                evaluation.getFeedback()
        );

        output.append("\n");

        output.append(
                "Action: "
        );

        output.append(
                evaluation.getAction()
        );

        return output.toString();
    }

    // =====================================================
    // GET NUMBER OF EVALUATIONS
    // =====================================================

    public int getEvaluationCount() {

        return evaluations.size();
    }

    // =====================================================
    // GET ALL EVALUATIONS
    // =====================================================

    public ArrayList<Evaluation> getEvaluations() {

        return evaluations;
    }

    // =====================================================
    // CHECK EMPTY
    // =====================================================

    public boolean isEmpty() {

        return evaluations.isEmpty();
    }
}
