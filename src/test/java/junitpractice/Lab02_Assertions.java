package junitpractice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Lab 02 - Assertions
 *
 * Learning Objectives:
 *   1. assertEquals / assertNotEquals -- compare expected vs actual values
 *   2. assertTrue / assertFalse       -- verify boolean conditions
 *   3. assertNull / assertNotNull     -- check for null
 *   4. assertThrows                   -- verify an exception is thrown
 *   5. assertAll                      -- group multiple assertions (all run even if one fails)
 *
 * Instructions:
 *   - Uncomment one STEP block at a time, then run the test
 *   - Run: mvn test -Dtest=Lab02_Assertions
 *
 * Target classes: Calculator, BankAccount
 */
public class Lab02_Assertions {

    Calculator calculator;
    BankAccount account;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
        account = new BankAccount("Alice", 1000.0);
    }

    // ============================================================
    // STEP 1 -- assertEquals / assertNotEquals
    //
    // assertEquals(expected, actual)
    //   Passes when expected.equals(actual).
    //   The FIRST argument is always the expected value -- this matters
    //   because failure messages say "expected <X> but was <Y>".
    //
    // assertNotEquals(unexpected, actual)
    //   Passes when the two values are NOT equal.
    // ============================================================
    @Test
    void testEquals() {
        // assertEquals(7, calculator.add(3, 4));
        // assertEquals(6, calculator.multiply(2, 3));
        // assertNotEquals(0, calculator.add(1, 1));
    }

    // ============================================================
    // STEP 2 -- assertTrue / assertFalse
    //
    // assertTrue(condition)  -- passes when condition is true
    // assertFalse(condition) -- passes when condition is false
    //
    // Use these when the result is inherently boolean.
    // ============================================================
    @Test
    void testBooleanConditions() {
        // assertTrue(calculator.add(2, 3) > 0);
        // assertFalse(calculator.subtract(3, 3) > 0);
    }

    // ============================================================
    // STEP 3 -- assertNull / assertNotNull
    //
    // assertNull(object)    -- passes when object IS null
    // assertNotNull(object) -- passes when object is NOT null
    //
    // Useful for checking return values and object creation.
    // ============================================================
    @Test
    void testNullChecks() {
        // BankAccount newAccount = new BankAccount("Bob", 0);
        // assertNotNull(newAccount);
        // assertNotNull(newAccount.getOwner());
    }

    // ============================================================
    // STEP 4 -- Custom failure messages
    //
    // Every assertion accepts an optional String (or Supplier<String>)
    // as the last argument -- shown in the failure report.
    // Use a lambda () -> "..." for expensive message construction.
    // ============================================================
    @Test
    void testWithMessage() {
        // int result = calculator.add(2, 2);
        // assertEquals(4, result, "2 + 2 should equal 4");
        // assertTrue(result > 0, () -> "Result was " + result + " but should be positive");
    }

    // ============================================================
    // STEP 5 -- assertAll
    //
    // assertAll(executables...)
    //   Runs ALL assertions even if some fail, then reports all failures.
    //   Without assertAll, the test stops at the first failure,
    //   hiding subsequent problems.
    //
    // Use assertAll when checking multiple properties of the same object.
    // ============================================================
    @Test
    void testAssertAll() {
        // assertAll("calculator operations",
        //     () -> assertEquals(5,  calculator.add(2, 3)),
        //     () -> assertEquals(1,  calculator.subtract(4, 3)),
        //     () -> assertEquals(6,  calculator.multiply(2, 3)),
        //     () -> assertEquals(2.5, calculator.divide(5, 2))
        // );
    }

    // ============================================================
    // STEP 6 -- assertThrows
    //
    // assertThrows(ExceptionType.class, executable)
    //   Passes when the executable throws the expected exception type.
    //   Returns the exception so you can inspect its message.
    //
    // If the code does NOT throw, the test FAILS.
    // ============================================================
    @Test
    void testThrows() {
        // ArithmeticException ex = assertThrows(
        //     ArithmeticException.class,
        //     () -> calculator.divide(10, 0)
        // );
        // System.out.println("Exception message: " + ex.getMessage());
    }
}

