package junitpractice;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple shopping cart used as a test target in JUnit labs.
 *
 * Covers: add/remove items, total calculation, discount application
 * Good for Nested tests and multi-step state verification.
 */
public class ShoppingCart {

    private final Map<String, Double> items = new HashMap<>();

    public void addItem(String name, double price) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Item name cannot be blank");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        items.put(name, price);
    }

    public void removeItem(String name) {
        if (!items.containsKey(name)) {
            throw new IllegalArgumentException("Item not found: " + name);
        }
        items.remove(name);
    }

    public double getTotal() {
        return items.values().stream().mapToDouble(Double::doubleValue).sum();
    }

    /**
     * Applies a percentage discount (0â€“100) and returns the discounted total.
     */
    public double getTotalWithDiscount(double discountPercent) {
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Discount must be between 0 and 100");
        }
        return getTotal() * (1 - discountPercent / 100);
    }

    public int getItemCount() {
        return items.size();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public Map<String, Double> getItems() {
        return Collections.unmodifiableMap(items);
    }
}

