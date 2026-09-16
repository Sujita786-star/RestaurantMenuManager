package restaurantmenumanager;

public abstract class MenuItem {

    // Attributes - Encapsulation
    private int id;
    private String name;
    private double price;
    private String category;
    private boolean available;

    // Constructor
    public MenuItem(int id, String name, double price,
                    String category, boolean available) {

        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.available = available;
    }

    // Get ID
    public int getId() {
        return id;
    }

    // Set ID
    public void setId(int id) {
        this.id = id;
    }

    // Get Name
    public String getName() {
        return name;
    }

    // Set Name
    public void setName(String name) {
        this.name = name;
    }

    // Get Price
    public double getPrice() {
        return price;
    }

    // Set Price
    public void setPrice(double price) {
        this.price = price;
    }

    // Get Category
    public String getCategory() {
        return category;
    }

    // Set Category
    public void setCategory(String category) {
        this.category = category;
    }

    // Check Availability
    public boolean isAvailable() {
        return available;
    }

    // Set Availability
    public void setAvailable(boolean available) {
        this.available = available;
    }

    // Polymorphic method
    public abstract String getDetails();
}
