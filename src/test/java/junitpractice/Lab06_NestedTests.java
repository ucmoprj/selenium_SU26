package junitpractice;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Lab 06 - Nested Tests
 *
 * Learning Objectives:
 *   1. Use @Nested to group related tests inside an inner class
 *   2. Understand how @BeforeEach in outer and inner classes interact
 *   3. Organize tests to reflect the structure of the class under test
 *   4. Improve test report readability with a hierarchy of @DisplayName labels
 *
 * Instructions:
 *   - Uncomment one STEP block at a time, then run the test
 *   - Run: mvn test -Dtest=Lab06_NestedTests
 *   - Notice the tree structure in the test report
 *
 * Target class: ShoppingCart
 *
 * Structure:
 *   Lab06_NestedTests
 *     --""--"--"-- EmptyCart
 *           --"--"--"-- isEmptyByDefault
 *           --""--"--"-- addingFirstItemMakesItNonEmpty
 *     --""--"--"-- CartWithItems
 *           --"--"--"-- totalCalculation
 *           --"--"--"-- itemCount
 *           --""--"--"-- WithDiscount
 *                 --"--"--"-- tenPercentDiscount
 *                 --""--"--"-- invalidDiscount_shouldThrow
 */
@DisplayName("ShoppingCart")
public class Lab06_NestedTests {

    ShoppingCart cart;

    // ============================================================
    // STEP 1 --" Outer @BeforeEach
    //
    // This runs before EACH test in the outer class AND all nested classes.
    // Nested @BeforeEach methods run AFTER this one.
    // ============================================================
    @BeforeEach
    void createCart() {
        // cart = new ShoppingCart();
    }

    // ============================================================
    // STEP 2 --" First @Nested group: empty cart behavior
    //
    // @Nested marks an inner class as a test group.
    // Inner class tests can access the outer class's fields (e.g., cart).
    // ============================================================
    @Nested
    @DisplayName("when cart is empty")
    class EmptyCart {

        @Test
        @DisplayName("isEmpty() returns true")
        void isEmptyByDefault() {
            // assertTrue(cart.isEmpty());
        }

        @Test
        @DisplayName("getTotal() returns 0.0")
        void totalIsZero() {
            // assertEquals(0.0, cart.getTotal());
        }

        @Test
        @DisplayName("addItem() makes cart non-empty")
        void addingFirstItemMakesItNonEmpty() {
            // cart.addItem("Apple", 1.50);
            // assertFalse(cart.isEmpty());
        }
    }

    // ============================================================
    // STEP 3 --" Second @Nested group: cart with items
    //
    // The nested @BeforeEach runs AFTER the outer @BeforeEach.
    // Use it to add items that all tests in this group need.
    // ============================================================
    @Nested
    @DisplayName("when cart has items")
    class CartWithItems {

        @BeforeEach
        void addItems() {
            // cart.addItem("Milk",    1.20);
            // cart.addItem("Bread",   2.50);
            // cart.addItem("Cheese",  4.30);
        }

        @Test
        @DisplayName("getTotal() sums all item prices")
        void totalCalculation() {
            // assertEquals(8.0, cart.getTotal(), 0.001);
        }

        @Test
        @DisplayName("getItemCount() returns correct count")
        void itemCount() {
            // assertEquals(3, cart.getItemCount());
        }

        @Test
        @DisplayName("removeItem() decreases count by 1")
        void removeItem() {
            // cart.removeItem("Milk");
            // assertEquals(2, cart.getItemCount());
        }

        // ============================================================
        // STEP 4 --" Deeply nested group: discount scenarios
        //
        // @Nested classes can be nested inside other @Nested classes.
        // This creates a three-level hierarchy in the test report.
        // ============================================================
        @Nested
        @DisplayName("with discount applied")
        class WithDiscount {

            @Test
            @DisplayName("10% discount reduces total to 7.20")
            void tenPercentDiscount() {
                // assertEquals(7.20, cart.getTotalWithDiscount(10), 0.001);
            }

            @Test
            @DisplayName("0% discount returns full total")
            void zeroPercentDiscount() {
                // assertEquals(cart.getTotal(), cart.getTotalWithDiscount(0), 0.001);
            }

            @Test
            @DisplayName("discount > 100 throws IllegalArgumentException")
            void invalidDiscount_shouldThrow() {
                // assertThrows(
                //     IllegalArgumentException.class,
                //     () -> cart.getTotalWithDiscount(110)
                // );
            }
        }
    }
}

