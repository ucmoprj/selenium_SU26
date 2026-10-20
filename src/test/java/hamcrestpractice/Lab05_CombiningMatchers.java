package hamcrestpractice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Lab 05 - Combining Matchers
 *
 * Learning Objectives:
 *   1. Require ALL conditions with allOf
 *   2. Require ANY condition with anyOf
 *   3. Use the fluent forms both().and() and either().or()
 *   4. Nest matchers inside other matchers
 *   5. Give a matcher a custom description with describedAs
 *   6. Read the failure message of a combined matcher
 *
 * Key Concept:
 *   Matchers are objects, so they can be passed into other matchers.
 *   Small matchers combine into bigger ones, like LEGO bricks:
 *
 *     allOf(startsWith("SE"), endsWith("enrolled)"))
 *     everyItem(both(greaterThan(0.0)).and(lessThanOrEqualTo(4.0)))
 *
 * Instructions:
 *   - Uncomment one STEP block at a time, then run the test
 *   - Run: mvn test -Dtest=Lab05_CombiningMatchers
 *
 * Target classes: Course, Student
 */
public class Lab05_CombiningMatchers {

    Course course;
    Student alice;
    Student bob;
    Student carol;

    @BeforeEach
    void setUp() {
        course = new Course("SE4930", "Software Testing", 30);
        alice = new Student(1, "Alice", "alice@example.com", "Computer Science", 3.8);
        bob = new Student(2, "Bob", "bob@example.com", "Software Engineering", 3.2);
        carol = new Student(3, "Carol", "carol@ucmo.edu", "Computer Science", 3.6);
        course.enroll(alice);
        course.enroll(bob);
        course.enroll(carol);
    }

    // ============================================================
    // STEP 1 -- allOf (logical AND)
    //
    // allOf(m1, m2, m3) -- passes only when EVERY matcher passes.
    // ============================================================
    @Test
    void allOfMatchers() {
        // assertThat(course.getSummary(), allOf(
        //     startsWith("SE4930"),
        //     containsString("Software Testing"),
        //     endsWith("enrolled)")));
    }

    // ============================================================
    // STEP 2 -- anyOf (logical OR)
    //
    // anyOf(m1, m2) -- passes when AT LEAST ONE matcher passes.
    // ============================================================
    @Test
    void anyOfMatchers() {
        // assertThat(bob.getMajor(), anyOf(
        //     equalTo("Computer Science"),
        //     equalTo("Software Engineering")));
    }

    // ============================================================
    // STEP 3 -- both().and() / either().or()
    //
    // A fluent way to combine exactly two (or more) matchers:
    //   both(m1).and(m2)   -- same as allOf(m1, m2)
    //   either(m1).or(m2)  -- same as anyOf(m1, m2)
    // ============================================================
    @Test
    void fluentCombinations() {
        // assertThat(alice.getGpa(), both(greaterThanOrEqualTo(0.0)).and(lessThanOrEqualTo(4.0)));

        // assertThat(alice.getEmail(), either(endsWith("@example.com")).or(endsWith("@ucmo.edu")));
        // assertThat(carol.getEmail(), either(endsWith("@example.com")).or(endsWith("@ucmo.edu")));
    }

    // ============================================================
    // STEP 4 -- Nesting matchers
    //
    // Any matcher can be used as an argument to another matcher.
    // Here a combined matcher is applied to every item of a list.
    // ============================================================
    @Test
    void nestedMatchers() {
        // assertThat(course.getStudentNames(), allOf(hasItem("Alice"), hasItem("Bob"), not(hasItem("Zoe"))));

        // assertThat(course.getStudentNames(), everyItem(either(startsWith("A")).or(startsWith("B")).or(startsWith("C"))));
    }

    // ============================================================
    // STEP 5 -- describedAs
    //
    // describedAs(description, matcher, values...)
    //   Replaces the matcher's description in failure messages.
    //   %0, %1, ... in the description are replaced with the values.
    // ============================================================
    @Test
    void customDescription() {
        // assertThat(course.getAverageGpa(),
        //     describedAs("an average GPA above %0", greaterThan(3.0), 3.0));

        // AssertionError error = assertThrows(AssertionError.class,
        //     () -> assertThat(bob.getGpa(), describedAs("an honors GPA (>= %0)", greaterThanOrEqualTo(3.5), 3.5)));
        // System.out.println(error.getMessage());
    }

    // ============================================================
    // STEP 6 -- Failure message of a combined matcher
    //
    // A combined matcher reports which part failed.
    // Run this step and read the output carefully:
    // which of the three conditions did not match?
    // ============================================================
    @Test
    void combinedFailureMessage() {
        // AssertionError error = assertThrows(AssertionError.class,
        //     () -> assertThat(course.getSummary(), allOf(
        //         startsWith("SE4930"),
        //         containsString("Software Quality"),
        //         endsWith("enrolled)"))));

        // System.out.println(error.getMessage());
    }
}
