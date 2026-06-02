package junitpractice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Lab 03 - Exception Testing
 *
 * Learning Objectives:
 *   1. Use assertThrows to verify that an exception IS thrown
 *   2. Inspect the exception type and message after catching it
 *   3. Use assertDoesNotThrow to verify that no exception is thrown
 *   4. Understand the difference between expected and unexpected exceptions
 *
 * Instructions:
 *   - Uncomment one STEP block at a time, then run the test
 *   - Run: mvn test -Dtest=Lab03_ExceptionTesting
 *
 * Target class: BankAccount
 */
public class Lab03_ExceptionTesting {

    BankAccount account;

    @BeforeEach
    void setUp() {
        account = new BankAccount("Alice", 500.0);
    }

    // ============================================================
    // STEP 1 -- assertThrows: verify exception type
    //
    // assertThrows(ExpectedException.class, () -> { code that should throw })
    //
    // The lambda is called an "executable". JUnit runs it and checks
    // whether the expected exception type is thrown.
    // If no exception is thrown, or a different type is thrown, the test FAILS.
    // ============================================================
    @Test
    void withdrawMoreThanBalance_shouldThrowIllegalStateException() {
        // assertThrows(
        //     IllegalStateException.class,
        //     () -> account.withdraw(1000.0)
        // );
    }

    // ============================================================
    // STEP 2 -- Inspect the exception message
    //
    // assertThrows returns the thrown exception object.
    // You can call getMessage() on it to verify the exact message.
    // ============================================================
    @Test
    void depositNegativeAmount_shouldThrowWithMessage() {
        // IllegalArgumentException ex = assertThrows(
        //     IllegalArgumentException.class,
        //     () -> account.deposit(-100.0)
        // );
        // assertEquals("Deposit amount must be positive", ex.getMessage());
    }

    // ============================================================
    // STEP 3 -- assertDoesNotThrow
    //
    // assertDoesNotThrow(executable)
    //   Passes when the executable completes WITHOUT throwing any exception.
    //   Use it to explicitly document that a happy-path operation is safe.
    // ============================================================
    @Test
    void validDeposit_shouldNotThrow() {
        // assertDoesNotThrow(() -> account.deposit(200.0));
    }

    // ============================================================
    // STEP 4 -- Verify state AFTER an exception
    //
    // When an exception is thrown mid-operation, the object's state
    // should remain unchanged (no partial updates).
    // ============================================================
    @Test
    void failedWithdraw_shouldNotChangeBalance() {
        // double balanceBefore = account.getBalance();

        // assertThrows(
        //     IllegalStateException.class,
        //     () -> account.withdraw(9999.0)
        // );

        // assertEquals(balanceBefore, account.getBalance(),
        //     "Balance should be unchanged after a failed withdrawal");
    }

    // ============================================================
    // STEP 5 -- Multiple exception scenarios
    //
    // Test all the ways an operation can fail.
    // Each scenario gets its own @Test method for clarity.
    // ============================================================
    @Test
    void withdrawZero_shouldThrowIllegalArgumentException() {
        // assertThrows(
        //     IllegalArgumentException.class,
        //     () -> account.withdraw(0)
        // );
    }

    @Test
    void createAccountWithNegativeBalance_shouldThrow() {
        // assertThrows(
        //     IllegalArgumentException.class,
        //     () -> new BankAccount("Bob", -100.0)
        // );
    }
}

