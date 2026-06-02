package junitpractice;

import org.junit.jupiter.api.*;

/**
 * Lab 01 - Basic Annotations & Test Lifecycle
 *
 * Learning Objectives:
 *   1. Understand the role of @Test -- marks a method as a test case
 *   2. Use @BeforeEach / @AfterEach -- run setup/cleanup around every test
 *   3. Use @BeforeAll / @AfterAll  -- run once for the entire test class
 *   4. Use @DisplayName            -- give tests human-readable names
 *   5. Use @Disabled               -- temporarily skip a test
 *
 * Instructions:
 *   - Read each STEP comment, then uncomment the corresponding code block
 *   - Run: mvn test -Dtest=Lab01_BasicAnnotations
 *   - Watch the console output to see the lifecycle order
 *
 * Target class: Calculator (src/main/java/practice/Calculator.java)
 */
public class Lab01_BasicAnnotations {

    Calculator calculator;

    // ============================================================
    // STEP 1 -- @BeforeAll
    //
    // @BeforeAll runs ONCE before all tests in this class.
    // The method must be static (unless using @TestInstance(PER_CLASS)).
    // Use it for expensive one-time setup: DB connections, config loading, etc.
    // ============================================================
    @BeforeAll
    static void initAll() {
         System.out.println("[BeforeAll] Test class starting...");
    }

    // ============================================================
    // STEP 2 -- @BeforeEach
    //
    // @BeforeEach runs before EACH @Test method.
    // Use it to create a fresh object so tests don't share state.
    // ============================================================
    @BeforeEach
    void setUp() {
        // calculator = new Calculator();
        // System.out.println("[BeforeEach] Calculator created");
    }

    // ============================================================
    // STEP 3 -- @AfterEach
    //
    // @AfterEach runs after EACH @Test method.
    // Use it to release resources, reset state, or log results.
    // ============================================================
    @AfterEach
    void tearDown() {
        // System.out.println("[AfterEach] Test finished");
    }

    // ============================================================
    // STEP 4 -- @AfterAll
    //
    // @AfterAll runs ONCE after all tests complete.
    // The method must be static (unless using @TestInstance(PER_CLASS)).
    // ============================================================
    @AfterAll
    static void cleanUpAll() {
        // System.out.println("[AfterAll] Test class finished");
    }

    // ============================================================
    // STEP 5 -- @Test
    //
    // @Test marks a method as a test case.
    // JUnit discovers and runs all @Test methods automatically.
    // A test passes if it completes without throwing an exception.
    // ============================================================
    @Test
    void addTwoNumbers() {
        // int result = calculator.add(3, 4);
        // System.out.println("3 + 4 = " + result);
    }

    // ============================================================
    // STEP 6 -- @DisplayName
    //
    // @DisplayName replaces the method name in test reports and IDEs.
    // Use descriptive sentences so failures are easy to understand.
    // ============================================================
    @Test
    @DisplayName("Multiplying two negative numbers should return a positive result")
    void multiplyNegatives() {
        // int result = calculator.multiply(-3, -4);
        // System.out.println("-3 * -4 = " + result);
    }

    // ============================================================
    // STEP 7 -- @Disabled
    //
    // @Disabled skips the test entirely -- useful for work-in-progress tests.
    // Always provide a reason so teammates know why it is skipped.
    // ============================================================
    @Test
    @Disabled("Not implemented yet -- will enable in Lab02")
    void subtractNumbers() {
        // int result = calculator.subtract(10, 3);
        // System.out.println("10 - 3 = " + result);
    }
}

