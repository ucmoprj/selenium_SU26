package junitpractice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.*;

/**
 * Lab 07 - Assumptions
 *
 * Learning Objectives:
 *   1. Understand the difference between a failed assertion and a failed assumption
 *   2. Use assumeTrue  â€” skip the test if a condition is false
 *   3. Use assumeFalse â€” skip the test if a condition is true
 *   4. Use assumingThat â€” run assertions only when a condition holds
 *
 * Key concept:
 *   - Assertion failure  â†’ test is MARKED AS FAILED
 *   - Assumption failure â†’ test is MARKED AS SKIPPED (aborted)
 *
 * Use assumptions when a test only makes sense in a specific environment
 * (e.g., only on Windows, only when a feature flag is on, only in CI).
 *
 * Instructions:
 *   - Uncomment one STEP block at a time, then run the test
 *   - Run: mvn test -Dtest=Lab07_Assumptions
 *   - Notice how aborted tests appear differently from failed tests
 *
 * Target class: StringUtils, Calculator
 */
public class Lab07_Assumptions {

    StringUtils stringUtils;
    Calculator calculator;

    @BeforeEach
    void setUp() {
        stringUtils = new StringUtils();
        calculator = new Calculator();
    }

    // ============================================================
    // STEP 1 â€” assumeTrue
    //
    // assumeTrue(condition)
    //   If condition is FALSE â†’ test is ABORTED (skipped), not failed.
    //   If condition is TRUE  â†’ test continues normally.
    //
    // Example use: skip a test unless running on a specific OS.
    // ============================================================
    @Test
    void runOnlyOnWindows() {
        // assumeTrue(
        //     System.getProperty("os.name").toLowerCase().contains("windows"),
        //     "Skipped: this test only runs on Windows"
        // );
        // String result = stringUtils.reverse("hello");
        // assertEquals("olleh", result);
    }

    // ============================================================
    // STEP 2 â€” assumeFalse
    //
    // assumeFalse(condition)
    //   If condition is TRUE  â†’ test is ABORTED (skipped).
    //   If condition is FALSE â†’ test continues normally.
    //
    // Opposite of assumeTrue â€” use it when you want to skip on a specific condition.
    // ============================================================
    @Test
    void skipInCIEnvironment() {
        // boolean isCI = "true".equalsIgnoreCase(System.getenv("CI"));
        // assumeFalse(isCI, "Skipped: this test is not intended for CI");
        // System.out.println("Running locally â€” performing extended checks");
        // assertEquals(true, stringUtils.isPalindrome("racecar"));
    }

    // ============================================================
    // STEP 3 â€” assumeTrue with a meaningful message
    //
    // The second argument is shown in the test report when skipped.
    // Always provide a reason so teammates know why a test was skipped.
    // ============================================================
    @Test
    void featureFlagEnabled() {
        // String flag = System.getProperty("feature.discount", "off");
        // assumeTrue("on".equals(flag),
        //     "Skipped: discount feature flag is not enabled (-Dfeature.discount=on)");
        //
        // // Only runs when: mvn test -Dtest=Lab07_Assumptions -Dfeature.discount=on
        // assertEquals(90.0, calculator.multiply(9, 10));
    }

    // ============================================================
    // STEP 4 â€” assumingThat
    //
    // assumingThat(condition, executable)
    //   If condition is TRUE  â†’ runs the executable (assertions inside).
    //   If condition is FALSE â†’ skips ONLY the executable, test continues.
    //
    // Unlike assumeTrue, assumingThat does NOT abort the test.
    // Code after assumingThat always runs regardless of the condition.
    // ============================================================
    @Test
    void conditionalAssertions() {
        // String os = System.getProperty("os.name").toLowerCase();

        // assumingThat(os.contains("windows"), () -> {
        //     System.out.println("Windows-specific check");
        //     assertEquals("olleh", stringUtils.reverse("hello"));
        // });

        // assumingThat(os.contains("linux"), () -> {
        //     System.out.println("Linux-specific check");
        //     assertEquals(true, stringUtils.isPalindrome("madam"));
        // });

        // This always runs regardless of OS:
        // assertEquals(5, stringUtils.countWords("one two three four five"));
    }

    // ============================================================
    // STEP 5 â€” Assumption vs Assertion: what's the difference?
    //
    // Run this test to see the difference in the test report:
    //   - assumeTrue(false) â†’ ABORTED  (yellow/skipped in most IDEs)
    //   - assertEquals(1,2) â†’ FAILED   (red in most IDEs)
    //
    // Aborted tests do NOT count as failures in CI pipelines.
    // ============================================================
    @Test
    void demonstrateAbortedVsFailed() {
        // Uncomment ONE of the following lines at a time:

        // This aborts (skips) the test â€” NOT a failure:
        // assumeTrue(false, "This assumption is false â€” test is aborted");

        // This fails the test â€” IS a failure:
        // assertEquals(1, 2, "This assertion is wrong â€” test is failed");
    }
}

