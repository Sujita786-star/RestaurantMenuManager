package restaurantmenumanager;

public class FoodItem extends MenuItem {

    // Food-specific attribute
    private String dietaryType;

    // Constructor
    public FoodItem(int id, String name, double price,
                    String category, boolean available,
                    String dietaryType) {

        super(id, name, price, category, available);

        this.dietaryType = dietaryType;
    }

    // Getter
    public String getDietaryType() {
        return dietaryType;
    }

    // Setter
    public void setDietaryType(String dietaryType) {
        this.dietaryType = dietaryType;
    }

    // Polymorphism
    @Override
    public String getDetails() {

        return "Food Item: " + getName()
                + " | Category: " + getCategory()
                + " | Price: $" + String.format("%.2f", getPrice())
                + " | Dietary Type: " + dietaryType
                + " | Available: " + isAvailable();
    }
}
