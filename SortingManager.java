package restaurantmenumanager;

import java.util.ArrayList;

public class SortingManager {

    // =====================================================
    // MERGE SORT - Sort Menu Items by Price
    // =====================================================

    public static void mergeSortByPrice(ArrayList<MenuItem> items) {

        if (items == null || items.size() <= 1) {
            return;
        }

        mergeSortByPrice(items, 0, items.size() - 1);
    }

    // Recursive Merge Sort by Price
    private static void mergeSortByPrice(
            ArrayList<MenuItem> items,
            int left,
            int right) {

        if (left < right) {

            int middle =
                    left + (right - left) / 2;

            // Sort left half
            mergeSortByPrice(
                    items,
                    left,
                    middle
            );

            // Sort right half
            mergeSortByPrice(
                    items,
                    middle + 1,
                    right
            );

            // Merge both halves
            mergeByPrice(
                    items,
                    left,
                    middle,
                    right
            );
        }
    }

    // Merge sections by Price
    private static void mergeByPrice(
            ArrayList<MenuItem> items,
            int left,
            int middle,
            int right) {

        ArrayList<MenuItem> leftList =
                new ArrayList<>();

        ArrayList<MenuItem> rightList =
                new ArrayList<>();

        // Copy left half
        for (int i = left; i <= middle; i++) {
            leftList.add(items.get(i));
        }

        // Copy right half
        for (int i = middle + 1; i <= right; i++) {
            rightList.add(items.get(i));
        }

        int i = 0;
        int j = 0;
        int k = left;

        // Compare prices
        while (i < leftList.size()
                && j < rightList.size()) {

            if (leftList.get(i).getPrice()
                    <= rightList.get(j).getPrice()) {

                items.set(
                        k,
                        leftList.get(i)
                );

                i++;

            } else {

                items.set(
                        k,
                        rightList.get(j)
                );

                j++;
            }

            k++;
        }

        // Copy remaining left elements
        while (i < leftList.size()) {

            items.set(
                    k,
                    leftList.get(i)
            );

            i++;
            k++;
        }

        // Copy remaining right elements
        while (j < rightList.size()) {

            items.set(
                    k,
                    rightList.get(j)
            );

            j++;
            k++;
        }
    }

    // =====================================================
    // MERGE SORT - Sort Menu Items by ID
    // =====================================================

    public static void mergeSortById(ArrayList<MenuItem> items) {

        if (items == null || items.size() <= 1) {
            return;
        }

        mergeSortById(
                items,
                0,
                items.size() - 1
        );
    }

    // Recursive Merge Sort by ID
    private static void mergeSortById(
            ArrayList<MenuItem> items,
            int left,
            int right) {

        if (left < right) {

            int middle =
                    left + (right - left) / 2;

            // Sort left half
            mergeSortById(
                    items,
                    left,
                    middle
            );

            // Sort right half
            mergeSortById(
                    items,
                    middle + 1,
                    right
            );

            // Merge both halves
            mergeById(
                    items,
                    left,
                    middle,
                    right
            );
        }
    }

    // Merge sections by ID
    private static void mergeById(
            ArrayList<MenuItem> items,
            int left,
            int middle,
            int right) {

        ArrayList<MenuItem> leftList =
                new ArrayList<>();

        ArrayList<MenuItem> rightList =
                new ArrayList<>();

        // Copy left half
        for (int i = left; i <= middle; i++) {
            leftList.add(items.get(i));
        }

        // Copy right half
        for (int i = middle + 1; i <= right; i++) {
            rightList.add(items.get(i));
        }

        int i = 0;
        int j = 0;
        int k = left;

        // Compare IDs
        while (i < leftList.size()
                && j < rightList.size()) {

            if (leftList.get(i).getId()
                    <= rightList.get(j).getId()) {

                items.set(
                        k,
                        leftList.get(i)
                );

                i++;

            } else {

                items.set(
                        k,
                        rightList.get(j)
                );

                j++;
            }

            k++;
        }

        // Copy remaining left elements
        while (i < leftList.size()) {

            items.set(
                    k,
                    leftList.get(i)
            );

            i++;
            k++;
        }

        // Copy remaining right elements
        while (j < rightList.size()) {

            items.set(
                    k,
                    rightList.get(j)
            );

            j++;
            k++;
        }
    }
}
