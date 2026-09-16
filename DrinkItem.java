package restaurantmenumanager;

public class DrinkItem extends MenuItem {

    // Drink-specific attribute
    private String drinkSize;

    // Constructor
    public DrinkItem(int id, String name, double price,
                     String category, boolean available,
                     String drinkSize) {

        super(id, name, price, category, available);

        this.drinkSize = drinkSize;
    }

    // Getter
    public String getDrinkSize() {
        return drinkSize;
    }

    // Setter
    public void setDrinkSize(String drinkSize) {
        this.drinkSize = drinkSize;
    }

    // Polymorphism
    @Override
    public String getDetails() {

        return "Drink Item: " + getName()
                + " | Category: " + getCategory()
                + " | Price: $" + String.format("%.2f", getPrice())
                + " | Size: " + drinkSize
                + " | Available: " + isAvailable();
    }
}
