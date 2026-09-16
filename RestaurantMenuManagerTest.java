package restaurantmenumanager;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantMenuManagerTest {

    @Test
    void testAddMenuItem() {
        MenuManager menuManager = new MenuManager();

        FoodItem item = new FoodItem(
                1,
                "Burger",
                12.50,
                "Main",
                true,
                "Non-Vegetarian"
        );

        menuManager.addItem(item);

        assertEquals(1, menuManager.getItemCount());
        assertNotNull(menuManager.searchItem(1));
    }

    @Test
    void testDuplicateIdIsNotAdded() {
        MenuManager menuManager = new MenuManager();

        FoodItem item1 = new FoodItem(
                1,
                "Burger",
                12.50,
                "Main",
                true,
                "Non-Vegetarian"
        );

        FoodItem item2 = new FoodItem(
                1,
                "Pizza",
                15.00,
                "Main",
                true,
                "Non-Vegetarian"
        );

        menuManager.addItem(item1);
        menuManager.addItem(item2);

        assertEquals(1, menuManager.getItemCount());
    }

    @Test
    void testMergeSortByPrice() {
        ArrayList<MenuItem> items = new ArrayList<>();

        items.add(new FoodItem(
                1, "Burger", 15.00, "Main", true, "Non-Vegetarian"
        ));

        items.add(new FoodItem(
                2, "Salad", 8.00, "Main", true, "Vegetarian"
        ));

        items.add(new DrinkItem(
                3, "Juice", 5.00, "Drink", true, "Medium"
        ));

        SortingManager.mergeSortByPrice(items);

        assertEquals(5.00, items.get(0).getPrice(), 0.001);
        assertEquals(8.00, items.get(1).getPrice(), 0.001);
        assertEquals(15.00, items.get(2).getPrice(), 0.001);
    }

    @Test
    void testBinarySearchByIdFound() {
        ArrayList<MenuItem> items = new ArrayList<>();

        items.add(new FoodItem(
                3, "Burger", 15.00, "Main", true, "Non-Vegetarian"
        ));

        items.add(new FoodItem(
                1, "Salad", 8.00, "Main", true, "Vegetarian"
        ));

        items.add(new DrinkItem(
                2, "Juice", 5.00, "Drink", true, "Medium"
        ));

        SortingManager.mergeSortById(items);

        MenuItem result =
                SearchingManager.binarySearchById(items, 2);

        assertNotNull(result);
        assertEquals(2, result.getId());
    }

    @Test
    void testBinarySearchByIdNotFound() {
        ArrayList<MenuItem> items = new ArrayList<>();

        items.add(new FoodItem(
                1, "Burger", 15.00, "Main", true, "Non-Vegetarian"
        ));

        items.add(new FoodItem(
                2, "Salad", 8.00, "Main", true, "Vegetarian"
        ));

        items.add(new DrinkItem(
                3, "Juice", 5.00, "Drink", true, "Medium"
        ));

        SortingManager.mergeSortById(items);

        MenuItem result =
                SearchingManager.binarySearchById(items, 99);

        assertNull(result);
    }

    @Test
    void testInvalidEvaluationRating() {
        MenuManager menuManager = new MenuManager();
        EvaluationManager evaluationManager = new EvaluationManager();

        FoodItem item = new FoodItem(
                1,
                "Burger",
                12.50,
                "Main",
                true,
                "Non-Vegetarian"
        );

        menuManager.addItem(item);

        boolean result =
                evaluationManager.addEvaluation(
                        item,
                        6,
                        "Week 12"
                );

        assertFalse(result);
        assertEquals(0, evaluationManager.getEvaluationCount());
    }
}
