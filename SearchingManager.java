package restaurantmenumanager;

import java.util.ArrayList;

public class SearchingManager {

    // =====================================================
    // BINARY SEARCH - Search Menu Item by ID
    // =====================================================

    public static MenuItem binarySearchById(
            ArrayList<MenuItem> items,
            int targetId) {

        if (items == null || items.isEmpty()) {
            return null;
        }

        int left = 0;
        int right = items.size() - 1;

        while (left <= right) {

            int middle = left + (right - left) / 2;

            int middleId =
                    items.get(middle).getId();

            // Target found
            if (middleId == targetId) {
                return items.get(middle);
            }

            // Target is in the right half
            if (middleId < targetId) {
                left = middle + 1;
            }

            // Target is in the left half
            else {
                right = middle - 1;
            }
        }

        // Target not found
        return null;
    }
}
