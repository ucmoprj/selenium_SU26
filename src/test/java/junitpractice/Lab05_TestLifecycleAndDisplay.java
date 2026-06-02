package junitpractice;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Lab 05 - Test Lifecycle Control & Display
 *
 * Learning Objectives:
 *   1. @TestMethodOrder -- control the order in which tests run
 *   2. @DisplayName     -- human-readable names in test reports
 *   3. @Disabled        -- skip a test with a reason
 *   4. @Tag             -- group tests by category (run subsets with -Dgroups=...)
 *   5. @TestInstance    -- share state across tests without static fields
 *
 * Instructions:
 *   - Uncomment one STEP block at a time, then run the test
 *   - Run all:          mvn test -Dtest=Lab05_TestLifecycleAndDisplay
 *   - Run tagged tests: mvn test -Dgroups=fast
 *
 * Target class: BankAccount
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Lab05_TestLifecycleAndDisplay {

    BankAccount account;

    @BeforeEach
    void setUp() {
        account = new BankAccount("Alice", 1000.0);
    }

    // ============================================================
    // STEP 1 -- @Order
    //
    // @Order(n) controls execution order when @TestMethodOrder
    // is set to OrderAnnotation. Lower numbers run first.
    //
    // Note: tests should ideally be independent, but ordered tests
    // are sometimes useful for documenting a workflow step-by-step.
    // ============================================================
    @Test
    @Order(1)
    @DisplayName("Step 1: Verify initial balance is 1000")
    void verifyInitialBalance() {
        // assertEquals(1000.0, account.getBalance());
    }

    @Test
    @Order(2)
    @DisplayName("Step 2: Deposit 500 -- balance becomes 1500")
    void depositIncreasesBalance() {
        // account.deposit(500.0);
        // assertEquals(1500.0, account.getBalance());
    }

    @Test
    @Order(3)
    @DisplayName("Step 3: Withdraw 200 -- balance becomes 800")
    void withdrawDecreasesBalance() {
        // account.withdraw(200.0);
        // assertEquals(800.0, account.getBalance());
    }

    // ============================================================
    // STEP 2 -- @Disabled with a reason
    //
    // Always explain WHY the test is disabled.
    // A disabled test with no reason is a maintenance hazard.
    // ============================================================
    @Test
    @Order(4)
    @Disabled("Interest calculation not yet implemented in BankAccount")
    @DisplayName("Step 4: Apply monthly interest")
    void applyMonthlyInterest() {
        // double expected = account.getBalance() * 1.02;
        // account.applyInterest(0.02);
        // assertEquals(expected, account.getBalance(), 0.001);
    }

    // ============================================================
    // STEP 3 -- @Tag
    //
    // Tags let you run a subset of tests.
    // Example: mvn test -Dgroups=fast   runs only @Tag("fast") tests.
    //
    // Common tags: "fast", "slow", "integration", "smoke", "regression"
    // ============================================================
    @Test
    @Order(5)
    @Tag("fast")
    @DisplayName("Owner name should not change after operations")
    void ownerNameIsImmutable() {
        // account.deposit(100.0);
        // account.withdraw(50.0);
        // assertEquals("Alice", account.getOwner());
    }

    @Test
    @Order(6)
    @Tag("fast")
    @DisplayName("Balance should never go below zero")
    void balanceNeverNegative() {
        // assertThrows(IllegalStateException.class,
        //     () -> account.withdraw(account.getBalance() + 1));
        // assertTrue(account.getBalance() >= 0);
    }

    // ============================================================
    // STEP 4 -- @TestInstance(PER_CLASS)
    //
    // By default JUnit creates a NEW instance of the test class
    // before each @Test (PER_METHOD). This means @BeforeAll / @AfterAll
    // methods must be static.
    //
    // @TestInstance(Lifecycle.PER_CLASS) reuses ONE instance for all tests.
    // Benefit: @BeforeAll / @AfterAll can be non-static,
    //          and you can share mutable state across tests.
    // Trade-off: tests can accidentally depend on each other's side effects.
    //
    // To try this: add @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    // to the class declaration above and remove 'static' from a @BeforeAll method.
    // ============================================================
}

