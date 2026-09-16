package restaurantmenumanager;

public class Evaluation {

    private MenuItem menuItem;
    private int rating;
    private String evaluationPeriod;
    private String feedback;
    private String status;
    private String action;

    // Constructor
    public Evaluation(MenuItem menuItem,
                      int rating,
                      String evaluationPeriod) {

        this.menuItem = menuItem;
        this.rating = rating;
        this.evaluationPeriod = evaluationPeriod;

        generateEvaluation();
    }

    // Generate feedback and performance result
    private void generateEvaluation() {

        if (rating >= 4) {

            feedback = "Positive feedback";
            status = "Good Performance";
            action = "Reward";

        } else if (rating <= 2) {

            feedback = "Negative feedback";
            status = "Needs Improvement";
            action = "Penalty / Review";

        } else {

            feedback = "Neutral feedback";
            status = "Average Performance";
            action = "Review";
        }
    }

    // Get Menu Item
    public MenuItem getMenuItem() {
        return menuItem;
    }

    // Get Rating
    public int getRating() {
        return rating;
    }

    // Get Evaluation Period
    public String getEvaluationPeriod() {
        return evaluationPeriod;
    }

    // Get Feedback
    public String getFeedback() {
        return feedback;
    }

    // Get Status
    public String getStatus() {
        return status;
    }

    // Get Action
    public String getAction() {
        return action;
    }

    // Display evaluation
    public void displayEvaluation() {

        System.out.println("-------------------------------------");
        System.out.println("Menu Item: "
                + menuItem.getName());
        System.out.println("Evaluation Period: "
                + evaluationPeriod);
        System.out.println("Rating: "
                + rating + "/5");
        System.out.println("Feedback: "
                + feedback);
        System.out.println("Status: "
                + status);
        System.out.println("Action: "
                + action);
    }
}
