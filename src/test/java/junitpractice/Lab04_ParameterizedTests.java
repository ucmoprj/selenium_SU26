package junitpractice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Lab 04 - Parameterized Tests
 *
 * Learning Objectives:
 *   1. Use @ParameterizedTest to run one test method with multiple inputs
 *   2. Supply values with @ValueSource (single values)
 *   3. Supply pairs with @CsvSource (comma-separated input + expected)
 *   4. Supply complex objects with @MethodSource (a Stream factory method)
 *
 * Instructions:
 *   - Uncomment one STEP block at a time, then run the test
 *   - Run: mvn test -Dtest=Lab04_ParameterizedTests
 *   - Notice how one method runs multiple times in the test report
 *
 * Target class: UserValidator, Calculator
 */
public class Lab04_ParameterizedTests {

    UserValidator validator;
    Calculator calculator;

    @BeforeEach
    void setUp() {
        validator = new UserValidator();
        calculator = new Calculator();
    }

    // ============================================================
    // STEP 1 -- @ValueSource
    //
    // Provides a single array of values -- each runs the test once.
    // The parameter type must match the @ValueSource type (strings, ints, etc.).
    //
    // Use case: check that several valid inputs all pass.
    // ============================================================
    @ParameterizedTest
    @ValueSource(strings = {"alice@example.com", "bob@test.org", "user@domain.co.uk"})
    void validEmails_shouldPass(String email) {
        // assertTrue(validator.isValidEmail(email),
        //     "Expected valid email: " + email);
    }

    // ============================================================
    // STEP 2 -- @ValueSource with invalid inputs
    //
    // Use case: check that several invalid inputs all fail.
    // ============================================================
    @ParameterizedTest
    @ValueSource(strings = {"notAnEmail", "@nodomain.com", "missing@", ""})
    void invalidEmails_shouldFail(String email) {
        // assertFalse(validator.isValidEmail(email),
        //     "Expected invalid email: " + email);
    }

    // ============================================================
    // STEP 3 -- @CsvSource
    //
    // Each string is one test case: "input1, input2, expected"
    // Commas separate the columns; JUnit maps them to method parameters.
    //
    // Use case: test input/output pairs without a separate data file.
    // ============================================================
    @ParameterizedTest
    @CsvSource({
        "3, 4, 7",
        "0, 0, 0",
        "-1, 1, 0",
        "100, 200, 300"
    })
    void addTwoNumbers_csvSource(int a, int b, int expected) {
        // assertEquals(expected, calculator.add(a, b));
    }

    // ============================================================
    // STEP 4 -- @CsvSource with strings containing spaces
    //
    // Wrap values in single quotes inside the CSV string when they
    // contain commas or leading/trailing spaces.
    // ============================================================
    @ParameterizedTest
    @CsvSource({
        "Password1,  true",
        "password1,  false",
        "PASSWORD,   false",
        "Short1,     false",
        "ValidPass9, true"
    })
    void passwordValidation_csvSource(String password, boolean expected) {
        // assertEquals(expected, validator.isValidPassword(password.trim()),
        //     "Password: '" + password.trim() + "'");
    }

    // ============================================================
    // STEP 5 -- @MethodSource
    //
    // Points to a static factory method that returns a Stream of arguments.
    // Use when test data is complex (objects, lists) or needs logic to generate.
    //
    // The factory method name must match the string in @MethodSource,
    // or omit it to use the same name as the test method.
    // ============================================================
    @ParameterizedTest
    @MethodSource("usernameProvider")
    void usernameValidation_methodSource(String username, boolean expected) {
        // assertEquals(expected, validator.isValidUsername(username),
        //     "Username: '" + username + "'");
    }

    static Stream<org.junit.jupiter.params.provider.Arguments> usernameProvider() {
        return Stream.of(
            org.junit.jupiter.params.provider.Arguments.of("alice",       true),
            org.junit.jupiter.params.provider.Arguments.of("user_123",    true),
            org.junit.jupiter.params.provider.Arguments.of("ab",          false),   // too short
            org.junit.jupiter.params.provider.Arguments.of("1startDigit", false),   // starts with digit
            org.junit.jupiter.params.provider.Arguments.of("has space",   false),   // space not allowed
            org.junit.jupiter.params.provider.Arguments.of("a".repeat(21), false)   // too long
        );
    }
}

